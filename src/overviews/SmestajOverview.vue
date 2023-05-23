<template>
    <div style="width:50%" >
        <div style="margin-left:30px">
            <h1>Pregled Smestaja[ID:{{smestaj.id}}]</h1>
            <div style="display:flex; width:100%">
                <div>
                    <!-- <button @click="goToReservation()" v-show="canMakeReservation" style="font-weight:500" class="btn btn-primary">Rezervisi</button> -->
                    <button v-show="role !== 'HOST'" @click="goToReservation()" style="font-weight:500; margin-right: 5px;" class="btn btn-success">Rezervisi</button>
                    <button v-show="role === 'HOST'" @click="editSmestaj(smestaj.id)" style="font-weight:500; margin-right: 5px;" class="btn btn-success">Izmeni</button>
                    <button @click="goToProfile(smestaj.vlasnikId)" style="font-weight:500" class="btn btn-success">Pogledaj profil oglasivaca</button>
                    
                    <div style="display:flex-inline" v-if="canRate">
                        <h4>Vasa ocena: </h4>
                        <star-rating v-model:rating="ocenaSmestaja.ocena" v-show="canRate" :star-size="40" style="margin-left:10px;display:inline-block"></star-rating>
                        <button @click="rateSmestaj()">Ocenite</button>
                    </div>
                    
                                           
                </div>
            </div>
            
            
        </div>
        <div style="margin-left:30px">
            <table>
                <thead>
                    <tr>
                        <th style="font-family:Verdana; font-style:italic; font-weight:1">Pregled detalja o smestaju</th>
                    </tr>
                </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div style="width:400px;height:600">
                                    <vueperslides v-if="smestaj.slike.length > 0" fade fixed-height="600px">
                                        <vueperslide v-for="(slide, i) in smestaj.slike" :key="i">
                                            <template v-slot:content>
                                                <img :src="slide" style="width:400px;height:600px">
                                            </template>
                                        </vueperslide>
                                    </vueperslides>
                                </div>
                            </td>
                            <td style="vertical-align: top;">
                                <div class="container" style="margin:20px ">
                                    <table>
                                        <tr>
                                            <td>
                                                <label>Vlasnik ID:</label>
                                            </td>
                                            <td>
                                                <label>{{smestaj.vlasnikId}}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Adresa:</label>
                                            </td>
                                            <td>
                                                <label v-if="smestaj.adresa">{{smestaj.adresa.adresa}}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Pregled termina: </label>
                                            </td>
                                            <td>
                                                <div>
                                                    <datepicker :min-date="new Date()" no-disabled-range :disabled-dates="disabledDates" read-only></datepicker>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;">
                                                <label>Ocena: </label>
                                            </td>
                                            <td>
                                                <star-rating :star-size="20" read-only style="margin-left:10px;display:inline-block"  v-model:rating="smestaj.prosecnaOcena"></star-rating>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;">
                                                <label>Pogodnosti: </label>
                                            </td>
                                            <td>
                                                <ul style="width: 200px; min-height:50px; max-height: 100px; overflow: auto">
                                                    <li v-for="(tempPogodnost, index) in smestaj.pogodnosti" v-bind:key="index">{{tempPogodnost}}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        
                                    </table>
                                </div>
                               
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="2">
                            <yandex-map
                                :settings="settings"
                                :coordinates="koordinate"
                                :zoom=5
                                style="width: 100%; height: 600px"
                                language="en-US"
                            >
                                <yandex-marker
                                v-for="(tmp, index) in lokacija"
                                :key="index"
                                :marker-id="index"
                                :coordinates="tmp.koordinateSmestaja"
                                :balloon-template="balloonTemplate"
                                >
                                </yandex-marker>
                            </yandex-map>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div id="comment-section" style="margin-left:30px">
            <div>
                <div  v-for="(tempComment, index) in smestaj.listaOcena" v-bind:key="index" class="comments-box">
                <div>
                    <p class="author">
                    {{tempComment.gost}} 
                    <star-rating :star-size="20" read-only style="margin-left:10px;display:inline-block"  v-model:rating="tempComment.ocena"></star-rating>
                    </p>
                </div>
                <p style="font-weight:200; font-style:italic">Kreiran: {{tempComment.datum}}</p>
                <!-- <div id="like-section">
                    <button @click="like()">
                        <div>
                            <img src="../assets/like.png" style="max-width:100%; max-height:100%;" />
                        </div>
                    </button>
                    <button @click="dislike()">
                        <div>
                            <img src="../assets/dislike.png" style="max-width:100%; max-height:100%;" />
                        </div>
                    </button>
                </div> -->
                </div>
                <div style="margin-left: 200px;margin-top:100px" v-show="smestaj.listaOcena.length == 0 ">
                    <h1>Trenutno nema ocena</h1>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import dataService from '../services/dataService'
