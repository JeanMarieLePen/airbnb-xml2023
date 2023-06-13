<template>
    <div style="width:100%" class="table">
        <h5 > Filtriranje prema statusu</h5>
        <select v-model="status">
           <option v-for="(s, index) in statusi" :key="index" >{{s}}</option>
        </select>
    </div>
    <div style="width:100%" >
        <table class="table" style="width:90%; margin-left:100;" >
            <thead>
                <th @click="tempSort('id')">ID
                    <img v-if='currentSortDir == "asc" && currentSort== "id"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "id" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('brojLeta')">Broj Leta
                    <img v-if='currentSortDir == "asc" && currentSort== "brojLeta"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "brojLeta" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('brKarata')">Broj Karata
                    <img v-if='currentSortDir == "asc" && currentSort== "brKarata"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "brKarata" ' src='../assets/down-arrow1.1.png'></th>
                <th @click="tempSort('cena')">Ukupna cena
                    <img v-if='currentSortDir == "asc" && currentSort== "cena"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "cena" ' src='../assets/down-arrow1.1.png'></th>
                <th @click="tempSort('status')">status
                    <img v-if='currentSortDir == "asc" && currentSort== "status"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "status" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th >Opcije</th>
            </thead>
            <tbody>
                    <tr v-for="(porudz, index) in sortEntities" :key="index" v-show="porudz.status==this.status || this.status=='SVE'">
                    <td>{{porudz.id}}</td>
                    <td><a href="" v-on:click="flightDetails(porudz.let)">{{porudz.letBroj}}</a></td>
                    <td>{{porudz.brojKarata}}</td>
                    <td>{{porudz.ukupnaCena}}e</td>
                    <td>{{porudz.status}}</td>
                    <td>
                            <button v-if="porudz.status.trim()==='REZERVISANA'" style="margin-left:30px;" v-on:click="ukloniNar(userObj.id,porudz.id)" class="btn btn-primary">Ukloni</button>
                   
                       
                    </td>

                    </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import dataService from '../services/dataService'
import parserMixin from '@/mixins/mixin'
import { assertCallExpression } from '@babel/types';
    export default{
        components:{},
        props:[ 
            'listaPorudzbina',
        ],
        data(){
            return{
                porudz:{},
                userObj:{},
                
                status: 'SVE',
                statusi:[
                    'SVE',
                    'REZERVISANA',
                    'OTKAZANA',
                    'ZAVRSENA',
                ],
                currentSortDir: 'asc',
                currentSort:'brojLeta',
            }
        },
        methods:{
            flightDetails(id){
            //    this.$router.push(`/flight/${id}` );
               this.$router.push(`/overview/${id}` );
            },
            ukloniNar(user, por){
                dataService.ukloniRez(user,por).then(response => {
                    console.log("UKLONJENA NARUDZBINA");
                    this.$router.go();
                });
                
            },
            tempSort(s){
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
            },
            sortiranje(tmpLista){
                let tempList = [];
                if(this.currentSort == 'id'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.id > b.id) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.id < b.id) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'brojLeta'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.let > b.let) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.let < b.let) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'brKarata'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.brojKarata > b.brojKarata) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.brojKarata < b.brojKarata) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'cena'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.cena > b.cena) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.cena < b.cena) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'status'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.status > b.status) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.status < b.status) ? 1 : -1)
                    }
                }
                return tempList;
            }
            
        },
        created(){
            parserMixin.methods.checkLoginStatus();
            this.userObj = parserMixin.methods.parseXmlJwt();
        
        },
        computed:{
            sortEntities(){
                let sortirani=this.sortiranje(this.listaPorudzbina)
            return sortirani;
            },
        },
    }
</script>
<style scoped>
.table{
    font-family: Arial, Helvetica, sans-serif;
    margin-left: 20px;
}
.table tr:nth-child(even){
    background-color: aquamarine;
}
.table td:nth-child(1){
    width:30%;
    font-size: 20px;
    font-weight: 600;
}
.table td:nth-child(2){
    width:100%;
}
.table td>input{
    width:100%;
}
</style>