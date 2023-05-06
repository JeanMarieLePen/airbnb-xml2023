<template>
    <div>
        <div style="margin-left:10px">
            <h2>Izmena smestaja ID-a {{smestaj.id}}</h2>
            <p>Popunite trazene karakteristike kako biste kreirali entitet</p>
        </div>
        <div>
            <div style="width:50%" class="table-responsive">
                <table class="tbl">
                    <tr>
                        <td>Maksimalni kapacitet</td>
                        <td><input class="input-class" v-model="smestaj.maxGosti" type="text" placeholder="Unesite maksimalan broj gostiju..." ></td>
                    </tr>
                    <tr>
                        <td>Minimalni kapacitet</td>
                        <td><input v-model="smestaj.minGosti" class="input-class" type="text" placeholder="Unesite minimalni broj gostiju..." ></td>
                    </tr>
                     <tr>
                        <td>Adresa</td>
                        <td><input class="input-class" v-model="smestaj.adresa.adresa" type="text" placeholder="Unesite adresu smestaja..." ></td>
                    </tr>
                    <tr>
                        <td>Cenovnik:</td>
                        <td>
                            <button @click="cenovnikForm()" v-show="this.showCenovnikForm == false" class="btn btn-success">+Dodaj</button>
                            <button id="zatvori-cenovnik" @click="cenovnikForm()" v-show="this.showCenovnikForm == true" class="btn btn-danger">Zatvori</button>
                        </td>
                    </tr>
                    <tr v-show="showCenovnikForm">
                        <td colspan="2">
                            <table style=" margin-bottom:100px;">
                                <thead>
                                    <tr>
                                        <td colspan="2" style="text-align: center;">Novi cenovnik</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Cena: </td>
                                        <td>
                                            <input type="number" v-model="smestaj.cenovnik.cena">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Praznicna cena: </td>
                                        <td>
                                            <input type="number" v-model="smestaj.cenovnik.cenaPraznik">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Vikend cena: </td>
                                        <td>
                                            <input type="number" v-model="smestaj.cenovnik.cenaVikend">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Cena leto: </td>
                                        <td>
                                            <input type="number" v-model="smestaj.cenovnik.cenaLeto">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Cena po smestaju:
                                        </td>
                                        <td>
                                            <div style="margin-left: 50px;">
                                                <input type="checkbox" @change="selectRezim()" v-model="smestaj.cenovnik.poSmestaju">
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot >
                                    <tr>
                                        <td colspan="2" style="text-align: center;">
                                            <div>
                                                <button @click="addCenovnik()" class="btn btn-warning">Izmeni</button>
                                            </div>
                                            <div v-if="messages.successCenovnik" v-html="messages.successCenovnik" class="alert alert-success"></div>
                                            <div v-if="messages.errorCenovnik" v-html="messages.errorCenovnik" class="alert alert-danger"></div>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </td>
                        
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table style="margin-bottom:50px;">
                                <thead>
                                    <tr>
                                        <td colspan="3" style="text-align: center;">POGODNOSTI</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <ul>
                                                <li @click="slctPogodnost(index)" :class="{backgroundColor: 'mycolor'}" v-for="(tempPogodnost, index) in svePogodnosti" :key="index">
                                                    {{tempPogodnost.naziv}} 
                                                </li>
                                            </ul>
                                        </td>
                                        <td style="width:10%;">
                                            <button @click="addPogodnost()">
                                                <div>
                                                    <img style="width:32px; height:32px;" src="../assets/double-right.png">
                                                </div>
                                            </button>
                                            <button @click="removePogodnost()">
                                                <div>
                                                    <img style="width:32px; height:32px;" src="../assets/double-left.png">
                                                </div>
                                            </button>
                                        </td>
                                        <td>
                                            <ul>
                                                <li @click="slctPogodnost2(index)" :class="{backgroundColor: 'mycolor'}" v-for="(tempPogodnost, index) in smestaj.pogodnosti" :key="index">
                                                    {{tempPogodnost.naziv}} 
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot style="height:50px;">
                                    <tr>
                                        <td colspan="3" style="text-align:center">
                                            <div v-if="messages.successPogodnost" v-html="messages.successPogodnost" class="alert alert-success"></div>
                                            <div v-if="messages.errorPogodnost" v-html="messages.errorPogodnost" class="alert alert-danger"></div>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>Slike:</td>
                        <td>
                            <div>
                                <vueperslides id="slajdovi" v-if="smestaj.slike.length > 0" fade fixed-height="600px">
                                    <vueperslide v-for="(slide, i) in smestaj.slike" :key="i">
                                        <template v-slot:content>
                                            <img :src="slide" style="width:400px;height:600px">
                                        </template>
                                    </vueperslide>
                                </vueperslides>
                            </div>
                            <div style="display: flex; justify-content: flex-end">
                                <!-- <label for="file-input">
                                    <button class="btn btn-warning">Choose files</button>
                                </label> -->
                                <input id="file-input" @change="uploadImage" multiple style="margin-top:20px;margin-bottom:20px;" type="file">
                                <button class="btn btn-primary" style="margin:10px; height:35px; font-weight:400" @click="ponistiIzbor()">Undo</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nedostupni termini</td>
                        <td>
                            <button @click="terminiForm()" v-show="this.showTerminiForm == false" class="btn btn-success">+Dodaj</button>
                            <button id="zatvori-cenovnik" @click="terminiForm()" v-show="this.showTerminiForm == true" class="btn btn-danger">Zatvori</button>
                        </td>
                    </tr>
                    <tr v-show="smestaj.nedostupni.length > 0">
                        <td colspan="2">
                            <ul>
                                <li @click="slctDatum(index)" :class="{backgroundColor: 'mycolor'}" v-for="(tempDatum, index) in smestaj.nedostupni" :key="index">
                                Datum od: {{setDatumForm(tempDatum.pocetak)}}; Datum do: {{setDatumForm(tempDatum.kraj)}}
                                
                                </li>
                            </ul>
                        </td>
                    </tr>
                    <tr v-show="showTerminiForm">
                        <td colspan="2">
                            <table style=" margin-bottom:100px;">
                                <thead>
                                    <tr>
                                        <td colspan="2" style="text-align: center;">Nedostupni termini</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <datepicker no-disabled-range :disabled-dates="disabledDates" @update:model-value="updateDatePicker" v-model="termin" range>

                                            </datepicker>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot >
                                    <tr>
                                        <td colspan="2" style="text-align: center;">
                                            <div style="display: flex; margin:5px;">
                                                <div style="margin-right: 5px;">
                                                    <button @click="addTermin()" class="btn btn-warning">Dodaj</button>
                                                </div>
                                                <div>
                                                    <button :disabled="smestaj.nedostupni.length === 0" @click="removeTermin()" class="btn btn-primary">Ukloni</button>
                                                </div>
                                            </div>
                                            
                                            <div v-if="messages.successTermin" v-html="messages.successTermin" class="alert alert-success"></div>
                                            <div v-if="messages.errorTermin" v-html="messages.errorTermin" class="alert alert-danger"></div>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </td>
                        
                    </tr>
                    <tfoot style="margin-top:100px;">
                        <tr>
                            <td colspan="2">
                                <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success"></div>
                                <div v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-warning"></div>
                            </td>
                        </tr>
                        <tr>
                            
                            <td colspan="2" class="text-center">
                                <button class="btn btn-warning" style="font-weight:600; margin:20px;" @click="editSmestaj()">Izmeni</button>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>



