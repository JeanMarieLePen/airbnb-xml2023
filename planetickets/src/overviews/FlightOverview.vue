<template>
   <!--<h1> let:{{this.let.id}}</h1>-->
   <!-- <h3>{{this.letString}}</h3>-->
    <div  class="container" style="margin-top: 30px;">
        <form>
            <ul id="services" class="list-group">
                <li class="list-group-item" >
                        <h5 class="header5">Adresa polaska</h5>
                        <h4>{{this.let.lokOd}}</h4>
                        <h5 class="header5">Adresa dolaska</h5>
                        <h4>{{this.let.lokDo}}</h4>
                        <h5 class="header5">Broj leta</h5>
                        <h4>{{this.let.brojLeta}}</h4>
                        <h5 class="header5">Kapacitet</h5>
                        <h4>{{this.let.kapacitet}}</h4>
                        <h5 class="header5">Br slobonih mesta</h5>
                        <h4>{{this.let.brSlobMesta}}</h4>
                        <h5 class="header5">Datum i vreme polaska</h5>
                        <h4>{{formatDate(this.let.datumIVreme)}}</h4>
                </li>
            </ul>
        </form>
        <!--Formu ispred treba sakriti ako je admin ili nije registrovan-->
        <form v-if="this.userObj.role=='REG_KOR'">
            <h5 class="header5"  v-if="this.userObj.role=='REG_KOR'">Nova Rezervacija:</h5>
            <ul id="services" class="list-group">
                <li class="list-group-item" >
                    <h5 class="header5">BrojKarata:</h5>
                    <input class="input-class" v-model="this.rez.brojKarata" type="number" min="1"  @input="this.izracunaj()">
                </li>
                <li class="list-group-item" >
                    <h5 class="header5">Ukupna cena:</h5>
                    <h4>{{this.let.cena}}e* {{this.rez.brojKarata}}={{this.cenaUk}}e</h4>
                    <button class="btn  btn-danger marg float-center" @click="novaRez()">Rezervisi</button>
                </li>
            </ul>
            <div v-if="this.messages.successMsg" v-html="messages.successMsg" class="alert alert-success"></div>
            <div v-if="this.messages.failureMsg" v-html="messages.failureMsg" class="alert alert-danger"></div>

        </form>
        <form v-if="this.userObj.role=='ADMINISTRATOR'">
            <div  class="container">
                <button v-if="userObj.role=='ADMINISTRATOR'" style="margin-top: 30px;" v-on:click="ObrisiLet(this.userObj.id,this.let.id)" class="btn btn-primary">Ukloni</button>
            </div>
        </form>

        
    </div>
</template>

<script>
import dataService from '../services/dataService'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import parserMixin from '@/mixins/mixin'
import moment from 'moment';

    export default{
        components:{},
        data(){
            return{
                message:'',
                idLeta : '',
                letString : '',
                let:{},
                rez:{
                    let: "",
                    kupac: "",
                    brojKarata:"1"
                    
                },
                cenaUk:"",
                userObj:{},
                messages:{
                    successMsg:'',
                    failureMsg:'',
                }

            }
        },
        created(){
            this.idLeta=this.$route.params.id;
            this.getFlight(this.idLeta);
            parserMixin.methods.checkLoginStatus();
            this.userObj = parserMixin.methods.parseXmlJwt();
            this.izracunaj();
        },
        methods:{
            formatDate(date){
                let tempDate = moment(date).format('MMMM Do YYYY, h:mm:ss a')
                return tempDate;
            },
            getFlight(id){
                try{
                    dataService.getFlight(id).then(response=>{
                        this.letString=JSON.stringify(response.data);
                        this.let=response.data;
                        console.log("Let: " + JSON.stringify(response.data));
                        this.izracunaj();
                    })
                }catch(error){
                    console.log("GRESKA: " + error.message);
                }
            },
            izracunaj(){
                if(this.rez.brojKarata>this.let.brSlobMesta){
                    this.rez.brojKarata=this.let.brSlobMesta;
                }
                this.cenaUk=this.let.cena*this.rez.brojKarata;
            },
            novaRez(){
                this.rez.let=this.let.id;
                this.rez.kupac=this.userObj.id;
                try{
                    dataService.rezervisi(this.rez).then(response=>{
                       
                        console.log(response.data)
                        if(response.data!=null)
                        this.failPor();
                        else this.uspehPor();
                    })
                }catch(error){
                    console.log("GRESKA PRI REZERVACIJI: " + error.message);
                }
            },
            ObrisiLet(idUser, idLet){
                dataService.ukloniLet(idLet);
                this.$router.go();
            },
            uspehPor(){
                this.messages.successMsg = '<h2>Uspesno Dodavanje Rezervacije.</h2>';

                            setTimeout(() => {
                                this.messages.successMessage = '';
                                this.$router.go();
                                
                            }, 4000)
            },
            failPor(){
                this.messages.successMsg = '<h2>Neuspelo dodavnje rezervacije.</h2>';

                            setTimeout(() => {
                                this.messages.successMessage = '';
                                this.$router.go();
                                
                            }, 4000)
            }

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