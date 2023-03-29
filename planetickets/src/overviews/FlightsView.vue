<template>
   <!-- <h1>{{ this.letoviString }}</h1>
    <p>abc</p>-->
    <h1>Vase porudzbine</h1>
    <div v-show="flights">
        <porTemp v-bind:listaPorudzbina="flights"></porTemp>     
    </div>
    <div v-if="flights.length==0">            
        <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA Porudzbina JE PRAZNA</p>
    </div>
        
</template>
<script>
import dataService from '../services/dataService'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import parserMixin from '@/mixins/mixin'
import axios from 'axios'
import PorTemlate from '../components/PorudzbineTemplate.vue'
    export default{   
        components:{
            vueperslides : VueperSlides,
            vueperslide :VueperSlide,
            porTemp:PorTemlate,
        },
        data(){
            return{
                letoviString:"",
                flights:[],
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
            this.getUserFlights(this.userObj.id);
        },
        methods:{
            getUserFlights(id){
                try{
                    dataService.getUserFlights(id).then(response=>{
                        this.letoviString=response.data;
                        this.flights=response.data;
                        console.log("LETOVI: " + JSON.stringify(response.data));

                    })
                }catch(error){
                    console.log("GRESKA: " + error.message);
                }
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