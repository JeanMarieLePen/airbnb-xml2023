<template>
    <div style="width:100%" class="table">
        <table class="table" style="width:90%; margin-left:100;" >
            <thead>
                <th @click="tempSort('polazak')">Polazak
                    <img v-if='currentSortDir == "asc" && currentSort== "polazak"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "polazak"' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('polazakIz')">Polazak iz
                    <img v-if='currentSortDir == "asc" && currentSort== "polazakIz"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "polazakIz"' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('dolazakU')">Slece u
                    <img v-if='currentSortDir == "asc" && currentSort== "dolazakU"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "dolazakU"' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('brojLeta')">Broj Leta
                    <img v-if='currentSortDir == "asc" && currentSort== "brojLeta"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "brojLeta" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('cena')">Cena karte(pojedinacno)
                    <img v-if='currentSortDir == "asc" && currentSort== "cena"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "cena" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th @click="tempSort('brSlobM')">Preostalo Mesta
                    <img v-if='currentSortDir == "asc" && currentSort== "brSlobM"' src='../assets/up-arrow1.1.png'>
                    <img v-if='currentSortDir == "desc" && currentSort== "brSlobM" ' src='../assets/down-arrow1.1.png'>
                </th>
                <th v-if="userObj.role=='ADMINISTRATOR'">   Opcije 

                </th>
            </thead>
            <tbody>
                <tr v-for="letT in sortEntities" >
                    <td>{{letT.datumIVreme}}</td>
                    <td>{{letT.lokOd}}</td>
                    <td>{{letT.lokDo}}</td>
                    <td><a href="" v-on:click="flightDetails(letT.id)">{{letT.brojLeta}}</a></td>
                    <td>{{letT.cena}}</td>
                    <td>{{letT.brSlobMesta}}</td>
                    <td v-if="userObj.role=='ADMINISTRATOR'"> 
                        <button v-if="userObj.role=='ADMINISTRATOR'" style="margin-left:30px;" v-on:click="ObrisiLet(this.userObj.id,letT.id)" class="btn btn-primary">Ukloni</button>
                       <!-- <button v-if="userObj.role=='REG_KOR'" style="margin-left:30px;" v-on:click="brzaREz(letT.id)" class="btn btn-primary">BrzaRez</button>-->
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</template>
<script>
    import dataService from '../services/dataService'
    import parserMixin from '@/mixins/mixin'

    export default{
        components:{},
        props:[ 
            'listaLetova',
        ],
        data(){
            return {
                userObj:{},
                currentSortDir: 'asc',
                currentSort:'polazakIz',
                rezDTO:{
                    let:'',
                    kupac:'',
                    brojKarata:''
                }
            }

        },
        methods:{
            brzaREz(letId){
                this.
                this.rezDTO.let=letId
                dataService.rezervisi(this.rezDTO)
            },
            flightDetails(id){
               this.$router.push(`/flight/${id}` );
            },
            ObrisiLet(idUser, idLet){
                dataService.ukloniLet(idLet);
                this.$router.go();
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
                        tempList = tmpLista.sort((a, b) => (a.brojLeta > b.brojLeta) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.brojLeta < b.brojLeta) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'brSlobM'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.brSlobMesta > b.brSlobMesta) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.brSlobMesta < b.brSlobMesta) ? 1 : -1)
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
                if(this.currentSort == 'polazak'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.datumIVreme > b.datumIVreme) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.datumIVreme < b.datumIVreme) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'polazakIz'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.lokOd > b.lokDo) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.lokOd < b.lokOd) ? 1 : -1)
                    }
                }
                if(this.currentSort == 'dolazakU'){
                    if(this.currentSortDir == 'asc'){
                        tempList = tmpLista.sort((a, b) => (a.lokDo > b.lokDo) ? 1 : -1);
                    }
                    else{
                        tempList = tmpLista.sort((a, b) => (a.lokDo < b.lokDo) ? 1 : -1)
                    }
                }
                return tempList;
            }
            
        },
        created(){
            parserMixin.methods.checkLoginStatus();
            this.userObj = parserMixin.methods.parseXmlJwt();
            console.log("ID KORISNIKA: " + this.userObj.id);
            this.rezDTO.kupac=this.userObj.id;
        },
        computed:{
            sortEntities(){
                let sortirani=this.sortiranje(this.listaLetova)
            return sortirani;
            },
        }

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

.table td>input{
    width:100%;
}

</style>