</template>

<script>

import parserMixin from '@/mixins/mixin' 
import dataService from '../services/dataService'
import VueDatePicker from '@vuepic/vue-datepicker';
import moment from 'moment';

import {YandexMap, YandexMarker} from "vue-yandex-maps"
import { loadYmap } from "vue-yandex-maps";
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'

import { addDays, subDays } from 'date-fns';



export default {
    components:{
        datepicker:VueDatePicker,
        YandexMap, YandexMarker,
        vueperslides : VueperSlides,
        vueperslide :VueperSlide,
    },
    async mounted(){
        await loadYmap({ ...this.settings, format: "json", debug: true});
    },
    data(){
        return{
            selectedDatumId: -1,
            settings: {
                apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
                lang: "en_US",
                coordorder: "latlong",
                enterprise: false,
                version: "2.1",
            },
            messages:{
                successResponse:'',
                errorResponse:'',
                successCenovnik:'',
                errorCenovnik:'',
            },
            // this.id = id;
            // this.version = version;
            // this.vlasnik = vlasnik;
            // this.adresa = adresa;
            // this.pogodnosti = pogodnosti;
            // this.slike = slike;
            // this.minGosti = minGosti;
            // this.maxGosti = maxGosti;
            // this.cenovnik = cenovnik;
            // this.rezervacije = rezervacije;
            // this.listaOcena = listaOcena;



            // this.poSmestaju = poSmestaju;
            // this.cena = cena;
            // this.cenaPraznik = cenaPraznik;
            // this.cenaVikend = cenaVikend;
            // this.cenaLeto = cenaLeto;

            termin:[],

            svePogodnosti:[],
            cenovnikOptions:["Po smestaju", "Po osobi"],
            showCenovnikForm:false,
            showTerminiForm:false,
            disabledDates:[],
            smestaj:{
                vlasnikId: -1,
                adresa:{

                },
                pogodnosti:[],
                slike:[],
                minGosti:-1,
                maxGosti:-1,
                cenovnik:{

                },
                nedostupni: [],

                // ovo su nullovi
                // rezervacije:[],
                // listaOcena:[],
            },
            lokacija : new Object(),
            slike:[],

            user:{
                username:'',
            },
            role:'',
            userId:-1,
        }
    },
    computed:{
        
    },
    methods:{
        addPogodnost(){
            if(this.selectedPogodnost != -1){
                let filteredList = this.smestaj.pogodnosti.filter((tmp) => {
                    return tmp.naziv == this.svePogodnosti[this.selectedPogodnost].naziv;
                });
                console.log("FILTERED LIST: " + JSON.stringify(filteredList));
                if(filteredList.length == 0){
                    this.smestaj.pogodnosti.push(this.svePogodnosti[this.selectedPogodnost]);
                    console.log("DODATA POGODNOST: " + JSON.stringify(this.smestaj.pogodnosti));
                    this.messages.successPogodnost = "<h4>Uspesno dodata pogodnost.</h4>";
                    setTimeout(() => {
                        this.messages.successPogodnost = '';
                    }, 3500);
                }else{
                    this.messages.errorPogodnost = "<h4>Pogodnost vec dodata.</h4>"
                    console.log("NIJE DODATA POGODNOST; PODESENA PORUKA")
                    setTimeout(() => {
                        this.messages.errorPogodnost = '';
                    }, 3500);
                }
            }
            
        },
        filterNaziv(naziv){
            return naziv == this.svePogodnosti[this.selectedPogodnost].naziv;
        },
        removePogodnost(){
            console.log("BROJ POGODNOSTI: " + this.smestaj.pogodnosti.length)
            
            if(this.selectedPogodnostUkloni != -1){
                console.log("SELEKTOVANA POGODNOST[REMOVE]: " + JSON.stringify(this.smestaj.pogodnosti[this.selectedPogodnostUkloni]));
                for(let i = 0; i < this.smestaj.pogodnosti.length; i++){
                    if(this.smestaj.pogodnosti[i] == this.smestaj.pogodnosti[this.selectedPogodnostUkloni]){
                        console.log("POGODNOST ZA BRISANJE: " + this.smestaj.pogodnosti[this.selectedPogodnostUkloni]);
                        this.smestaj.pogodnosti.splice(this.selectedPogodnostUkloni, 1);
                        break;
                    }
                }
            }
            
        },
        slctPogodnost(idx){
            console.log("SELEKTOVANA POGODNOST: " + idx);
            this.selectedPogodnost = idx;
        },
        slctPogodnost2(idx){
            console.log("SELEKTOVANA POGODNOST: " + idx);
            this.selectedPogodnostUkloni = idx;
        },
        removeTermin(idx){
            let tmp = this.smestaj.nedostupni[this.selectedDatumId];
            console.log("NA BRISANJE IDE INTERVAL: " + JSON.stringify(this.smestaj.nedostupni[this.selectedDatumId]));
            if(tmp != null){
                this.freeUpDates(tmp);
                this.smestaj.nedostupni.splice(this.selectedDatumId, 1);
                this.selectedDatumId = -1;
            }
        },
        freeUpDates(intrv){
            console.log("INTERVAL ZA OSLOBADANJE: " + JSON.stringify(intrv))
            let interval = [];
            interval.push(intrv.pocetak, intrv.kraj);
            let tempDisabledDates = this.disabledDates;
            let tempDisabledDates2 = tempDisabledDates;
            let tempDisabledDates3 = [];

            let datum1 = new Date(interval[0]);
            let datum2 = new Date(interval[1]);
            const diffTime = Math.abs(datum2 - datum1);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
            let brDana = diffDays;
            console.log("BROJ DANA JE: " + brDana);

            let pocetni1 = new Date(tempDisabledDates[0]);
            let pocetni2 = new Date(interval[0]);
            console.log("POCETNI DATUM 1: " + pocetni1);
            console.log("POCETNI DATUM 2: " + pocetni2);
            console.log("BROJ DANA ZA PROVERU: " + tempDisabledDates.length)


            let datumiNiz = this.disabledDates.slice();

            let datumiZaOslobadjanje = [];
            for(let z = 0; z <= brDana; z++){
                let tmpDate = addDays(new Date(pocetni2), z)
                datumiZaOslobadjanje.push(tmpDate);
            }
            let nultiNiz = [];
            for(let z = 0; z < tempDisabledDates.length; z++){
                nultiNiz.push(new Date(tempDisabledDates[z]));
            }
            console.log("NIZ DATUMA KOJI SE OSLOBADJAJU: " + JSON.stringify(datumiZaOslobadjanje));
            console.log("NULTI NIZ: " + JSON.stringify(nultiNiz));

            let i = 0;
            while(i <= nultiNiz.length){
                let date1 = new Date(nultiNiz[i]);
                let j = 0;
                let izbacen = false;
                while(j <= brDana){
                    let date2 = new Date(datumiZaOslobadjanje[j]);
                    if(date1.getTime() == date2.getTime()){
                        console.log("UKLONJEN DATUM: " + date1); 
                        tempDisabledDates3.push(nultiNiz[i]);
                        nultiNiz.splice(i, 1);
                        izbacen = true;
                    }
                    j++;
                }
                if(izbacen == true){

                }else{
                    i++;
                }
                // i++;
            }
            console.log("DATUMI ZA UKLANJANJE: " + JSON.stringify(tempDisabledDates3))
            console.log("OCISCENA LISTA: " + JSON.stringify(nultiNiz))
            this.disabledDates = nultiNiz;
        },
        slctDatum(idx){
            console.log("SELEKTOVAN ID DATUMA: " + idx);
            this.selectedDatumId = idx;
        },
        setDatumForm(datum){
            return moment(datum).format("YYYY-MM-DD");
        },
        addTermin(){
            // this.smestaj.nedostupni.push(this.termin);
            let tempNedostupniTermin = {
                pocetak : this.termin[0],
                kraj : this.termin[1]
            }
            this.smestaj.nedostupni.push(tempNedostupniTermin);
            console.log("DATUM POCETAK: " + JSON.stringify(new Date(this.termin[0])));
            console.log("DATUM KRAJ: " + JSON.stringify(new Date(this.termin[1])));
            let cntr = 0;
            
            let datum1 = new Date(this.termin[0]);
            let datum2 = new Date(this.termin[1]);
            const diffTime = Math.abs(datum2 - datum1);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
            let brDana = diffDays;
            console.log("BROJ DANA JE: " + brDana);

            for(let date1 = new Date(this.termin[0]); date1 < new Date(this.termin[1]); date1.setDate(date1.getDate() + 1)){
                // cntr += 1;
                // console.log("ITERACIJA: " + cntr);
                // this.disabledDates.push(date1);
                // this.addDate(date1);
            }
            // this.disabledDates.push(this.termin);

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
            console.log("TEMP DATUMI: " + JSON.stringify(tempDatumi));

            //  this.disabledDates = [subDays(new Date(), 1), new Date(), addDays(new Date(), 1)];
            //  console.log("DATUMI: " + JSON.stringify(this.disabledDates));
        },
        addDate(date1){
            console.log("DODAVANJE")
            subDays(date1, 1);
            addDays(date1, 1);
            this.disabledDates.push(date1);
        },
        updateDatePicker(selectedDate){
            console.log("VAL1: " + selectedDate);
            this.termin = selectedDate
            console.log(JSON.stringify(this.termin))
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
                    this.smestaj.slike.push(reader.result);
                    this.slike.push(reader.result);
                });
            }
        },
        ponistiIzbor(){
            // console.log("BROJ SLIKA: " + this.slike.length)
            // let cntr1 = this.slike.length;
            // for(let i = 0; i < cntr1; i++){
            //     console.log("IZBACENA SLIKA: " + this.slike.length)
            //     this.slike.pop();
            // }
            // let cntr2 = this.smestaj.slike.length;
            //  for(let i = 0; i < cntr2; i++){
            //     console.log("IZBACENA SLIKA: " + this.smestaj.slike.length)
            //     this.smestaj.slike.pop();
            // }
            this.slike.pop();
            this.smestaj.slike.pop();
        },
        cenovnikForm(){
            this.showCenovnikForm = !this.showCenovnikForm;
            console.log("PRIKAZ CENOVNIK: " + this.showCenovnikForm)
        },
        terminiForm(){
            this.showTerminiForm = !this.showTerminiForm;
            console.log("PRIKAZ TERMIN-FORME: " + this.showTerminiForm)
        },
        addCenovnik(){
            console.log("KREIRAN CENOVNIK: " + JSON.stringify(this.smestaj.cenovnik));
            if(this.smestaj.cenovnik.cena >= 0 && this.smestaj.cenovnik.cenaPraznik >= 0 && this.smestaj.cenovnik.cenaVikend >= 0
            && this.smestaj.cenovnik.cenaLeto >= 0){
                this.messages.successCenovnik = "<h4>Uspesno kreiran cenovnik</h4>";
                setTimeout(() =>{
                    this.messages.successCenovnik = "";
                }, 3500);
                setTimeout(() => {
                    document.getElementById("zatvori-cenovnik").click();
                }, 3550);
            }else{
                this.messages.errorCenovnik = "<h4>Greska pri kreiranju cenovnika!</h4>";
                setTimeout(() =>{
                    this.messages.errorCenovnik = "";
                }, 3500)
            }
        },
        async getCoordsFromAdresa(adresa){
            return ymaps.geocode(adresa).then((result) => {
                let locationCoords = {
                    coords: result.geoObjects.get(0).geometry.getCoordinates(),
                };
                this.lokacija.lat = locationCoords.coords[0];
                this.lokacija.lng = locationCoords.coords[1];
                this.smestaj.adresa.lat = this.lokacija.lat;
                this.smestaj.adresa.lng = this.lokacija.lng;
                console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
            });
        },
        selectRezim(){
            console.log("PO SMESTAJU: " + this.smestaj.cenovnik.poSmestaju)
        },
        async editSmestaj(){
            await this.getCoordsFromAdresa(this.smestaj.adresa.adresa);
            console.log("ADRESA: " + JSON.stringify(this.smestaj.adresa));
            console.log("NEDOSTUPNI[EDIT method]: " + JSON.stringify(this.smestaj.nedostupni))
            try{
                dataService.editSmestaj(this.smestaj.id, this.userId, this.smestaj).then(response => {
                    console.log("IZMENJEN SMESTAJ");
                    if(response.status === 200){
                        this.messages.successResponse = "<h4>Uspesno izmenjen smestaj</h4>";
                    }else{
                        this.messages.errorResponse = "<h4>Doslo je do greske</h4>";
                    }
                });
            }catch(error){

            }
        },
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
    },
    async created(){
        console.log("ID SMESTAJA KOJI SE TRAZI: " + this.$route.params.id);
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
            console.log("ID VLASNIKA JE: " + this.userId);
            this.smestaj.vlasnikId = this.userId;
            try{
                console.log("DOBAVLJANJE POGODNOSTI!");
                dataService.getAllPogodnosti().then(response => {
                    console.log("DOBAVLJENE POGODNOSTI: " + response.data.length);
                    console.log(JSON.stringify(response.data));
                    this.svePogodnosti = response.data;
                });
            }catch(error){

            }
        }
        await this.getSmestaj();
    },

    
}
</script>


<style scoped>
.tbl{
    font-family: Arial, Helvetica, sans-serif;
    margin-left: 20px;
}
.tbl tr:nth-child(even){
    background-color: rgba(127, 255, 212, 0.548);
}
.tbl td:nth-child(1){
    width:30%;
    font-size: 20px;
    font-weight: 500;
}
.tbl td:nth-child(2){
    width:100%;
}
.tbl td>input{
    width:100%;
}

/* #file-input{
    display: none;
} */
</style>