import moment from 'moment'

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'

import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";
import "regenerator-runtime/runtime";

import parserMixin from '@/mixins/mixin';
import StarRating from 'vue-star-rating';

import { addDays, subDays } from 'date-fns';

export default{
    async mounted(){
        await loadYmap({ ...this.settings, format: "json", debug: true});
        await this.getCoords(this.smestaj.adresa.adresa);
    },
    async created(){
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
            console.log("ID VLASNIKA JE: " + this.userId);
            // this.smestaj.vlasnikId = this.userId;

            this.idEntiteta = this.$route.params.id;
            await dataService.getSmestaj(this.idEntiteta).then(response => {
                console.log("DOBAVLJEN SMESTAJ: " + JSON.stringify(response.data));
                console.log("BROJ NEDOSTUPNIH: " + response.data.nedostupni.length);
                this.smestaj = response.data;
                let tempSlike = [];
                for(let i = 0; i < this.smestaj.slike.length; i++){
                    tempSlike.push('data:image/png;base64,' + this.smestaj.slike[i]);
                }
                this.smestaj.slike = tempSlike;

                let tempTermini = [];
                for(let i = 0; i < this.smestaj.nedostupni.length; i++){
                    tempTermini.push({
                        pocetak: this.smestaj.nedostupni[i].pocetak,
                        kraj: this.smestaj.nedostupni[i].kraj
                    });
                }

                this.smestaj.nedostupni = [];
                this.smestaj.nedostupni = tempTermini;

                let nedostupniTermini = [...this.smestaj.nedostupni]
                for(let i = 0; i < nedostupniTermini.length; i++){
                    console.log("UCITAVANJE BR: " + i);
                    this.ucitavanjeTermina(nedostupniTermini[i].pocetak, nedostupniTermini[i].kraj);
                }
                
                // let tmpObj = {
                //     koordinateSmestaja: [this.smestaj.adresa.lat, this.smestaj.adresa.lng]
                // };
                // this.lokacija.push(tmpObj);
            });

            await dataService.canGiveRating(this.userId, this.$route.params.id).then(response => {
                this.canRate = response.data;
                console.log("Can give rating : "+this.canRate);
                if(this.canRate===true){
                    for(let i =0 ; i<this.smestaj.listaOcena.length;i++){
                        console.log("poredjenje ids : "+this.smestaj.listaOcena[i].gost+" : "+this.userId.substr(1,this.userId.length-2))
                        if(this.smestaj.listaOcena[i].gost===this.userId.substr(1,this.userId.length-2)){
                            this.ocenaSmestaja.ocena=this.smestaj.listaOcena[i].ocena;
                            break;
                        }
                    }
                }
            });
            // await this.getCoords(this.smestaj.adresa.adresa);
            navigator.geolocation.getCurrentPosition((geolocation) => {
                this.koordinate.push(geolocation.coords.latitude);
                this.koordinate.push(geolocation.coords.longitude);
            });
            console.log("KOORDINATE KORISNIKA: " + JSON.stringify(this.koordinate));    
        }
        
    },
    components:{
        datepicker:VueDatePicker,
        vueperslides : VueperSlides,
        vueperslide :VueperSlide,
        YandexMap, YandexMarker,
        StarRating,
    },
    methods:{
        rateSmestaj(){
            console.log("OCENI SMESTAJ")
            console.log("DATA OCENA: " + this.ocenaSmestaja.ocena);
            //this.ocenaSmestaja.ocena = rating;
            this.ocenaSmestaja.datum = moment(new Date()).format("YYYY-MM-DD");
            dataService.giveRatingToSmestaj(this.userId, this.$route.params.id, this.ocenaSmestaja).then(response => {
                console.log("uspesno ocenjeno");
            });
        },
        async getCoords(adresa){
            return ymaps.geocode(adresa).then((res) => {
                console.log("ADRESA: " + adresa);
                let tmpObjekat = {
                    koordinateSmestaja: res.geoObjects.get(0).geometry.getCoordinates(),
                };
                console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
                // this.lokacije.push(tmpObjekat);
                this.lokacija.push(tmpObjekat);
                console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            });
        },
        ucitavanjeTermina(terminPocetak, terminKraj){
            console.log("UCITAVANJE TERMINA");
            let datum1 = new Date(terminPocetak);
            let datum2 = new Date(terminKraj);
            console.log("POCETAK: " + JSON.stringify(datum1));
            console.log("KRAJ: " + JSON.stringify(datum2));
            const diffTime = Math.abs(datum2 - datum1);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
            let brDana = diffDays;
            console.log("BROJ DANA JE: " + brDana);

            let tempDatumi = [];
            let disblDatesConcat = this.disabledDates;
            for(let i = 0; i <= brDana; i++){
                let tmpDate = addDays(new Date(datum1), i)
                tempDatumi.push(tmpDate);
            }
            for(let i = 0; i < tempDatumi.length; i++){
                disblDatesConcat.push(tempDatumi[i]);
            }
            this.disabledDates = disblDatesConcat;
            console.log("NEDOSTUPNI disblDatesConcat: " + JSON.stringify(disblDatesConcat));
            console.log("DISABLED DATES[UCITAVANJE]: " + JSON.stringify(this.disabledDates));
        },
        goToProfile(idVlasnika){
            this.$router.push(`/ownerProfile/${idVlasnika}`);
        },
        goToReservation(){
            this.$router.push(`/reservation/${this.$route.params.id}`);
        },
        editSmestaj(id){
            this.$router.push(`/editSmestaj/${id}`);
        }
    },
    mixins:[parserMixin],
    computed:{
       canMakeReservation(){
            if(this.role === "GUEST"){
                if(this.brPenala >= 3){
                    return false;
                }else{
                    return true;
                }
                
            }else{
                return false;
            }
        },
        cenaPoDanu(){
            //racunanje cene po danu
        },
        balloonTemplate() {
            return `
                <h1 class="red">Entitet!</h1>
                <p>Adresa centra: ${this.smestaj.adresa.adresa}</p>
                
                <img src="http://via.placeholder.com/350x150">`;

                // <p>Ocena centra: ${this.a.ocena}</p>
        },
        
    },
    data(){
        return{
            ocenaSmestaja:{
                ocena:0,
                datum:null,
            },
            canRate: false,
            lokacija:[],
            reservationForbidden:false,
            koordinate:[],
            disabledDates:[],
            user:{
                username:'',
            },
            role:'',
            userId:-1,

            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
            slike:[],
            smestaj: {

            },
        }
    }
}
</script>

<style scoped>
/* #small_table td:last-child{
    align-items: center;
} */
ul{
    display:inline-block;
}
tr:nth-child(even){
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: #f2f2f2;
}
td:first-child{
    font-weight: bold;
}
td:last-child{
    width: 100%;
}
#comment-section{
    margin-bottom:100px;
    width:60%;
}
.comments-box{
    width: 90%;
    padding: 20px 0;
    border-bottom: 1px solid #000;
}
.author{
    margin: 10px 0;
    font-weight: bold;
}
#like-section button{
    height:50px;
    width: 50px;
    text-align: center;
    background: white;
}
</style>