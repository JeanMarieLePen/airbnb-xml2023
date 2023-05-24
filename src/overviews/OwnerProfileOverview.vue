<template>

    <div style="margin-left:10px;">
        <div style="display:flex-inline" v-if="canRate">
                        <h4>Vasa ocena: </h4>
                        <star-rating v-model:rating="ocena.ocena" v-show="canRate" :star-size="40" style="margin-left:10px;display:inline-block"></star-rating>
                        <button @click="rateHost()">Ocenite</button>
                    </div>
        <div>
            <h1 style="margin-top:10px;color:#35424a;"><span id='titleEffect'>Pregled naloga</span></h1>
            <hr style='background:#35424a;height:1px;'>
        </div>
        <div style="width:30%;">
            <form>
                <ul id="services" class="list-group">
                    <li class="list-group-item" >
                        <h5 class="header5">Email korisnika:</h5>
                        <h4>{{profile.email}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Korisnicko ime:</h5>
                        <h4>{{profile.korIme}}</h4>
                    </li>
                    <li class="list-group-item">
                        <h5 class="header5">Ime korisnika:</h5>
                        <h4>{{profile.ime}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Prezime korisnika:</h5>
                        <h4>{{profile.prezime}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Prosecna ocena:</h5>
                        <star-rating :star-size="20" read-only style="margin-left:10px;display:inline-block"  v-model:rating="profile.prosecnaOcena"></star-rating>
                    </li>

                    <li v-if="profile.adresaPrebivalista" class="list-group-item">
                        <h5 class="header5">Adresa</h5>
                        <h6 class="header5">Drzava: {{profile.adresa.adresa}}</h6>
                    </li>
                </ul>
            </form>
        </div>
        <div>
            <h1 style="margin-top:10px;color:#35424a;"><span id='titleEffect'>Pregled ponude</span></h1>
            <hr style='background:#35424a;height:1px;'>
        </div>
        <section style="margin-left: 10px; margin-bottom:200px;">
            <div>
                <div v-show="profile.smestajList.length > 0" class="row">
                    <div class="col-md-3" style="min-height:600px;" v-bind:key="index" v-for="(tempSmestaj, index) in profile.smestajList.slice(0,15)">
                        <div style="width:90%;height:90%" class="card  text-center">
                            <img class="card-img-top" style="width:90%;height:50%; margin-left:auto; margin-right:auto; margin-top:10px;" :src="getAnImage(tempSmestaj)" alt="card image collar">
                            <div class="card-body">
                                <p class="card-text">Smestaj {{tempSmestaj.naziv}} - {{tempSmestaj.cena}}</p>
                                <p class="card-text">{{tempSmestaj.adresa.adresa}}</p>
                                
                                <div class="starClass">
                                    <StarRating style="margin-bottom:10px" read-only v-model="tempSmestaj.ocena" :star-size="30"/>
                                </div>
                                <div>
                                    <button v-on:click="smestajDetails(tempSmestaj.id)" class="btn btn-primary">Detalji</button>
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
        <section style="margin-left: 10px; margin-bottom:200px;">
            <div id="comment-section" style="margin-left:30px">
            <div>
                <div  v-for="(tempComment, index) in profile.ocene" v-bind:key="index" class="comments-box">
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
                <div style="margin-left: 200px;margin-top:100px" v-show="profile.ocene.length == 0 ">
                    <h1>Trenutno nema ocena</h1>
                </div>
            </div>
        </div>
        </section>
    </div>

</template>

<script>

import dataService from '../services/dataService';
import StarRating from 'vue-star-rating';
import parserMixin from '@/mixins/mixin';
import moment from 'moment'

export default{
    components:{
        StarRating
    },
    data(){
        return{
            smestajList:[],
            profile:{
                // korIme:'tempusername',
                // ime:'Momcilo',
                // prezime:'Milinovic',
                // ocena:4,
                // email:'temp@gmail.com',
                smestajList:[],
                ocene:[],
            },
            ocena:{
                ocena:0,
                datum:'',
            },
            role:'',
            userId:'',
            canRate:''
        }
    },
    methods:{
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
        rateHost(){
            console.log("OCENI Host")
            console.log("DATA OCENA: " + this.ocena.ocena);
            //this.ocenaSmestaja.ocena = rating;
            this.ocena.datum = moment(new Date()).format("YYYY-MM-DD");
            dataService.giveRatingToHost(this.userId, this.$route.params.id, this.ocena).then(response => {
                console.log("uspesno ocenjeno");
            });
        },
    },
    async created(){
        console.log("ID VLASNIKA SMESTAJA: " + this.$route.params.id);
        try{    

            let userObj = parserMixin.methods.parseXmlJwt();
            //this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;


            await  dataService.getOwnerById(this.$route.params.id).then(response => {
                console.log("Profil korisnika: " + JSON.stringify(response.data));
                this.profile = response.data;
                //console.log("BROJ SMESTAJA: " + this.profile.smestajList.length);
               // this.smestajList = this.profile.smestajList;
                // dataService.getAllSmestajByOwnerId(this.$route.params.id).then(response => {
                //     console.log("Dobavljen smestaj vlasnika ID-a: " + this.$route.params.id);
                //     this.smestajList = response.data;
                //     console.log(JSON.stringify(this.smestajList))
                //     console.log("Broj smestaja: " + this.smestajList.length);
                // })
            });

            await dataService.canGiveRating(this.userId, this.$route.params.id).then(response => {
                this.canRate = response.data;
                console.log("Can give rating : "+this.canRate);
                if(this.canRate===true){
                    for(let i=0 ; i<this.profile.ocene.length;i++){
                        console.log("poredjenje ids : "+this.profile.ocene[i].gost+" : "+this.userId.substr(1,this.userId.length-2))
                        if(this.profile.ocene[i].gost===this.userId.substr(1,this.userId.length-2)){
                            this.ocena.ocena=this.profile.ocene[i].ocena;
                            break;
                        }
                    }
                }
            });

        }catch(error){
            console.log('doslo je do greske');
            console.log(error.message);
        }   
        
    },
    computed:{

    }
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
    width:90%;
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
.starClass{
    display: inline-block;
}

</style>