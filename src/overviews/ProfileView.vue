<template>
    <div>
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;"><span id='titleEffect'>Pregled naloga</span></h1>
            <hr style='background:#35424a;height:1px'>
        </div>
        <div  class="container" style="margin-bottom:200px;">
            <form>
                <ul id="services" class="list-group">
                <!-- Osoba -->
                    <li class="list-group-item" >
                        <h5 class="header5">Tip korisnika</h5>
                        <h4>{{profile.tipKorisnika}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Korisnicko ime</h5>
                        <h4>{{profile.korIme}}</h4>
                    </li>
                    <li class="list-group-item">
                        <h5 class="header5">Email adresa</h5>
                        <h4>{{profile.email}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Ime</h5>
                        <h4>{{profile.ime}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Prezime</h5>
                        <h4>{{profile.prezime}}</h4>
                    </li>
                    <li v-if="profile.adresa" class="list-group-item" >
                        <h5 class="header5">Adresa</h5>
                        <h4>{{profile.adresa.adresa}}</h4>
                    </li>
                    <!-- <li class="list-group-item" >
                        <h5 class="header5">Datum Rodjenja</h5>
                        <h4>{{profile.datumRodjenja}}</h4>
                    </li> -->
                    <!-- <li class="list-group-item" >
                        <h5 class="header5">Poslednja Donacija</h5>
                        <h4>{{profile.poslednjaDonacija}}</h4>
                    </li>


                    <li class="list-group-item" >
                        <h5 class="header5">Penali</h5>
                        <h4>{{profile.penali}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Loyalty poeni</h5>
                        <h4>{{profile.poeni}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Loyalty kategorija</h5>
                       <h4>{{profile.kategorija.naziv}}</h4>
                    </li> -->
                    <li class="list-group-item">
                        <h5 class="header5">Slike</h5>
                        <div>
                            <vueperslides  fade fixed-height="600px">
                                <vueperslide v-for="(slide, i) in profile.slike" :key="i">
                                    <template v-slot:content>
                                        <img style="width:100%; height:600px;" :src="slide">
                                    </template>
                                </vueperslide>
                            </vueperslides>
                        </div>
                    </li>
                    
                </ul>

                <div  id='buttonUpdate' style="margin-bottom:100px; display:flex">
                    <button :disabled='btnEnabled' type='button' class="btn btn-lg btn-success marg float-left" v-on:click='updateUser(userObj.id)'> Update</button> 
                    <button style="margin-left:auto" type='button' class="btn btn-lg btn-danger marg float-right" v-on:click='deleteUser(userObj.id)'> Delete</button> 
                </div>
                
            </form>
            <div >
                <div v-if="messages.errorRemoval" v-html="messages.errorRemoval" class="alert alert-danger">
                </div>
                <div v-if="messages.successRemoval" v-html="messages.successRemoval" class="alert alert-success">
                </div>
            </div>
            
        </div>
        <section v-if="userObj.role == 'HOST'" style="margin-left: 10px; margin-bottom:40px;">
            <div>
                <div v-show="profile.smestajList.length > 0" class="row">
                    <div class="col-md-3" style="min-height:600px;" v-bind:key="index" v-for="(tempSmestaj, index) in profile.smestajList.slice(0,15)">
                        <div style="width:90%;height:90%" class="card  text-center">
                            <img class="card-img-top" style="width:90%;height:50%; margin-left:auto; margin-right:auto; margin-top:10px;" :src="getAnImage(tempSmestaj)" alt="card image collar">
                            <div class="card-body">
                                <p class="card-text">Smestaj {{tempSmestaj.naziv}} - {{tempSmestaj.cena}}</p>
                                <p class="card-text">{{tempSmestaj.adresa.adresa}}</p>
                                
                                <div class="starClass">
                                    <starrating style="margin-bottom:10px" read-only v-model="tempSmestaj.ocena" :star-size="30"/>
                                </div>
                                <div class="options-buttons">
                                    <button v-on:click="smestajDetails(tempSmestaj.id)" class="btn btn-primary">Detalji</button>
                                    <button v-on:click="smestajEdit(tempSmestaj.id)" class="btn btn-primary">Izmeni</button>
                                    <button v-on:click="smestajRemove(tempSmestaj.id)" class="btn btn-primary">Ukloni</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-show="profile.smestajList.length == 0">
                    <h1 style="font-style:italic; font-family:Verdana">KORISNIK NEMA NISTA U PONUDI...</h1>
                </div>
            </div>
        </section>
    </div>

</template>

<script>

import dataService from '../services/dataService'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import parserMixin from '@/mixins/mixin'
import axios from 'axios'
    export default{
        components:{
            vueperslides : VueperSlides,
            vueperslide :VueperSlide
        },
        data(){
            return{
                messages:{
                    successRemoval:'',
                    errorRemoval:'',
                },
                profile:{
                    kategorija:{},
                    smestajList:[]
                },
                btnEnabled:false,
                userId : '',
                username : '',
                //Ostaviti proil objekat zbog mapiranja
                role:'',
                ownerId:'',

                userObj:{

                }
            }
        },
        created(){ 
            parserMixin.methods.checkLoginStatus();
            this.userObj = parserMixin.methods.parseXmlJwt();
            console.log("ID KORISNIKA: " + this.userObj.id);
            this.getUserProfileData(this.userObj.id);
        },
        methods:{
            smestajRemove(id){
                dataService.removeSmestaj(id, this.userObj.id).then(response => {
                    if(response.status === 200){
                        console.log("USPESNO OBRISANO");
                        this.$router.push(`/dash`);
                    }
                })
            },
            smestajEdit(id){
                this.$router.push(`/editSmestaj/${id}`);
            },
            smestajDetails(id){
                this.$router.push(`/pregled/${id}`);
                // this.$router.push(`/overview/${id}`);
            },
            getAnImage(smestaj){
                let tmpImg = 'data:image/png;base64,';
                for(let i = 0; i < smestaj.slike.length; i++){
                    tmpImg += smestaj.slike[i];
                    break;
                }
                console.log(tmpImg);
                return tmpImg;
            },
            getUserProfileData(id){
                try{
                        // const response = axios.get('http://localhost:8082/korisnik/1');
                        // console.log("STIGAO ODGOVOR");
                    dataService.getUser(id).then(response => {
                        // console.log("USER PROFILE: " + JSON.stringify(response.data));
                        this.profile = response.data;
                        if(this.profile.slike){
                            if(this.userObj.role === 'HOST'){
                                console.log("BROJ SMESTAJA: " + this.profile.smestajList.length);
                            }
                            // console.log("BROJ SLIKA: " + this.profile.slike.length);
                            let tempSlike = [];
                            for(let i = 0; i < this.profile.slike.length; i++){
                                // console.log("AAA")
                                tempSlike.push('data:image/png;base64,' + this.profile.slike[i]);
                            }
                            this.profile.slike = tempSlike;
                            // console.log("SLIKA POSLE FORMATIRANJA: " + JSON.stringify(this.profile.slike[0]))
                        }else{
                            this.profile.slike = [];
                        }
                        
                    });
                }catch(error){
                    console.log("GRESKA: " + error.message);
                }
            
            },
            updateUser(id){ 
                this.$router.push(`/edit`);
            },
            deleteUser(id){ 
                // this.$router.push(`/deleteAccount/${id}`)
                console.log("BRISANJE NALOGA ID-a: " + this.userObj.id);
                dataService.deleteUserProfile(id).then(response => {
                     //this.profile = response.data;
                     if(response.status === 200){
                        this.messages.successRemoval = "<h4>Nalog uspesno obrisan!</h4>"
                        setTimeout(() => {
                            this.messages.successRemoval = '';
                            this.emitter.emit("loggedIn", false);
                            if(localStorage.getItem('xmljwt')){
                                localStorage.removeItem('xmljwt');
                                axios.defaults.headers.common['Authorization'] = undefined;
                                this.loggedIn = false;
                                this.$router.push("/");
                                if(localStorage.getItem('parsedToken')){
                                    localStorage.removeItem('parsedToken');
                                }
                            }else{
                                localStorage.removeItem('xmljwt');
                                axios.defaults.headers.common['Authorization'] = undefined;
                                this.loggedIn = false;
                                this.$router.push('/');
                            }
                        }, 5000);
                        setTimeout(() => {
                            this.$router.push(`/`);
                        }, 5050);
                     }else{
                        this.messages.errorRemoval = "<h4>Ne mozete obrisati nalog jer imate aktivne rezervacije!</h4>"
                        setTimeout(() => {
                            this.messages.errorRemoval = '';
                        }, 5050);
                     }
                })
            },
        },
        
    }

</script>

<style scoped>

.options-buttons button{
    margin-left: 5px;
    margin-right: 5px;
}

#titleEffect{
  color:#1E90FF;
  font-weight: bold;
}

#user-profile ul#services li{
    list-style: none;
    padding:15px;
    border-bottom: gray solid 1px;
    border-radius: 15px;
    margin-bottom: 3px; 
    background-color:#fff;
    width:100%;
}

/* #user-profile#buttonUpdate{
  float:left;
} */


.header5{
    color:#1E90FF;
    font-weight: bold;
}

.marg{
    margin-top:15px;
}

</style>