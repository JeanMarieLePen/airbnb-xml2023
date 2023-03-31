<template>
    <div>
        <div>
            <h2>Kreiranje novog entiteta</h2>
            <p>Popunite trazene karakteristike kako biste kreirali entitet</p>
        </div>
        <div>
            <div style="width:50%">
                <table class="table">


                    <tr>
                        <td>Broj</td>
                        <td><input class="input-class" v-model="flight.brojLeta" type="text" placeholder="Unesite broj leta..." ></td>
                    </tr>
                     <tr>
                        <td>Mesto polaska</td>
                        <td><input class="input-class" v-model="flight.lokOd.adresa" type="text" placeholder="Unesite mesto polaska..." ></td>
                    </tr>
                    <tr>
                        <td>Mesto dolaska</td>
                        <td><input class="input-class" v-model="flight.lokDo.adresa" type="text" placeholder="Unesite mesto dolaska..." ></td>
                    </tr>
                    <tr>
                        <td>Maksimalni kapacitet</td>
                        <td><input v-model="flight.kapacitet" class="input-class" type="text" placeholder="Unesite mesto dolaska..." ></td>
                    </tr>
                    <tr>
                        <td>Cena karte</td>
                        <td><input v-model="flight.cena" class="input-class" type="text"  placeholder="Unesite mesto dolaska..." ></td>
                    </tr>
                    <tr>
                        <td>Datum i vreme polaska</td>
                        <td>
                            <datepicker @selected="updateDatePicker" v-model="flight.datumIVreme">

                            </datepicker>
                        </td>
                    </tr>
                    <tfoot>
                        <tr style="background:white">
                            <td colspan="2">
                                <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success"></div>
                            <div v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-warning"></div>
                            </td>
                        </tr>
                        <tr>
                            
                            <td colspan="2" class="text-center">
                                <button @click="createNewFlight()">Kreiraj</button>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>



</template>

<script>
import parserMixin from '@/mixins/mixin' 
import dataService from '../services/dataService'
import VueDatePicker from '@vuepic/vue-datepicker';
import moment from 'moment'
import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";
export default {
    components:{
        datepicker:VueDatePicker,
        YandexMap, YandexMarker,
    },
    data(){
        return{
            //Polja u Klasi Let
            // private String id;
	
            // private Adresa lokOd;
            // private Adresa lokDo;
            
            // private String brojLeta;
            // private int kapacitet;
            // private double cena;
            // private LocalDateTime datumIVreme;
            // @DBRef
            // @Nullable
            // private Collection<RegKor> listaPutnika;
            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
            messages:{
                successMessage:'',
                errorMessage:'',
                
                errorLokOd:'',
                errorLokDo:'',
                errorTermin:'',
                errorBrLet:'',
                errorKapacitet:'',
                errorCena:'',
            },
            flight:{
                lokOd: new Object(),
                lokDo: new Object(),
                //ovo bi trebalo generisati automatski al ajd
                brojLeta: -1,
                kapacitet: -1,
                cena: -1,
                datumIVreme: new Date(),
            },
            lokacija: new Object(),
        }
    },
    async mounted(){
        await loadYmap({ ...this.settings, format: "json", debug: true});
    },
    created(){
    },
    methods:{
        updateDatePicker(val1){
            console.log("VAL1: "  + val1);
            let tempDatum = moment(val1).format('YYYY-MM-DD');
            console.log("TEMP DATUM: "  + tempDatum);
            this.flight.datumIVreme = tempDatum;
        },
        async createNewFlight(){
            console.log("ODREDJIVANJE KOORDINATA");
            await this.getCoordsFromAdresa(this.flight.lokOd.adresa);
            this.flight.lokOd.lat = this.lokacija.lat;
            this.flight.lokOd.lng = this.lokacija.lng;
            await this.getCoordsFromAdresa(this.flight.lokDo.adresa);
            this.flight.lokDo.lat = this.lokacija.lat;
            this.flight.lokDo.lng = this.lokacija.lng;
            console.log("RUTA: " + JSON.stringify(this.flight.lokOd));
            dataService.createNewFlight(this.flight).then(response => {
                console.log("KREIRAN LET");
                if(response.status === 200){
                    this.messages.successMessage = '<h4>Uspesno dodat let</h4>'
                    setTimeout(() => {
                        this.messages.successMessage = '';
                        this.$router.push(`/dash`);
                    }, 3500);
                }else{
                    this.messages.errorMessage = '<h4>Doslo je do greske!</h4>'
                    setTimeout(() => {
                        this.messages.errorMessage = '';
                    });
                }
            });
        },
        async getCoordsFromAdresa(adresa){
            console.log("ADRESA: " + adresa)
            return ymaps.geocode(adresa).then((result) => {
                let locationCoords = {
                    coords: result.geoObjects.get(0).geometry.getCoordinates(),
                };
                this.lokacija.lat = locationCoords.coords[0];
                this.lokacija.lng = locationCoords.coords[1];
                console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            });
        }
    }
    
}
</script>


<style scoped>
.table{
    font-family: Arial, Helvetica, sans-serif;
    margin-left: 20px;
}
.table tr:nth-child(even){
    /* background-color: aquamarine; */
}
.table td:nth-child(even){
    background: white;
}
.table tr:nth-child(odd){
    color: white;
    background-color: firebrick;
}
.table td:nth-child(1){
    width:30%;
    font-size: 20px;
    font-weight: 600;
}
.table td:nth-child(2){
    width:100%;
}
.table td>input{
    width:100%;
}
</style>