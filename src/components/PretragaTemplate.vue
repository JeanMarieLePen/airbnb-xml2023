<template>
    <!--SORTIRANJE-->
    <!--<p>{{this.listaSmestaja.brojDana}}</p>-->
    <section style="margin-left: 10px; margin-bottom:100px;">
        <div>
            <div class="row">
                <div class="col-md-4" v-bind:key="index" v-for="(sTemp, index) in sortEntities.slice(0,15)">
                    <div style="width:90%;height:300px; margin-bottom:100px;" class="card">
                       <img  :src="getAnImage(sTemp)" alt="card image collar"> 
                       <div class="card-body">
                        <h5 class="card-title">{{sTemp.adresa.adresa}}</h5>
                        <p class="card-text">Ocena: {{sTemp.prosecnaOcena}}</p>
                        <p class="card-text">Cena(ukupna): {{sTemp.ukCena}}</p>
                        <p class="card-text" v-if="sTemp.poSmestaju===true">Cena(po smestaju): {{this.getProsekCena(sTemp.ukCena)}}</p>
                        <p class="card-text" v-else>Cena(po smestaju/po Osobi): {{this.getProsekCenaOsoba(sTemp.ukCena)}}</p> 

                        <button style="margin-left:30px;" v-on:click="smestajDet(sTemp.id)" class="btn btn-primary">Detalji</button>
                       </div>
                    </div>
                </div>                        
            </div>
        </div>
    </section>

</template>
<script>

export default {
    components: { },
    props: [
        'listaSmestaja',
        
    ],
        data(){
        return {
            userObj: {},
            currentSortDir: 'asc',
            currentSort: 'polazakIz',
            brGosti:''
        }
    },
    methods: { 
        getAnImage(smestaj){
            let tmpImg = 'data:image/png;base64,';
            for(let i = 0; i < smestaj.slike.length; i++){
                tmpImg += smestaj.slike[i];
                break;
            }
            console.log(tmpImg);
            return tmpImg;
        },
        getProsekCena(cena){
            return Math.abs(cena/(this.listaSmestaja.brojDana-1))
        },
        getProsekCenaOsoba(cena){
            let c= Math.abs(cena/(this.listaSmestaja.brojDana-1));
            return Math.abs(c/this.brGosti-1);
        },
        smestajDet(id){
            this.$router.push(`/pregled/${id}`);
        },
    },
    created(){
        //if(parserMixin.methods.checkLoginStatus() == true){
        console.log("ID KORISNIKA: " + this.userObj.role);
        if(this.listaSmestaja.brojGostiju===''){ this.brGosti=1;}
        else {this.brGosti=Number(this.listaSmestaja.brojGostiju);}
        //}
    },
    computed:{
        sortEntities(){
            let sortirani = this.listaSmestaja.listaSmestaja;
            return sortirani;
        },
    }

}

</script>
<style scoped>
.table {
    font-family: Arial, Helvetica, sans-serif;
    margin-left: 20px;

}

.table tr:nth-child(even) {
    background-color: aquamarine;
}

.table td:nth-child(1) {
    width: 30%;
    font-size: 20px;
    font-weight: 600;
}

.table td>input {
    width: 100%;
}
</style>