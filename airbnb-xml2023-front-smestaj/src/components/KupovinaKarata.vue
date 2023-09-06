<template>
    <div>
        <div>
            <h2>Pregled letova:</h2>
            <table class="table" style="width:90%; margin-left:100;">
                <tbody>
                    <tr>
                        <table>
                            <tr>
                                <td>
                                    UNESITE LOKACIJU POLASKA: 
                                </td>
                                <td>
                                    <input type="text" v-model="lokacijaPolaska"/>
                                </td>
                                <td>
                                    <button @click="pretragaLetova1()" class="btn btn-success">PRETRAGA</button>
                                </td>
                            </tr>
                        </table>
                    </tr>
                    
                    <tr v-for="(letT, index) in listaLetova1" :key="index">
                        <td>{{formatDate(letT.datumIVreme)}}</td>
                        <td>{{letT.lokOd}}</td>
                        <td>{{letT.lokDo}}</td>
                        <!-- <td><a href="" v-on:click="flightDetails(letT.id)">{{letT.brojLeta}}</a></td> -->
                        <td>{{letT.cena}}</td>
                        <!-- <td v-if="getBrKarata() > 0">{{getUkCena(letT.cena)}}</td> -->
                        <td>{{letT.brSlobMesta}}</td>
                        <td> 
                            <button @click="enterTokenButton()" class="btn btn-success">BrzaRez</button>
                        </td>
                        <td v-show="showTokenInput">
                            <input type="text" placeholder="Unesite Vas token..." v-model="token">
                        </td>
                        <td v-show="showTokenInput == true && token != ''">
                            <button @click="buyTickets(letT)">Naruci</button>
                        </td>
                        <td v-show="rezervacijaUspesna == true">
                            <div v-html="messages.successKarta" v-if="messages.successKarta" class="alert alert-success"></div>
                            <div v-html="messages.errorKarta" v-if="messages.errorKarta" class="alert alert-warning"></div>
                        </td>
                    </tr>
                </tbody>
            </table>
             <table class="table" style="width:90%; margin-left:100;">
                <tbody>
                    <tr>
                        <table>
                            <tr>
                                <td>
                                    UNESITE LOKACIJU DOLASKA: 
                                </td>
                                <td>
                                    <input type="text" v-model="lokacijaDolaska"/>
                                </td>
                                <td>
                                    <button @click="pretragaLetova2()" class="btn btn-success">PRETRAGA</button>
                                </td>
                            </tr>
                        </table>
                    </tr>
                    <tr v-for="(letT, index) in listaLetova2" :key="index">
                        <td>{{formatDate(letT.datumIVreme)}}</td>
                        <td>{{letT.lokOd}}</td>
                        <td>{{letT.lokDo}}</td>
                        <!-- <td><a href="" v-on:click="flightDetails(letT.id)">{{letT.brojLeta}}</a></td> -->
                        <td>{{letT.cena}}</td>
                        <!-- <td v-if="getBrKarata() > 0">{{getUkCena(letT.cena)}}</td> -->
                        <td>{{letT.brSlobMesta}}</td>
                        <td> 
                            <button @click="enterTokenButton()" class="btn btn-success">BrzaRez</button>
                        </td>
                        <td v-show="showTokenInput">
                            <input type="text" placeholder="Unesite Vas token..." v-model="token">
                        </td>
                        <td v-show="showTokenInput == true && token != ''">
                            <button @click="buyTickets(letT)">Naruci</button>
                        </td>
                        <td v-show="rezervacijaUspesna == true">
                            <div v-html="messages.successKarta" v-if="messages.successKarta" class="alert alert-success"></div>
                            <div v-html="messages.errorKarta" v-if="messages.errorKarta" class="alert alert-warning"></div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>    
</template>


<script>
import dataService from '@/services/dataService';
import moment from 'moment';
import axios from 'axios';
import parserMixin from '@/mixins/mixin';

