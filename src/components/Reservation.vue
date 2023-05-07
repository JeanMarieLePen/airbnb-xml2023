<template>
    <div style="margin-left:50px; margin-top:30px;width:50%">
        <h2>Rezervisanje smestaja ID-a: {{smestaj.id}}</h2>
        <!-- class="table table-borderless" -->
            <table>
                <thead>
                    <tr style="text-align:center;">
                        <th colspan="2">Osnovi podaci o smestaju</th>
                    </tr>
                </thead>
                <tr>
                    <td>
                        <label>Vlasnik ID:</label>
                    </td>
                    <td>
                        <label>{{smestaj.vlasnikId}}</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Ocena:</label>
                    </td>
                    <td>
                       <star-rating :star-size="30" read-only style="margin-left:10px;display:inline-block"  v-model:rating="smestaj.ocena"></star-rating>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Adresa:</label>
                    </td>
                    <td>
                        <div v-if="smestaj.adresa">
                            <label>{{smestaj.adresa.adresa}}</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Cena po smestaju:</td>
                    <td>
                        <div v-if="smestaj.cenovnik.poSmestaju">DA</div>
                        <div v-if="!smestaj.cenovnik.poSmestaju">NE</div>
                    </td>
                </tr>
                
                <tr>
                    <td style="vertical-align: top;">
                        <label>Pogodnosti: </label>
                    </td>
                    <td>
                        <ul style="width:100%; min-height:50px; max-height: 100px; overflow: auto">
                            <li v-for="(tempPogodnost, index) in smestaj.pogodnosti" v-bind:key="index">{{tempPogodnost.naziv}}</li>
                        </ul>
                    </td>
                </tr>
                <tr >
                    <td colspan="2"><div style="margin-top:30px;"></div></td>
                </tr>
                
                <tr style="font-size:20px;color:white;background-color:darkgreen;">
                    <td colspan="2">
                        <p>
                            FORMA ZA REZERVACIJU
                        </p>
                    </td>
                </tr>
                <tr>
                    <td>
                        Broj osoba:
                    </td>
                    <td>
                        <input type="number" style="width:100%" :placeholder="inputBrOsobaPlaceholder" v-model="reservation.brojGostiju" :min="smestaj.minGosti" :max="smestaj.maxGosti">
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Odabir termina: </label>
                    </td>
                    <td>
                        <div id="dtpckr" style="width:100%;"> 
                            <datepicker :min-date="new Date()" no-disabled-range :disabled-dates="disabledDates" @update:model-value="updateDatePicker" v-model="termin" range>

                            </datepicker>
                        </div>
                        <div v-html="messages.errorDatum" v-if="messages.errorDatum" style="margin:10px;" class="alert alert-danger"></div>
                    </td>
                </tr>

                <tr style="border-top: 1pt solid black;">
                    <td>
                        <label style="font-size:25px;font-weight:800">Ukupna Cena: </label>
                    </td>
                    <td>
                        <label v-if="smestaj.cenovnik.poSmestaju" style="font-size:25px;">
                            <!-- {{suma}} -->
                            {{calcCena1}}
                        </label>
                        <label v-if="!smestaj.cenovnik.poSmestaju" style="font-size:25px;">
                            <!-- {{suma}} -->
                            {{calcCena2}}
                        </label>
                    </td>
                </tr>
                <tfoot>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <button @click="createReservation()" class="btn btn-success">Rezervisi</button>
                        </td>
                    </tr>
                    <tr v-if="messages.errorMessage || messages.successMessage || messages.errorMessage" style="height:100px;">
                        <td colspan="3">
                            <div style="margin-bottom:20px;">
                                <div style="margin-bottom:20px;" v-if="messages.errorUnosDatuma" v-html="messages.errorUnosDatuma" class="alert alert-danger">

                                </div>
                                <div style="margin-bottom:20px;" v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success">

                                </div>
                                <div style="margin-bottom:20px;" v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-danger">

                                </div>
                            </div>
                        </td>
                    </tr>
                </tfoot>

            </table>        
    </div>
</template>

<script>

import dataService from '../services/dataService'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

import StarRating from 'vue-star-rating';
import parserMixin from '@/mixins/mixin' 

import moment from 'moment'
import { addDays, subDays } from 'date-fns';

