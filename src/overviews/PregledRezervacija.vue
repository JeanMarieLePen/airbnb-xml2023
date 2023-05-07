<template>
    <div style="margin-left:15px;">
        <h1>Pregled rezervacija:</h1>
        <div v-if="listaRezervacija.length > 0" class="row">
            <!-- <div class="card-deck"> -->
                <div class="col-md-3" v-for="(r, index) in listaRezervacija" v-bind:key="index">
                    <!-- class="card" v-for="(t, index) in listaRezervacija" :key="index" -->
                    <div class="card">
                        <div class="text-center bg-success" style="height:350px;">
                            <img class="card-img-top" style="height:95%;width:95%; margin-top:2.5%;" :src="getAnImage(r.smestaj)">
                        </div>
                        
                        <div class="card-body text-white bg-success">
                            <h5 class="card-title text-center">
                                Termin
                            </h5>
                            <div style="margin:5%;">
                                <p class="card-text display-7">Pocetak: {{setDatumForm(r.odDatum)}}</p>
                                <p class="card-text display-7">Kraj: {{setDatumForm(r.doDatum)}}</p>
                                <p class="card-text display-7">Broj gostiju: {{r.brojGostiju}}</p>
                                <p class="card-text display-7">Slobodnih mesta: {{r.smestaj.maxGosti}}</p>
                                <p class="card-text display-7">Preostalo vreme: {{calcTime(r)}}</p>
                                <p class="card-text display-7">Status: {{r.status}}</p>
                            </div>
                            
                        </div>
                        <div class="card-footer bg-white text-white text-center " style="height:100px;">
                            <button v-show="calcTime2(r) >= 24 && r.status == 'REZERVISANA'" @click="cancelRezervaciju(r, index)" style="margin-top:20px;" class="btn btn-warning">Otkazi</button>
                            <div style="margin:10px;" v-if="messages[index].successMessage" v-html="messages[index].successMessage" class="alert alert-success">
                            
                            </div>
                            <div style="margin:10px;" v-if="messages[index].errorMessage" v-html="messages[index].errorMessage" class="alert alert-danger">

                            </div>
                        </div>
                    </div>
                <!-- </div> -->
            </div>
        </div>
        <div v-if="listaRezervacija.length==0">
            Trenutno nemate rezervacija...
        </div>
    </div>
</template>


<script>

import dataService from '../services/dataService'
import moment from 'moment'
import parserMixin from '@/mixins/mixin';
export default {
    mixins:[parserMixin],
    data(){
        return{
            user:{
                username:'',
            },
            role:'',
            userId:-1,

            listaRezervacija:[],
            messages:[],
        }
    },
    methods:{
        getAnImage(smestaj){
            let tmpImg = 'data:image/png;base64,';
            for(let i = 0; i < smestaj.slike.length; i++){
                tmpImg += smestaj.slike[i];
                break;
            }
            console.log(tmpImg);
            return tmpImg;
        },
        setDatumForm(datum){
            console.log("setDatumForm: prosledjen datum: " + JSON.stringify(datum));
            return moment(datum).format("YYYY-MM-DD");
        },
        datumFormat(datum){
            console.log("DATUM PRE FORMATIRANJA: " + datum);
            let tempDatum = moment(datum).format("YYYY-MM-ddd hh:mm")
            console.log("DATUM POSLE FORMATIRANJA: " + tempDatum);
            return tempDatum;
        },
        calcTime(termin){
            console.log("Pocetak: " + termin.odDatum)
            const date1 = new Date(termin.odDatum);
            const date2 = new Date();
            const diffTime = date2.getTime() - date1.getTime();
            const diffDays = Math.floor(Math.abs(diffTime / (1000 * 3600 * 24)));
            const diffHours = Math.floor(Math.abs(diffTime / (1000 * 3600)));
            console.log("diffTime: " + diffTime)
            console.log("diffDays: " + diffDays)
            console.log("diffHours: " + diffHours)
            return diffDays + ' dana(' + diffHours + ' sati)';
        },
        calcTime2(termin){
            console.log("Pocetak: " + termin.odDatum)
            const date1 = new Date(termin.odDatum);
            const date2 = new Date();
            const diffTime = date2.getTime() - date1.getTime();
            const diffDays = Math.floor(Math.abs(diffTime / (1000 * 3600 * 24)));
            const diffHours = Math.floor(Math.abs(diffTime / (1000 * 3600)));
            console.log("diffTime: " + diffTime)
            console.log("diffDays: " + diffDays)
            console.log("diffHours: " + diffHours)
            return diffHours;
        },
        cancelRezervaciju(rezervacija, index){
            try{
                dataService.cancelRezervaciju(rezervacija.id, this.userId).then(response => {
                    console.log("Otkazana rezervacija");
                    if(response.status === 200){
                        this.messages[index].successMessage = '<h4>Uspesno otkazana rezervacija.</h4>';
                        setTimeout(() => {
                            this.messages[index].successMessage = '';
                        }, 4000);
                        setTimeout(() => {
                            this.getRezervacije();
                        }, 4050);
                    }else{
                        this.messages[index].errorMessage = '<h4>Doslo je do greske.</h4>';
                        setTimeout(() => {
                            this.messages[index].errorMessage = '';
                        }, 4000);
                        setTimeout(() => {
                            this.getRezervacije();
                        }, 4050);
                    }
                });
            }catch(error){

            }
        },
        getRezervacije(){
            return dataService.getRezervacijeByKorisnik(this.userId).then(response => {
                
                this.listaRezervacija = response.data;
                console.log("userId:"+this.userId)
                console.log("Dobavljena lista rezervacija, size="+this.listaRezervacija.length);
                this.messages = [];
                for(let i = 0; i < this.listaRezervacija.length; i++){
                    let tempObjekat = new Object();
                    tempObjekat.successMessage = '';
                    tempObjekat.errorMessage = '';
                    this.messages.push(tempObjekat);
                }
            });
        },
    },  
    async mounted(){
        await this.getRezervacije();
    },
    created(){
        let userObj = parserMixin.methods.parseXmlJwt();
        this.user.username = userObj.email;
        this.role = userObj.role;
        this.userId = userObj.id;
    }
}
</script>




<style scoped>

</style>