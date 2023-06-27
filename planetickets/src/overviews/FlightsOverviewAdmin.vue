<template>
    <div style="margin-left:20px;">
        <div>
            <h1>Pregled letova</h1>
        </div>
        <div>
            <section style="margin-left: 10px; margin-bottom:100px;">
            <div>
                <div class="row">
                    <div class="col-md-4" v-bind:key="index" v-for="(tempFlight, index) in flights">
                        <div style="width:70%; margin-bottom:100px;" class="card bg-success text-white text-center p-3">
                            <div class="card-body" style="font-weight:700px; height:400px; font-size:20px; font-style:italic;">
                                <h4 class="card-title">Broj leta: {{tempFlight.brojLeta}}</h4>
                                <p class="card-text">Kapacitet: {{tempFlight.kapacitet}}</p>
                                <p class="card-text">Datum i vreme: {{formatDate(tempFlight.datumIVreme)}}</p>
                                <p class="card-text">Mesto polaska: {{tempFlight.lokOd}}</p>
                                <p class="card-text">Mesto dolaska: {{tempFlight.lokDo}}</p>
                                <p class="card-text">Slobodnih mesta: {{tempFlight.brSlobMesta}}</p>
                                <p class="card-text">Cena: {{tempFlight.cena}}</p>
                                <!-- <p class="card-text">ID: {{tempFlight.id}}</p> -->
                            </div>
                            <div style="margin-bottom:30px;margin-top:50px;" class="card-footer bg-white text-white text-center">
                                <button style="margin-left:30px;" v-on:click="flightDetails(tempFlight.id)" class="btn btn-primary">Detalji</button>
                                <button v-if="tempFlight.kapacitet === tempFlight.brSlobMesta" @click="removeFlight(tempFlight.id)" class="btn btn-danger" style="margin-left:5px;">Ukloni</button> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </div>
    </div>    
</template>


<script>
import dataService from '@/services/dataService'
import parserMixin from '@/mixins/mixin'
import {ref} from 'vue';
import moment from 'moment';
export default {
    setup() {
        var flights = ref([]);
        const getAllFlights = () => {
            return dataService.getAllFlights().then(response => {
                console.log("DOBAVLJENI LETOVI");
                flights.value = response.data;
                console.log("BROJ LETOVA: " + flights.value.length);
                console.log(JSON.stringify(flights));
            });
        }
        const formatDate = (date) => {
            let tempDate = moment(date).format('MMMM Do YYYY, h:mm:ss a')
            return tempDate;
        }
        return {flights, getAllFlights, formatDate};
    },
    methods:{
        flightDetails(flightId){
            // return this.$router.push(`/flight/${flightId}`);
            return this.$router.push(`/overview/${flightId}`);
        },
        removeFlight(flightId){
            console.log("ID USERA: " + this.userObj.id)
            dataService.ukloniLet(flightId).then(response => {
                this.getAllFlights();
            });
        }
    },
    created(){
        parserMixin.methods.checkLoginStatus();
        this.userObj = parserMixin.methods.parseXmlJwt();
        this.getAllFlights();
        console.log("BROJ LETOVA: " + this.flights.length);
    },
    
    data(){
        return{
            // flights:[],
            userObj:{},
        }
    },
    // mixins:[parserMixin],
}
</script>



<style scoped>
</style>
