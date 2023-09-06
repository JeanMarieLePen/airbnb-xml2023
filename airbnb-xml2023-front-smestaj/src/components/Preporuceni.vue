<template>
    <div style="height:300px;">
        <h3>Preporuceni smestaj:</h3>
        <section style="margin-left: 10px; margin-bottom:100px;">
        <div v-if="preporuceni.length > 0">
            <div class="row">
                <div class="col-md-4" v-bind:key="index" v-for="(sTemp, index) in preporuceni.slice(0,10)">
                    <div style="width:45%;height:350px; margin-bottom:20px;" class="card">
                       <img  :src="getAnImage(sTemp)" style="margin:10px;width:90%; height:50%;" alt="card image collar"> 
                       <div class="card-body">
                            <h5 class="card-title">{{sTemp.adresa.adresa}}</h5>
                            <p class="card-text">Ocena: {{sTemp.prosecnaOcena}}</p>
                            <!-- <p class="card-text">Cena(ukupna): {{sTemp.ukCena}}</p> -->
                            <!-- <p class="card-text" v-if="sTemp.poSmestaju===true">Cena(po smestaju): {{this.getProsekCena(sTemp.ukCena)}}</p> -->
                            <!-- <p class="card-text" v-else>Cena(po smestaju/po Osobi): {{this.getProsekCenaOsoba(sTemp.ukCena)}}</p>  -->

                            <button style="margin-left:30px;" v-on:click="smestajDet(sTemp.id)" class="btn btn-primary">Detalji</button>
                       </div>
                    </div>
                </div>                        
            </div>
        </div>
        <div v-if="preporuceni.length == 0">
            <h2>NEMATE PREPORUCENIH SMESTAJA...</h2>
        </div>
    </section>
    </div>
</template>

<script>


export default {

    props:[
        'preporuceni'
    ],
    data(){
        return{

        }
    },
    methods:{
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
            return Math.abs(cena/(this.preporuceni.brojDana-1))
        },
        getProsekCenaOsoba(cena){
            let c= Math.abs(cena/(this.preporuceni.brojDana-1));
            return Math.abs(c/this.brGosti-1);
        },
        smestajDet(id){
            this.$router.push(`/pregled/${id}`);
        },
    }

}
</script>

<style>

</style>