export default{
    methods:{
        getSmestaj(){
            return dataService.getSmestaj(this.$route.params.id).then(response => {
                if(response.status === 200){
                    console.log("DOBAVLJEN SMESTAJ");
                    console.log(JSON.stringify(response.data));
                    this.smestaj = response.data;
                    let tempSlike = [];
                    for(let i = 0; i < this.smestaj.slike.length; i++){
                        tempSlike.push('data:image/png;base64,' + this.smestaj.slike[i]);
                    }
                    this.smestaj.slike = tempSlike;


                    let tempTermini = [];
                    for(let i = 0; i < this.smestaj.nedostupni.length; i++){
                        tempTermini.push({
                            pocetak: this.smestaj.nedostupni[i].pocetak,
                            kraj: this.smestaj.nedostupni[i].kraj
                        });
                    }
                    
                    this.smestaj.nedostupni = [];
                    this.smestaj.nedostupni = tempTermini;
                    
                    let nedostupniTermini = [...this.smestaj.nedostupni]
                    for(let i = 0; i < nedostupniTermini.length; i++){
                        this.ucitavanjeTermina(nedostupniTermini[i].pocetak, nedostupniTermini[i].kraj);
                    }
                    
                    // console.log("BROJ NEDOSTUPNIH TERMINA: " + tempTermini.length);
                    // this.smestaj.nedostupni = tempTermini;

                }
            });
        },
        
        ucitavanjeTermina(terminPocetak, terminKraj){
            console.log("UCITAVANJE TERMINA");
            let datum1 = new Date(terminPocetak);
            let datum2 = new Date(terminKraj);
            const diffTime = Math.abs(datum2 - datum1);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
            let brDana = diffDays;
            console.log("BROJ DANA JE: " + brDana);

            let tempDatumi = [];
            let disblDatesConcat = this.disabledDates;
            for(let i = 0; i <= brDana; i++){
                let tmpDate = addDays(new Date(datum1), i)
                tempDatumi.push(tmpDate);
            }
            for(let i = 0; i < tempDatumi.length; i++){
                disblDatesConcat.push(tempDatumi[i]);
            }
            this.disabledDates = disblDatesConcat;
            console.log("NEDOSTUPNI disblDatesConcat: " + JSON.stringify(disblDatesConcat));
            console.log("DISABLED DATES[UCITAVANJE]: " + JSON.stringify(this.disabledDates));
        },
        updateDatePicker(selectedDate){
            console.log("VAL1: " + selectedDate);
            this.termin = selectedDate;
            this.reservation.odDatum = this.termin[0];
            this.reservation.doDatum = this.termin[1];
            console.log(JSON.stringify(this.reservation));
            this.numberOfDays();
        },
        
        createReservation(){
            if(this.reservation.odDatum == null || this.reservation.doDatum == null || this.reservation.brojGostiju <= 0){
                console.log("GRESKA");
                this.messages.errorParametri = "<h4>Niste pravilno uneli sve parametre forme.";
                setTimeout(() => {
                    this.messages.errorParametri = '';
                }, 5000); 
            }else{
                dataService.makeReservation(this.reservation, this.userId, this.$route.params.id).then(response => {
                    if(response.status === 200){
                        this.messages.successResponse = '<h4>Uspesno ste rezervisali smestaj.';
                        setTimeout(() => {
                            this.messages.successResponse = '';
                        }, 5000);
                        setTimeout(() => {
                            this.$router.push(`/dash`);
                        }, 5000);
                    }else{
                        this.messages.errorResponse = '<h4>Doslo je do greske. Pokusajte ponovo.';
                        setTimeout(() => {
                            this.messages.errorResponse = '';
                        }, 5000);
                    }
                });
            }
        },
        numberOfDays(){
            let pocetak = moment(this.termin[0],).format('YYYY-MM-DD');
            let temp = moment(this.termin[1]).format('YYYY-MM-DD');
            const diffInDays = Math.abs(moment(pocetak).diff(moment(temp), 'days'));
            console.log("RAZLIKA U DANIMA: " + diffInDays)
            // this.brojDana = diffInDays;
            this.brojDana = diffInDays + 1;
        },
        
        
    },
    data(){
        return{
            brojDana:0,
            disabledDates:[],
            messages:{
                successResponse:'',
                errorResponse:'',
            },
            smestaj:{
            },
            termin:[],
            user:{
                username:'',
            },
            role:'',
            userId:-1,

            suma : 0,

            // private Long id;
            // private SmestajDTO smestaj;
            // private KorisnikBasicDTO gost;
            // private LocalDateTime odDatum;
            // private LocalDateTime doDatum;
            // private int brojGostiju;
            // private StatusRezervacije status;
            reservation:{
                brojGostiju:null,
                odDatum:null,
                doDatum:null,
            },
        }
    },
    components:{
        datepicker:VueDatePicker,
        StarRating
    },
    computed:{
        inputBrOsobaPlaceholder(){
            return 'Najmanje ' + this.smestaj.minGosti + '; Najvise ' + this.smestaj.maxGosti + '...';
        },
        calcCena2(){
            return this.reservation.brojGostiju == null ? 0 : this.brojDana * this.reservation.brojGostiju * this.smestaj.cenovnik.cena;
        },
        calcCena1(){
            return this.brojDana * this.smestaj.cenovnik.cena;
        }
    },
    async created(){
        console.log("ID SMESTAJA KOJI SE REZERVISE: " + this.$route.params.id);
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
            console.log("ID VLASNIKA JE: " + this.userId);
            this.smestaj.vlasnikId = this.userId;
        }
        await this.getSmestaj();
    }
}
</script>

<style scoped>

ul{
    display:inline-block;
}
tr:nth-child(even){
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: #f2f2f2;
}
table{
    font-size: 20px;
}
table td:first-child{
    width: 20%;
}
td:first-child{
    font-weight: bold;
}
/* formatiranje input polja za datepicker */
.vdpr-datepicker .my_class{
    width:100% !important;
}
ul{
    display:inline-block;
}

</style>