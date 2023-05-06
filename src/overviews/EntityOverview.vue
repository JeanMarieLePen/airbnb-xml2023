<template>
    <div style="width: 50%;">
        <div style="margin-left:10px;margin-bottom:100px;">
            <h1>Pregled smestaja ID-a: {{smestaj.id}}</h1>
            <table style="margin-left: 10px" id="table_overview">
                <tr>
                    <td>Vlasnik:</td>
                    <td>
                        <input type="text" readonly v-model="smestaj.vlasnik" />
                    </td>
                    </tr>
                    <tr>
                        <td>Adresa:</td>
                        <td>
                            <input type="text" readonly v-model="smestaj.adresa.adresa" />
                        </td>
                    </tr>
                    <tr>
                        <td>Cena:</td>
                        <td>
                            <input type="text" readonly v-model="smestaj.cenovnik.cena" />
                        </td>
                    </tr>
                    <tr>
                    <td>Ocena:</td>
                    <td>
                        <starrating read-only v-model="smestaj.ocena" />
                    </td>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <button class="rezervisi-button" @click="makeReservation()">Rezervisi</button>
                    </td>
                </tr>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <yandex-map
                                :settings="settings"
                                :coordinates="koordinate"
                                zoom="5"
                                style="width: 100%; height: 600px"
                                language="en-US"
                            >
                                <!-- <ymap-marker
                                v-for="(tmp, index) in lokacija"
                                :key="index"
                                :marker-id="index"
                                :coords="tmp.koordinateTemp"
                                :balloon-template="balloonTemplate"
                                >
                                </ymap-marker> -->
                            </yandex-map>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
    <!-- <div style="width:100%;">
        <comment-section></comment-section>
    </div> -->
    
</template>



<script>
import StarRating from 'vue-star-rating'
import dataService from "../services/dataService"
import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";
import "regenerator-runtime/runtime";
import CommentSection from '../components/CommentSection.vue'

import parserMixin from '@/mixins/mixin'

export default {
    components:{
        starrating:StarRating,
        YandexMap, YandexMarker,
        CommentSection,
    }, 
    data(){
        return{
            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
            idEntiteta: -1,
            smestaj:{
                adresa:{
                    adresa:''
                }
            },
            userObj:{

            },
            //mapa
            koordinate:[],

            user:{
                username:'',
            },
            role:'',
            userId:-1,
        }
    },
    methods:{
        makeReservation(){
            
        },
        getSmestaj(){
            dataService.getSmestaj(this.idEntiteta).then(response => {
                console.log("DOBAVLJEN ENTITET");
                this.smestaj = response.data;
            });
        },
    },
    async created(){
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
            console.log("ID VLASNIKA JE: " + this.userId);
            this.smestaj.vlasnikId = this.userId;

            this.idEntiteta = this.$route.params.id;
            await dataService.getSmestaj(this.idEntiteta).then(response => {
                console.log("DOBAVLJEN SMESTAJ");
                this.smestaj = response.data;
                // this.koordinate.push(this.smestaj.adresa.lat);
                // this.koordinate.push(this.smestaj.adresa.lng);
            });

            
            navigator.geolocation.getCurrentPosition((geolocation) => {
                this.koordinate.push(geolocation.coords.latitude);
                this.koordinate.push(geolocation.coords.longitude);
            });
            console.log("KOORDINATE KORISNIKA: " + JSON.stringify(this.koordinate));
        }
    },
    async mounted(){
        await loadYmap({ ...this.settings, format: "json", debug: true});
        // await this.getEntity();
        // await this.getCoordsFromAdresa();
    },
    computed:{
        balloonTemplate() {
            return `
                <h1 class="red">Entitet!</h1>
                <p>Adresa centra: ${this.entitet.adresa}</p>
                <p>Ocena centra: ${this.entitet.ocena}</p>
                <img src="http://via.placeholder.com/350x150">`;
        },
    }
}
</script>



<style scoped>
#table_overview {
  font-size: 20px;
  font-style: oblique;
  font-weight: 600;
}
#table_overview input {
  width: 100%;
}
#table_overview select {
  width: 100%;
}

#table_overview tr {
  margin-top: 5px;
  margin-bottom: 5px;
}
#table_overview tr:nth-child(even) {
  background-color: #198754;
}
#table_overview td:first-child {
  width: 20%;
}
#table_overview td:last-child {
  width: 200px;
}

.rezervisi-button {
  color: #5cb85c;
  border: 2px solid #5cb85c;
  background-color: white;
  font-weight: 500;
  width: 200px;
  font-size: 15px;
  padding: 10px;
  margin: 10px;
  cursor: pointer;
  transition-duration: 0.4s;
}
.rezervisi-button:hover {
  /* background-color: #f0ad4e; */
  /* color: white; */
  color: #198754;
  font-size: 20px;
  font-weight: 200px;
}
</style>