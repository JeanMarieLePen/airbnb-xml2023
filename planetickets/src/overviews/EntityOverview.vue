<template>
    <div class="container" style="margin-top: 30px;">
        <div>
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
                        <h5 class="header5">Br slobodnih mesta</h5>
                        <h4>{{this.let.brSlobMesta}}</h4>
                        <h5 class="header5">Datum i vreme polaska</h5>
                        <h4>{{formatDate(this.let.datumIVreme)}}</h4>
                </li>
            </ul>
        </div>
        <div  v-if="this.userObj.role=='REG_KOR'">
            <h5 class="header5"  v-if="this.userObj.role=='REG_KOR'">Nova Rezervacija:</h5>
            <ul id="services" class="list-group">
                <li class="list-group-item" >
                    <h5 class="header5">BrojKarata:</h5>
                    <input class="input-class" v-model="this.rez.brojKarata" type="number" min="1" @input="this.izracunaj()">
                </li>
                <li class="list-group-item" >
                    <h5 class="header5">Ukupna cena:</h5>
                    <h4>{{this.let.cena}}e* {{this.rez.brojKarata}}={{this.cenaUk}}e</h4>
                    <button class="btn  btn-danger marg float-center" @click="novaRez()">Rezervisi</button>
                </li>
            </ul>
            <div v-if="messages.successResponse" v-html="messages.successResponse" class="alert alert-success"></div>
            <div v-if="messages.errorResponse" v-html="messages.errorResponse" class="alert alert-danger"></div>
        </div>
        <div v-if="userObj.role=='ADMINISTRATOR'">
            <button style="margin-top: 30px;" v-on:click="ObrisiLet(this.userObj.id,this.let.id)" class="btn btn-primary">Ukloni</button>
        </div>

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
                messages:{
                    successResponse:'',
                    errorResponse:''
                },
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
            }
        },
        created(){
            this.idLeta=this.$route.params.id;
            console.log("ID LETA JE: " + this.idLeta);
            this.getFlight(this.idLeta);
            parserMixin.methods.checkLoginStatus();
            this.userObj = parserMixin.methods.parseXmlJwt();
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
                this.cenaUk=this.let.cena*this.rez.brojKarata;
            },
            novaRez(){
                this.rez.let=this.let.id;
                this.rez.kupac=this.userObj.id;
                dataService.rezervisi(this.rez).then(response => {
                    if(response.status === 200){
                        this.messages.successResponse = `<h4>Uspesna rezervacija.</h4>`;
                        setTimeout(() => {
                            this.messages.successResponse = '';
                        }, 5000);
                        setTimeout(() => {
                            this.$router.push(`/dash`);
                        }, 5050);
                        
                    }else{
                        this.messages.errorResponse = '<h4>Rezervacija nije uspela.</h4>';
                        setTimeout(() => {
                            this.messages.errorResponse = '';
                        }, 5000);
                    }
                    
                })
            },
            ObrisiLet(idUser, idLet){
                dataService.ukloniLet(idLet).then(response => {
                    this.$router.push(`/dash`);
                })
            },

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