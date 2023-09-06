<template>
    <div style="width:60%;">
        <div class="search-row">
            <div style="display:inline-flex;">
                <p style="margin-right: 10px;">lokacija: </p>
                <input style="border-radius: 10rem;" type="text" placeholder="Unesite zeljenu adresa" v-model="parametri.adresa.adresa" />
            </div>
        </div>
        <div class="search-row">
            <div style="display:inline-flex; " >
                <p style="margin-right: 10px;">Termin za rezervaciju:  </p>
                <datepicker @selected="updateDatePicker" v-model="parametri.pocetak" style="margin-right: 10px;" placeholder="pocetni termin"></datepicker>
                <p style="margin-right: 10px;" class="d-inline"> i </p>
                <datepicker @selected="updateDatePicker" v-model="parametri.kraj" style="margin-right: 10px;" placeholder="krajnji termin"></datepicker>                
            </div>           
        </div>
        <div class="search-row" >
            <div style="display:inline-flex; " >

            <p style="margin-right: 10px;">Cena: </p>
            <input style="border-radius: 10rem;" type="number" placeholder="Minimalna cena (opciono)" v-model="parametri.minCena">
            <p style="margin-right: 10px;" class="d-inline"> : </p>
            <input style="border-radius: 10rem;" type="number" placeholder="Maksimalna cena (opciono)." v-model="parametri.maxCena">
            </div>
        </div>
        <div class="search-row" >
            <div style="display:inline-flex">
                <p style="margin-right: 10px;">Parametri smestaja: </p>
                <input style="border-radius: 10rem;" type="number" placeholder="Broj ljudi" v-model="parametri.brGosti">
                <input style="border-radius: 10rem;" type="number" placeholder="Minimalna ocena (opciono)" v-model="parametri.minOcena" min="0" max="5">
                <input style="border-radius: 10rem;" type="number" placeholder="Maksimalna ocena (opciono)." v-model="parametri.maxOcena" min="0" max="5">
            </div>
        </div>
        <div class="search-row">
            <div style="dispaly:inline-block">
                <button @click="search()" class="search-button">Pretrazi</button>
                <button @click="ponistiPretragu()" class="anull-button">Ponisti</button>
            </div>    
        </div>

    </div>

    <div >
        <Preporuceni :preporuceni="preporuceni" style="margin-bottom:100px;"></Preporuceni>
    </div>

    <div style="margin-bottom:100px;margin-left:10px;">
        <h2>FILTRIRANJE REZULTATA:</h2>
        <button class="pending-button" @click="showFilters()">Filteri</button>
        <div style="margin-left:30px;" v-if="prikaziFiltere">
            <input type="number" placeholder="Minimalna cena..." style="margin-right:10px;" v-model="cenaMinFilter">
            <input type="number" placeholder="Maksimalna cena..." style="margin-right:10px;" v-model="cenaMaxFilter">
            <label>Istaknuti Host
                <input type="checkbox" @change="istaknutiHostShow()" v-model="istaknutiHost">
            </label>
            <div style="overflow: auto; height:100px; width:20%">
                <table >
                <tbody>
                    <tr v-for="(pgd, index) in pogodnosti" :key="index">
                        <td>
                            {{pgd}}
                        </td>
                        <td>
                            <input v-model="pogodnostiTruthMatrix[index]" @change="pogodnostUkljucena(pgd)" type="checkbox">
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
            
        </div>
    </div>
    <div v-if="smestaj.length > 0">
        <Template v-bind:listaSmestaja="filtriraniSmestaj"></Template> 
       <!-- <p>{{ this.smestajString }}</p>-->
    </div>
    <div v-if="smestaj.length == 0">
        <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA SMESTAJA JE PRAZNA</p>
    </div>
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import dataService from '@/services/dataService';
import PretragaTemplate from './PretragaTemplate.vue'
import Preporuceni from './Preporuceni.vue';
import parserMixin from '@/mixins/mixin';
export default {
    components: {
        datepicker:VueDatePicker,
        Template: PretragaTemplate,
        Preporuceni: Preporuceni
    },
    data() {
        return {
            cenaMinFilter:0,
            cenaMaxFilter:0,
            istaknutiHost:false,
            pogodnosti:[],
            prikaziFiltere:false,
            preporuceni:[],
            prop:{
                brojDana:'1',
                listaSmestaja:[],
                brojGostiju:'1',
            },
            smestaj: [],
            smestajString: '',


            parametri: {
                vlasnik: '',
                adresa: {
                    id: '0',
                    adresa: '',
                    lat: 0,
                    lng: 0
                },
                brGosti: '',
                minCena: '',
                maxCena: '',
                minOcena: '',
                maxOcena: '',
                pocetak: '',
                kraj: '',
            },
            parametriDTO: {
                vlasnik: '',
                adresa: {
                    id: '0',
                    adresa: '',
                    lat: 0,
                    lng: 0
                },
                brGosti: '',
                minCena: '',
                maxCena: '',
                minOcena: '',
                maxOcena: '',
                pocetak: '',
                kraj: '',
            },
            pogodnostiTruthMatrix:[],
        }
    },
    methods: {
        pogodnostUkljucena(pgd){
            console.log("POGODNOST UKLJUCENA: " + pgd);
        },
        istaknutiHostShow(){
            console.log("ISTAKNUTI HOST FILTER: " + this.istaknutiHost);
        },
        showFilters(){
            this.prikaziFiltere = !this.prikaziFiltere;
        },
        getAllPogodnosti(){
            dataService.getAllPogodnosti().then(response => {
                console.log("DOBAVLJENE POGODNOSTI: " + response.data.length);
                console.log(JSON.stringify(response.data));
                this.pogodnosti = response.data;
                for(let i = 0; i < this.pogodnosti.length; i++){
                    this.pogodnostiTruthMatrix.push(false);
                }
            });
        },
        getDani(date1, date2){
            let d1=new Date(date1);
            let d2=new Date(date2);
            return 	Math.floor((d2.getTime()-d1.getTime()) / (1000 * 60 * 60 * 24));

        },
        search() {
            this.prop.brojDana=this.getDani(this.parametriDTO.kraj,this.parametriDTO.pocetak);
            this.prop.brojGostiju=this.parametri.brGosti;
            this.parametriDTO.pocetak = this.parametri.pocetak;
            this.parametriDTO.kraj = this.parametri.kraj;
            this.parametriDTO.adresa = this.parametri.adresa;
            //cena
            if (this.parametri.minCena === '') {
                this.parametriDTO.minCena = -1;
            } else {
                this.parametriDTO.minCena = this.parametri.minCena;
            }
            if (this.parametri.maxCena === '') {
                this.parametriDTO.maxCena = -1;
            } else {
                this.parametriDTO.maxCena = this.parametri.maxCena;
            }
            //gosti
            if (this.parametri.brGosti === '') {
                this.parametriDTO.brGosti = -1;
            } else {
                this.parametriDTO.brGosti = this.parametri.brGosti;
            }
            //ocena
            if (this.parametri.minOcena === '') {
                this.parametriDTO.minOcena = -1;
            } else {
                this.parametriDTO.minOcena = this.parametri.minOcena;
            }
            if (this.parametri.maxOcena === '') {
                this.parametriDTO.maxOcena = -1;
            } else {
                this.parametriDTO.maxOcena = this.parametri.maxOcena;
            }
            dataService.search(this.parametriDTO).then(response => {
                console.log("Pretraga QUERY");
                if (response.data == '') {
                    console.log("ODGOVOR NULL");
                    this.smestaj = [];
                    this.smestajString = [];
                } else {
                    console.log("Broj Pronadjenih:" + response.data.length);
                    this.smestaj = response.data;
                    this.smestajString = JSON.stringify(response.data);
                    this.prop.listaSmestaja=response.data;
                    // this.prop.listaLetova=response.data
                    //this.prop.brKarata=this.parametri.brKarata;
                }
            });
        },
        ponistiPretragu(){
            this.parametri.pocetak='';
            this.parametri.kraj='';
            this.parametri.adresa.adresa='';
            this.parametri.minCena='';
            this.parametri.maxCena='';
            this.parametri.minOcena='';
            this.parametri.maxOcena='';   
            this.parametri.brGosti='';
        },
        getRecommended(id){
            console.log("DOBAVLJANJE PREPORUCENIH");
            dataService.getRecommended(id).then(response => {
                console.log("DOBAVLJENI PREPORUCENI");
                this.preporuceni = response.data;
            });
        },
        filterByPrice(tempSmestaj){
            if(this.cenaMinFilter > 0 && this.cenaMaxFilter <= 0){
                console.log("FILTRIRANJE PO MINIMALNOJ CENI");
                let retLista = tempSmestaj => tempSmestaj.ukCena >= this.cenaMinFilter;
                return retLista;
            }else if(this.cenaMinFilter <= 0 && this.cenaMaxFilter > 0){
                console.log("FILTRIRANJE PO MAKSIMALNOJ CENI");
                let retLista = tempSmestaj =>  tempSmestaj.ukCena <= this.cenaMaxFilter;
                return retLista;
            }else if(this.cenaMinFilter == 0 && this.cenaMaxFilter == 0){
                 return () => true;
            }else{
                console.log("FILTRIRANJE PO OBE CENE");
                let retLista = tempSmestaj => tempSmestaj.ukCena >= this.cenaMinFilter && tempSmestaj.ukCena <= this.cenaMaxFilter;
                return retLista;
            }

        },
        filterByIstaknuti(tempSmestaj){
            let kriterijum = tempSmestaj => tempSmestaj.hostIstaknuti == this.istaknutiHost ? true : false;
            return kriterijum;
        },
        filterByPogodnosti(){
            console.log("FILTER BY POGODNOSTI")
            return tempSmestaj => {
                if(this.pogodnostiTruthMatrix.includes(true)){
                    let tmpPogodnosti = tempSmestaj.pogodnosti.slice();
                    let cntr = tmpPogodnosti.length;
                    if(cntr === 0){
                        return false;
                    }
                    let selektovani = this.pogodnostiTruthMatrix;
                    let matches = 0;
                    for(let i = 0; i < cntr; i++){
                        console.log("BROJ PROLAZA: " + i)
                        let pgd = tmpPogodnosti[i];
                        let isSelected = selektovani[this.pogodnosti.indexOf(pgd)];
                        
                        if(isSelected){
                            matches++;
                        }
                    }
                    let brojSelektovanih = selektovani.reduce((count, currentValue) => {
                        if(currentValue === true){
                            return count + 1;
                        }else{
                            return count;
                        }
                    }, 0);
                    console.log("BROJ SELEKTOVANIH: " + brojSelektovanih)
                    return matches === brojSelektovanih;
                }
                return true;
            }
        }
    },
    created() {
        this.search();
        this.getAllPogodnosti();
        let userId = null;
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            userId = userObj.id;
            console.log("ID KORISNIKA: " + this.userId);
        }
        if(userId != null){
            this.getRecommended(userId);
        }
        
    },
    computed: {
        filtriraniSmestaj(){
            // let retList = this.smestaj.filter(this.filterByPrice());
            return this.smestaj.filter(this.filterByPrice()).filter(this.filterByIstaknuti()).filter(this.filterByPogodnosti());
            // return this.smestaj.filter(smTemp => {
            //     console.log("BROJ SMESTAJA: " + this.smestaj.length);
            //     let tmpList = smTemp.ukCena >= this.cenaMinFilter && smTemp.ukCena <= this.cenaMaxFilter;
            //     console.log("OSTAJE U LISTI: " + tmpList)
            //     return tmpList;
            // });
        }
    }

}
</script>
<style scoped>
.search-button {
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width: 150px;
    font-size: 15px;
    padding: 10px;
    margin: 10px;
    cursor: pointer;
    transition-duration: 0.4s;
}

.search-button:hover {
    background-color: #0275d8;
    color: white;
}

.anull-button:hover {
    background-color: #0275d8;
    color: white;
}

.anull-button {
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width: 150px;
    font-size: 15px;
    padding: 10px;
    margin: 10px;
    cursor: pointer;
    transition-duration: 0.4s;
}

.filter-button {
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    font-weight: 500;
    width: 100px;
    font-size: 15px;
    padding: 10px;
    margin: 10px;
    cursor: pointer;
    transition-duration: 0.4s;
}

.filter-button:hover {
    background-color: #0275d8;
    color: white;
}

.search-row {
    margin-bottom: 15px;
    margin-top: 15px;
    margin-left: 20px;
}

.itemTmp {
  width: 15%;
  box-sizing: border-box;
  padding: 10px;
  float: left;
}
.pending-button {
    border-radius: 6rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width: 150px;
    font-size: 15px;
    padding: 10px;
    margin: 0px;
    cursor: pointer;
    transition-duration: 0.4s;
    margin-bottom: 20px;
}

.pending-button:hover {
    background-color: #0275d8;
    color: white;
}
</style>