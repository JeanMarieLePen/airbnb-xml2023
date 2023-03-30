<template>
    <div style="margin-left:20px;margin-bottom:30px;">
        <h1>PRETRAGA LETOVA</h1>
    </div>
    <div style="width:60%;">
        <div class="search-row">
            <div style="display:inline-flex;">
                <p style="margin-right: 10px;">Parametri lokacije: </p>
                <input style="border-radius: 10rem;" type="text" placeholder="Unesite mesto polaska" v-model="parametri.pocetnaLok.adresa"/>
                <input style="border-radius: 10rem;" type="text" placeholder="Unesite odrediste" v-model="parametri.krajanjaLok.adresa"/>
               
            </div>
        </div>
        <div class="search-row">
            <div style="display:inline-flex; " >
                <p style="margin-right: 10px;">Termini za polazak izmedju  </p>
                <datepicker @selected="updateDatePicker" v-model="parametri.pocetak" style="margin-right: 10px;" placeholder="pocetni termin"></datepicker>
                <p style="margin-right: 10px;" class="d-inline"> i </p>
                <datepicker @selected="updateDatePicker" v-model="parametri.kraj" style="margin-right: 10px;" placeholder="krajnji termin"></datepicker>                
            </div>           
        </div>
        <div class="search-row" >
            <div style="display:inline-flex">
                <p style="margin-right: 10px;">Parametri karte: </p>
                <input style="border-radius: 10rem;" type="number" placeholder="Unesite broj karata..." v-model="parametri.brKarata">
                <input style="border-radius: 10rem;" type="number" placeholder="Minimalna cena karte..." v-model="parametri.minCena">
                <input style="border-radius: 10rem;" type="number" placeholder="Maksimalna cena karte cena karte..." v-model="parametri.maxCena">
            </div>
        </div>
        <div class="search-row">
            <div style="dispaly:inline-block">
                <button @click="search()" class="search-button">Pretrazi</button>
                <button @click="ponistiPretragu()" class="anull-button">Ponisti</button>
            </div>    
        </div>
    </div>
    <div  v-if="letovi.length>0">
            <LetTemplate v-bind:listaLetova="prop"></LetTemplate>     
    </div>
    <div v-if="letovi.length==0">            
        <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA LETOVA JE PRAZNA</p>
    </div>


   <!-- <p>{{this.letoviString}}</p> -->
</template>
<script>
import { defaultMaxListeners } from 'events';
import VueDatePicker from '@vuepic/vue-datepicker';
import dataService from '@/services/dataService';
import LetoviTemplate from '../overviews/FlightsTableSearch.vue';
import moment from 'moment';

    export default{
        components:{
            datepicker:VueDatePicker,
            LetTemplate:LetoviTemplate
        },
        data(){
            return{
                currentSort : 'nazivEntiteta',
                currentSortDir : 'asc',
                letovi:[],
                prop:{
                    listaLetova:[],
                    brKarata:''
                },
                letoviString:'',
                parametri:{
                    pocetak:'',
                    kraj:'',
                    brKarata: '',
                    minCena:'',
                    maxCena:'',
                    pocetnaLok:{
                        id:'0',
                        adresa:'',
                        lat:0,
                        lng:0
                    },
                    krajanjaLok:{
                        id:'0',
                        adresa:'',
                        lat:0,
                        lng:0
                    }
                },
                parametriDTO:{
                    pocetak:'',
                    kraj:'',
                    brKarata: '',
                    minCena:'',
                    maxCena:'',
                    pocetnaLok:{
                        id:'0',
                        adresa:'',
                        lat:0,
                        lng:0
                    },
                    krajanjaLok:{
                        id:'0',
                        adresa:'',
                        lat:0,
                        lng:0
                    }
                }


            }
        },
        methods:{
            
            search(){
                this.parametriDTO.pocetak=this.parametri.pocetak;
                this.parametriDTO.kraj=this.parametri.kraj;
                this.parametriDTO.pocetnaLok=this.parametri.pocetnaLok;
                this.parametriDTO.krajanjaLok=this.parametri.krajanjaLok;
                if(this.parametri.minCena===''){
                    this.parametriDTO.minCena=-1;
                }else{
                    this.parametriDTO.minCena = this.parametri.minCena;
                }

                if(this.parametri.maxCena===''){
                    this.parametriDTO.maxCena=-1;
                }else{
                    this.parametriDTO.maxCena = this.parametri.maxCena;
                }

                if(this.parametri.brKarata===''){
                    this.parametriDTO.brKarata=-1;
                }else{
                    this.parametriDTO.brKarata = this.parametri.brKarata;
                }
                console.log(JSON.stringify(this.parametriDTO));
                
                dataService.searchQuery(this.parametriDTO).then(response =>{
                    console.log("Pretraga QUERY");
                    if(response.data == ''){
                        console.log("ODGOVOR NULL");
                        this.letovi = [];
                        this.letoviString=[];
                    }else{
                        console.log("Broj Pronadjenih:"+response.data.length);
                        this.letovi = response.data;
                        this.letoviString=JSON.stringify(response.data);
                        this.prop.listaLetova=response.data
                        this.prop.brKarata=this.parametri.brKarata;
                    }
                });
            },
            ponistiPretragu(){
                this.parametri.brKarata='';
                this.parametri.minCena='';
                this.parametri.maxCena='';
                this.parametri.pocetak='';
                this.parametri.kraj='';
                this.parametri.pocetnaLok.adresa='';
                this.parametri.krajanjaLok.adresa=''
            }
        },
        created(){
            this.search();

        },
        computed:{}
    }

</script>

<style scoped>

.search-button{
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width:150px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}
.search-button:hover{
    background-color: #0275d8;
    color: white;
}

.anull-button:hover{
    background-color: #0275d8;
    color: white;
}

.anull-button{
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width:150px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}

.filter-button{
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    font-weight: 500;
    width:100px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}
.filter-button:hover{
    background-color: #0275d8;
    color: white;
}

.search-row{
    margin-bottom: 15px;
    margin-top:15px;
    margin-left:20px;
    
    
}
</style>