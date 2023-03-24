<template>
    <div style="width: 50%;">
        <div style="margin-left:10px;margin-bottom:100px;">
            <h1>Podaci o entitetu</h1>
            <table style="margin-left: 10px" id="table_overview">
                <tr>
                    <td>Naziv:</td>
                    <td>
                        <input type="text" readonly v-model="entity.naziv" />
                    </td>
                    </tr>
                    <tr>
                    <td>Adresa:</td>
                    <td>
                        <input type="text" readonly v-model="entity.adresa" />
                    </td>
                    </tr>
                    <tr>
                    <td>Radno vreme:</td>
                    <td>
                        <input type="text" readonly v-model="radnoVreme" />
                    </td>
                    </tr>
                    <tr>
                    <td>Ocena:</td>
                    <td>
                        <starrating read-only v-model="entity.ocena" />
                    </td>
                    </tr>
                    <tr>
                    <td>Krvna grupa:</td>
                    <td>
                        <select @change="selectGroup()" v-model="selectedGroup">
                        <option value="1">A grupa</option>
                        <option value="2">AB grupa</option>
                        <option value="3">B grupa</option>
                        <option value="4">Nulta grupa</option>
                        </select>
                    </td>
                    </tr>
                    <tr>
                    <td colspan="2">
                        <input
                        style="width: 100%"
                        placeholder="Stanje zaliha odabrane grupe izrazeno u litrima..."
                        v-model="bloodReserve"
                        type="text"
                        readonly
                        />
                    </td>
                    </tr>
                    <tr>
                    <td colspan="2">
                        <div v-if="entity.opis">
                        <textarea
                            style="width: 100%; height: 150px; overflow: auto"
                            v-text="entity.opis"
                            readonly
                        />
                        </div>
                        <div
                        v-if="!entity.opis"
                        v-text="'Kratak opis medicinskog centra...'"
                        ></div>
                    </td>
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
    <div style="width:100%;">
        <comment-section></comment-section>
    </div>
    
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
            entity:{
                
            },
            userObj:{

            },
            //mapa
            koordinate:[],
        }
    },
    methods:{
        makeReservation(){
            
        },
        getEntity(){
            dataService.getEntity(this.idEntiteta).then(response => {
                console.log("DOBAVLJEN ENTITET");
                this.entity = response.data;
            });
        },
        async getCoordsFromAdresa(){
            // return dataService.getMedCentarByWorkerId(this.userId).then(response => {
            //     this.idCentraNabavljac = response.data.id;
            //     console.log("DOBAVLJEN MEDCENTAR U KOM RADI RADNIK");
            //     this.adresaNabavljaca = response.data.adresa;
            //     console.log("ADRESA OD JE: " + this.adresaNabavljaca)
            //     ymaps.geocode(this.adresaNabavljaca).then((res) => {
            //     console.log("ADRESA: " + this.adresaNabavljaca);
            //     let tmpObjekat = {
            //         koordinateTemp: res.geoObjects.get(0).geometry.getCoordinates(),
            //     };
            //     this.orderForm.lokacijaDo.lat = tmpObjekat.koordinateTemp[0];
            //     this.orderForm.lokacijaDo.lng = tmpObjekat.koordinateTemp[1];
            //     console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
            //     // this.lokacije.push(tmpObjekat);
            //     this.lokacija.push(tmpObjekat);
            //     console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            //     });
            // });
        },
    },
    created(){
        console.log("AAAA")
        // parserMixin.methods.checkLoginStatus();
        // this.userObj = parserMixin.methods.parseXmlJwt();
        // console.log("ID KORISNIKA: " + this.userObj.id)
        // this.idEntiteta = this.$route.params.id;
        navigator.geolocation.getCurrentPosition((geolocation) => {
            this.koordinate.push(geolocation.coords.latitude);
            this.koordinate.push(geolocation.coords.longitude);
        });
        console.log("KOORDINATE KORISNIKA: " + JSON.stringify(this.koordinate));
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
                <img src="http://via.placeholder.com/350x150">
                    `;
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