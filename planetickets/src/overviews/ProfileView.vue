<template>
    <div>
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;"><span id='titleEffect'>Pregled naloga</span></h1>
            <hr style='background:#35424a;height:1px'>
        </div>
        <div  class="container">
            <form>
                <ul id="services" class="list-group">
                <!-- Osoba -->
                    <li class="list-group-item" >
                        <h5 class="header5">Tip korisnika</h5>
                        <h4>{{profile.tip}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Korisnicko ime</h5>
                        <h4>{{profile.username}}</h4>
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
                    <li class="list-group-item" >
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
                    <li v-if="profile.slike" class="list-group-item">
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
            <div v-if="messages.errorRemoval" v-html="messages.errorRemoval" class="alert alert-danger">
            </div>
            <div v-if="messages.successRemoval" v-html="messages.successRemoval" class="alert alert-success">
            </div>
        </div>

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
            getUserProfileData(id){
                try{
                        // const response = axios.get('http://localhost:8082/korisnik/1');
                        // console.log("STIGAO ODGOVOR");
                    dataService.getUser(id).then(response => {
                        console.log("USER PROFILE: " + JSON.stringify(response.data));
                        this.profile = response.data;
                        if(response.data.slike != null){
                            console.log("BROJ SLIKA: " + this.profile.slike.length);
                            let tempSlike = [];
                            for(let i = 0; i < this.profile.slike.length; i++){
                                console.log("AAA")
                                tempSlike.push('data:image/png;base64,' + this.profile.slike[i]);
                            }
                            this.profile.slike = tempSlike;
                            console.log("SLIKA POSLE FORMATIRANJA: " + JSON.stringify(this.profile.slike[0]))
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
                     this.profile = response.data;
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