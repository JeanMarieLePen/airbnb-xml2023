<template>
    <div style="width:50%; margin: 0 auto;"  class="registration_form">
        <div class="container" >
    
            <div class="row" style="width:100%" >
            <!-- <div class="col-lg-10 col-xl-9 mx-auto"> -->
                <div class="card card-signin flex-row my-5" >
                <div class="card-img-left d-none d-md-flex">
                    <!-- Background image for card set in CSS! -->
                </div>
                <div class="card-body" style="width:100hv;">
                    <h1 class="card-title text-center" style="color :#5cb85c;">Register</h1>
                    <hr>
                    <br>
                    <div id='message-box' class="alert alert-success" v-if='messages.successMessage' v-html="messages.successMessage"> </div>
                    <div v-if='!this.submitted'>
                    <form class="form-signin">

                        <div v-if='messages.errorFirstName' id='testError' class="alert alert-danger" v-html="messages.errorFirstName"></div>
                        <div class="form-label-group">
                        <input v-model="form.ime" type="text" id="inputName" class="form-control" placeholder="Insert name" required autofocus>
                        <label for="inputName">Ime</label>
                        </div>

                                <div v-if='messages.errorLastName' class="alert alert-danger" v-html="messages.errorLastName"></div>
                        <div class="form-label-group">
                        <input v-model="form.prezime" type="text" id="inputSurname" class="form-control" placeholder="Insert surname" required autofocus>
                        <label for="inputSurname">Prezime</label>
                        </div>

                                <div v-if='messages.errorEmail' class="alert alert-danger" v-html="messages.errorEmail"></div>
                        <div class="form-label-group">
                        <input v-model="form.email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
                        <label for="inputEmail">Email adresa</label>
                        </div>

                        <div v-if='messages.errorUsername' class="alert alert-danger" v-html="messages.errorUsername"></div>
                        <div class="form-label-group">
                        <input v-model="form.username" type="text" id="inputUsername" class="form-control" placeholder="Username" required>
                        <label for="inputUsername">Korisničko ime</label>
                        </div>

                        <div style="margin-top:20px" v-if='messages.errorAddress' class="alert alert-danger" v-html="messages.errorAddress"></div>
                        <br>
                        <h4><label style="color :#5cb85c;">Adresa</label></h4>
                        <div>
                        <input class="one-forth" placeholder="Unesite adresu(ulica, mesto, broj, drzava)..." v-model='form.adresa.adresa'>
                        </div>

                        <h4><label style="color :#5cb85c;">Slike</label></h4>
                        <div>
                            <vueperslides fixed-height="600px">
                                <vueperslide v-for="(slide, i) in form.slike" :key="i">
                                    <template v-slot:content>
                                        <img :src="slide" style="width:400px;height:600px">
                                    </template>
                                </vueperslide>
                            </vueperslides>
                        </div>
                        <div style="display: flex; justify-content: flex-end">
                            <input @change="uploadImage" multiple style="margin-top:20px;margin-bottom:20px;" type="file">
                            <button style="width:100px; margin-top:15px; height:30px;" @click="ponistiIzbor()">Ponisti</button>
                        </div>


                        <div v-if='messages.errorPassword' class="alert alert-danger" v-html="messages.errorPassword"></div>
                        <div class="form-label-group">
                        <input v-model="form.password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                        <label for="inputPassword">Šifra</label>
                        </div>
                        
                        <div v-if='messages.errorRepNewPass' class="alert alert-danger" v-html="messages.errorRepNewPass"></div>
                        <div class="form-label-group"> 
                        <input  v-model="confirmPassword" type="password" id="inputConfirmPassword" class="form-control" placeholder="Password" required>
                        <label for="inputConfirmPassword">Potvrda šifre</label>
                        </div>
                        <hr>

                        <!-- <div style="margin-top:20px" v-if='messages.errorDatumRodjenja' class="alert alert-danger" >
                        <br>
                        </div>
                        <h4><label style="color :#5cb85c;">Datum rodjenja:</label></h4> 
                        <div style="margin-bottom:30px;">
                            <dtpckr @selected="updateDatePicker" v-model="form.datumRodjenja">

                            </dtpckr>
                        </div> -->

                        <div class="alert alert-warning" v-if='messages.errorPasswordMismatch' v-html="messages.errorPasswordMismatch"> </div>
                        <!-- <div style="margin-top:20px" v-if='messages.error' class="alert alert-danger" v-html="messages.error"></div> -->
                        <div style="text-align:center">
                            <button  type="button" class="btn btn-lg btn-success btn-block text-uppercase" @click="submit()">
                            Registruj se
                            </button>
                            <a class="d-block text-center mt-2 small" href="/login">Uloguj se</a>
                            <hr class="my-4">
                            <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success"></div>
                        </div>
                        
                    </form>
                    </div>  
                </div>
                <!-- </div> -->
            </div>
            </div>
        </div>
    </div>