export default {
 
    data(){
        return{
            userObj:{

            },


            lokacijaPolaska:'',
            lokacijaDolaska:'',
            listaLetova1:[],
            listaLetova2:[],
            showTokenInput:false,
            token:'',
            rez:{
                let: "",
                emailKupca: "",
                brojKarata:"1"
            },
            rezervacijaUspesna:false,
            messages:{
                successKarta:'',
                errorKarta:'',
            },
            idRezervacije:'',
            rezervacija:null,
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
                krajnjaLok:{
                    id:'0',
                    adresa:'',
                    lat:0,
                    lng:0
                }
            }
        }
    },
    methods:{
        async pretragaLetova1(){
            console.log("PARAMETRIDTO: " + JSON.stringify(this.parametriDTO));
             this.parametriDTO.pocetnaLok.adresa = this.lokacijaPolaska;
             this.parametriDTO.krajnjaLok.adresa = this.rezervacija.adresa;

            console.log("POCETNI DATUM PRE: " + this.rezervacija.odDatum);
            let pocetakTemp = new Date(this.rezervacija.odDatum);
            pocetakTemp.setDate(pocetakTemp.getDate() - 5);
            this.parametriDTO.pocetak = moment(pocetakTemp).format("YYYY-MM-DDThh:mm:ss");
            console.log("POCETNI DATUM POSLE: " + this.parametriDTO.pocetak);

            let krajTemp = new Date(this.rezervacija.odDatum);
            krajTemp.setDate(krajTemp.getDate());
            this.parametriDTO.kraj = moment(krajTemp).format("YYYY-MM-DDThh:mm:ss");

            return dataService.getPreporuceniLetovi(this.parametriDTO).then(response => {
                console.log("DOBAVLJENA LISTA LETOVA");
                console.log(JSON.stringify(this.listaLetova));
                this.listaLetova1 = response.data;
            }).catch(error => {
                console.log("GRESKA: " + error.message);
            });
        },
        async pretragaLetova2(){
            console.log("PARAMETRIDTO: " + JSON.stringify(this.parametriDTO));

            console.log("POCETNI DATUM PRE: " + this.rezervacija.doDatum);
            let pocetakTemp = new Date(this.rezervacija.doDatum);
            pocetakTemp.setDate(pocetakTemp.getDate());
            this.parametriDTO.pocetak = moment(pocetakTemp).format("YYYY-MM-DDThh:mm:ss");
            console.log("POCETNI DATUM POSLE: " + this.parametriDTO.pocetak);

            let krajTemp = new Date(this.rezervacija.doDatum);
            krajTemp.setDate(krajTemp.getDate() + 5);
            this.parametriDTO.kraj = moment(krajTemp).format("YYYY-MM-DDThh:mm:ss");

             this.parametriDTO.pocetnaLok.adresa = this.rezervacija.adresa;
             this.parametriDTO.krajnjaLok.adresa = this.lokacijaDolaska;
            return dataService.getPreporuceniLetovi(this.parametriDTO).then(response => {
                console.log("DOBAVLJENA LISTA LETOVA");
                console.log(JSON.stringify(this.listaLetova));
                this.listaLetova2 = response.data;
            }).catch(error => {
                console.log("GRESKA: " + error.message);
            });
        },
        getDani(date1, date2){
            let d1=new Date(date1);
            let d2=new Date(date2);
            return 	Math.floor((d2.getTime()-d1.getTime()) / (1000 * 60 * 60 * 24));
        },
        buyTickets(letT){
            this.rez.let = letT.id;
            this.rez.emailKupca = this.userObj.email;
            this.rez.brojKarata = this.rezervacija.brojGostiju;

            axios.post(`http://localhost:7777/regkor/reservationWithToken`, this.rez,
            {
                headers: {
                    'gatewayApiKey': this.token
                }
            }).then(response => {
                if(response.status === 200){
                    console.log("USPESNA REZERVACIJA KARTE");
                    this.rezervacijaUspesna = true;
                    this.messages.successKarta = "<h2>REZERVISANO</h2>";
                    setTimeout(() => {
                        this.messages.successKarta = "";
                        this.rezervacijaUspesna = false;
                    }, 4000);
                }else{
                    console.log("NEUSPESNA REZERVACIJA");
                    this.rezervacijaUspesna = true;
                    this.messages.errorKarta = "<h5>NEUSPESNO</h5>";
                    setTimeout(() => {
                        this.messages.errorKarta = "";
                        this.rezervacijaUspesna = false;
                    }, 4000);
                }
                
            });
            // dataService.buyTicketsViaToken(this.rez, this.token).then(response => {
            //     if(response.status === 200){
            //         console.log("Kupljena karta");
            //         this.messages.successKarta = '<h4>Uspesno kupljena avionska karta.</h4>'
            //         setTimeout(() => {
            //             this.messages.successKarta = '';
            //         });
            //     }else{
            //         this.messages.errorKarta = '<h4>Prosledjeni token nije ispravan ili je istekao.</h4>'
            //         setTimeout(() => {
            //             this.messages.errorKarta = '';
            //         });
            //     }
            // });
        },
        enterTokenButton(){
            this.showTokenInput = !this.showTokenInput;
        },
        formatDate(date){
                let tempDate = moment(date).format('MMMM Do YYYY, h:mm:ss a')
                return tempDate;
            },

        // getLetovi(){
        //     console.log("PARAMETRIDTO: " + JSON.stringify(this.parametriDTO));

        //     return dataService.getPreporuceniLetovi(this.parametriDTO).then(response => {
        //         console.log("DOBAVLJENA LISTA LETOVA");
        //         console.log(JSON.stringify(this.listaLetova));
        //         this.listaLetova = response.data;
        //     }).catch(error => {
        //         console.log("GRESKA: " + error.message);
        //     });
        // },
        getRezervacija(){
            return dataService.getRezervacijaById(this.$route.params.id).then(response => {
                console.log("DOBAVLJENA REZERVACIJA: " + JSON.stringify(response.data));
                this.rezervacija = response.data;

                // console.log("POCETNI DATUM PRE: " + this.rezervacija.odDatum);
                // let pocetakTemp = new Date(this.rezervacija.odDatum);
                // pocetakTemp.setDate(pocetakTemp.getDate() - 2);
                // this.parametriDTO.pocetak = moment(pocetakTemp).format("YYYY-MM-DDThh:mm:ss");
                // console.log("POCETNI DATUM POSLE: " + this.parametriDTO.pocetak);

                // let krajTemp = new Date(this.rezervacija.doDatum);
                // krajTemp.setDate(krajTemp.getDate() + 2);
                // this.parametriDTO.kraj = moment(krajTemp).format("YYYY-MM-DDThh:mm:ss");

                this.parametriDTO.brKarata = this.rezervacija.brojGostiju;
                // this.parametriDTO.krajnjaLok.adresa = this.rezervacija.adresa;
                if(this.parametriDTO.minCena===''){
                    this.parametriDTO.minCena=-1;
                }
                if(this.parametriDTO.maxCena===''){
                    this.parametriDTO.maxCena=-1;
                }
            });
        }
    },
    async created(){
        parserMixin.methods.checkLoginStatus();
        this.userObj = parserMixin.methods.parseXmlJwt();
        console.log("EMAIL KORISNIKA: " + this.userObj.email);
        await this.getRezervacija();
        // await this.getLetovi();
    }
}
</script>



<style scoped>

</style>