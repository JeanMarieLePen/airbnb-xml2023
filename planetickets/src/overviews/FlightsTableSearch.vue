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
            </thead>
            <tbody>
                <tr v-for="letT in sortEntities" >
                    <td>{{letT.datumIVreme}}</td>
                    <td>{{letT.lokOd}}</td>
                    <td>{{letT.lokDo}}</td>
                    <td>{{letT.brojLeta}}</td>
                    <td>{{letT.cena}}</td>
                    <td>{{letT.brSlobMesta}}</td>
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
                currentSortDir: 'asc',
                currentSort:'polazakIz',
            }

        },
        methods:{
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
        created(){},
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