</template>


<script>
import dataService from '../services/dataService'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";


import moment from 'moment'

import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import coordsMixin from '@/mixins/coordsMixin'
export default{
    // mixins:[coordsMixin],
    components:{
        dtpckr:VueDatePicker,
        vueperslides : VueperSlides,
        vueperslide :VueperSlide,
        YandexMap, YandexMarker,
    },
    async mounted(){
        console.log("MONTIRANJE")
        await loadYmap({ ...this.settings, format: "json", debug: true});
        // await this.getEntity();
        // await this.getCoordsFromAdresa();
    },
    data(){
        return{
            // private String password;
            // private String username;
            // private String email;
            
            // private String ime;
            // private String prezime;
            // private String adresa;
            // private LocalDate datumRodjenja;
	
	        // private TipKorisnika tipKorisnika;
            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
            lokacija: new Object(),
            submitted : false,
            confirmPassword : '',
            form:{
                password : '',
                username : '',
                email : '',
                ime : '',
                prezime: '',
                adresa: {
                    adresa:'',
                    lat:-1,
                    lng:-1,
                },
                // datumRodjenja: new Date(),
                slike:[],
            },
            slike : [],

            messages:{
                messageConfirmPassword:'',
                successMessage:'',
                errorType:'',
                errorLastName:'',
                errorFirstName:'',
                errorEmail:'',
                errorPassword:'',
                errorUsername:'',
                errorRepNewPass:'',
                errorNoPassword:'',
                errorAddress:'',
                // errorDatumRodjenja:'',
                errorPasswordMismatch:'',
            }
            
        }
    },
    methods:{
        ponistiIzbor(){
            this.slike = [];
            this.form.slike = [];
        },
        uploadImage(e){
            let images = [];
            for(let i = 0; i < e.target.files.length; i++){
                images.push(e.target.files[i]);
            }
            for(let i = 0; i < images.length; i++){
                let reader = new FileReader();
                reader.readAsDataURL(images[i]);
                reader.onload = (() => {
                    this.form.slike.push(reader.result);
                    this.slike.push(reader.result);
                });
            }
        },
        async getCoordsFromAdresa(adresa){
            console.log("DDDDDDD")
            return ymaps.geocode(adresa).then((result) => {
                let locationCoords = {
                    coords: result.geoObjects.get(0).geometry.getCoordinates(),
                };
                this.lokacija.lat = locationCoords.coords[0];
                this.lokacija.lng = locationCoords.coords[1];
                this.form.adresa.lat = this.lokacija.lat;
                this.form.adresa.lng = this.lokacija.lng;
                console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            });
            // return (() => {
            //     console.log("AAAAAAA")
            //     ymaps.geocode(adresa).then((result) => {
            //         let locationCoords = {
            //             coords: result.geoObjects.get(0).geometry.getCoordinates(),
            //         };
            //         this.lokacija.lat = locationCoords.coords[0];
            //         this.lokacija.lng = locationCoords.coords[1];
            //     });
            // })



            // ymaps.geocode(adresa).then((result) => {
            // let locationCoords = {
            //     coords: result.geoObjects.get(0).geometry.getCoordinates(),
            // };
            // let location = new Object();
            // location.lat = locationCoords.coords[0];
            // location.lng = locationCoords.coords[1];
            // console.log("LOKACIJA:" + JSON.stringify(this.lokacija));
            // });
            // ymaps.geocode(adresa).then((result) => {
            //     let locationCoords = {
            //         coords: result.geoObjects.get(0).geometry.getCoordinates(),
            //     };
            //     this.lokacija.lat = locationCoords.coords[0];
            //     this.lokacija.lng = locationCoords.coords[1];
            //     console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            // });
        },
        updateDatePicker(val1){
            console.log("VAL1: "  + val1);
            let tempDatum = moment(val1).format('YYYY-MM-DD');
            console.log("TEMP DATUM: "  + tempDatum);
            this.form.datumRodjenja = tempDatum;
        },
        // setDatumRodjenja(){
        //     let tempDatum = 
        // },
        async submit(){
            if((this.form.ime === '')){
                this.messages.errorFirstName = '<h2>Niste uneli ime korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorFirstName = '';
                }, 4000);
            }else if((this.form.prezime === '')){
                this.messages.errorLastName = '<h2>Niste uneli prezime korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorLastName = '';
                }, 4000);
            }
            else if((this.form.email === '')){
                this.messages.errorEmail = '<h2>Niste uneli email adresu korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorEmail = '';
                }, 4000);
            }
            else if(this.form.username === ''){
                this.messages.errorUsername = '<h2>Niste uneli korisnicko ime!</h2>'
                setTimeout(() => {
                    this.messages.errorUsername = '';
                }, 4000);
            }
            else if((this.form.adresa.adresa === '')){
                this.messages.errorAddress = '<h2>Niste uneli adresu korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorAddress = '';
                }, 4000);
            }
            else if((this.form.password === '')){
                this.messages.errorPassword = '<h2>Niste uneli lozinku korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorPassword = '';
                }, 4000);
            }else if(this.confirmPassword === ''){
                this.messages.errorRepNewPass = '<h2>Niste potvrdili lozinku korisnika!</h2>'
                setTimeout(() => {
                    this.messages.errorRepNewPass = '';
                }, 4000);
            }
            // else if(this.form.datumRodjenja == ''){
            //     this.messages.errorDatumRodjenja = '<h2>Niste odabrali datum rodjenja!</h2>';
            //     setTimeout(() => {
            //         this.messages.errorDatumRodjenja = '';
            //     }, 4000);
            // }
            else{
                // this.updateDatePicker(this.form.datumRodjenja);
                console.log("Kreiran objekat: " + JSON.stringify(this.form));
                if(this.form.password != this.confirmPassword){
                        this.messages.errorPasswordMismatch = "<h2>Lozinke se ne podudaraju!</h2>";
                        setTimeout( () => {
                            this.messages.errorPasswordMismatch = '';
                        }, 4000);
                }else{
                    // let lokacija = coordsMixin.methods.getCoordsFromAdresa(this.form.adresa.adresa);
                    console.log("PRE POZIVA")
                    await this.getCoordsFromAdresa(this.form.adresa.adresa);
                    console.log("REGISTRUJE SE KORISNIK: " + JSON.stringify(this.form));
                    dataService.register(this.form).then(response => {
                        if(response.status === 200){
                            console.log("Uspesno registrovan korisnik");
                            this.messages.successMessage = '<h2>Zahtev uspesan. Proverite mejl za potvrdu naloga.</h2>';

                            setTimeout(() => {
                                this.messages.successMessage = '';
                                this.$router.push("/");
                                
                            }, 4000)
                        }
                    })
                }
            }
        }
    },
    created(){

    }
}

</script>



<style scoped>
.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: 65px;
  /* height: auto; */
  text-align: center;
  border-radius: 2rem;
  
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}

.form-label-group>label {
  position: absolute;
  top: 10px;
  left: 30px;
  display: block;
  width: 100%;
  margin-bottom: 0;
  /* Override default `<label>` margin */
  line-height: 1.5;
  color: #495057;
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
  font-size: 18px;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown)~label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}

.one-forth{
  width:100%;
  padding:10px;
  margin-bottom:15px;
  border-radius:20px;
}

#message-box{
  margin-bottom:400px;
  padding:50px;
}

</style>