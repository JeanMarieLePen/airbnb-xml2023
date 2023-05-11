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

    <div v-if="smestaj.length > 0">
        <!-- <LetTemplate v-bind:listaLetova="prop"></LetTemplate>  -->
        <p>{{ this.smestajString }}</p>
    </div>
    <div v-if="smestaj.length == 0">
        <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA SMESTAJA JE PRAZNA</p>
    </div>
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import dataService from '@/services/dataService';

export default {
    components: {
        datepicker:VueDatePicker,

    },
    data() {
        return {
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
            }
        }
    },
    methods: {
        search() {
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
                    this.letovi = [];
                    this.letoviString = [];
                } else {
                    console.log("Broj Pronadjenih:" + response.data.length);
                    this.smestaj = response.data;
                    this.smestajString = JSON.stringify(response.data);
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
        }
    },
    created() {
        this.search()
    },
    copmuted: {}

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
</style>