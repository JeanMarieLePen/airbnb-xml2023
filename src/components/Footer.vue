<template>
    <div class="div1" style="justify-content:center">
        <div class="divFake">

        </div>
        <div class="div2">
            <div>
                <p>Copyrights &copy;XML2023[Tim33]</p>
            </div>
        </div>
        <div class="div3">
            <transition name="slide">
                <div class="slide" v-if="showSlide" >
                    <div v-if="showOpened">
                        Ukljucene notifikacije
                    </div>
                    <div v-if="showClosed">
                        Iskljucene notifikacije
                    </div>
                </div>
            </transition>
        </div>
    </div>    
</template>

<script>

import {ref} from 'vue';

export default {
    name:'Footer',
    watch:{
        notifications(){
            console.log("AKTIVIRAN WATCHER")
            if(this.notifications==true){
                this.triggerSlide();
                this.showOpened = true;
                this.showClosed = false;
            }else{
                this.triggerSlide();
                console.log("PRIKAZ NOTIFIKACIJA: " + this.notifications)
                this.showOpened = false;
                this.showClosed = true;
            }
        }
    },
    components:{
        
    },
    setup(){
        const showSlide = ref(false);
        const triggerSlide = () => {
            showSlide.value = true;
            setTimeout(() => showSlide.value = false, 3000);
        }
        return {showSlide, triggerSlide}
    },
    data(){
        return{
            showOpened: false,
            showClosed: true,
            notifications:false,
            notificationsShow: false,
        }
    },
    mounted(){
        this.emitter.on("notificationOn", (data) => {
            console.log("NOTIFIKACIJE SU UPALJENE: " + data)
            this.notifications = data;
        })
    },
    methods:{
    }
}
</script>



<style scoped>
/*Podesavanje inicijalnog polozaja elementa koju ce se animirati
    enter-from - klasa koja se primenjuje na element pre nego sto se pojavi u browseru
    enter-to - klasa koja se na element primenjuje nakon sto se pojavi u browseru
    enter-active - klasa koja se izvrsava nad elementom u toku tranzicije iz jednog stanja u drugo tj iz stanja from u stanje to
    
    u enter-from stavljamo opacity na 0, u enter-to stavljamo opacity na 1
    u klasi enter-active naglasavamo kako ce se ta tranzicija odvijati(kojom brzinom, itd)

    slicno se odnosi na klase leave-from, leave-to, leave-active
    tranziciji se identifikator zadaje u okviru atributa name
*/
.slide-enter-from{
    opacity: 0;
    transform: translateY(60px);
    transform: translateX(100px);
}
.slide-enter-to{
    opacity: 1;
    transform: translateY(0);
    transform: translateX(0px);
}
.slide-enter-active{
    transition: all 0.4s ease;
}
.slide-leave-from{
    opacity: 1;
    transform:translateY(0);
    transform: translateX(0px);
}
.slide-leave-to{
    opacity: 0;
    transform: translateY(60);
    transform: translateX(100px);
}
.slide-leave-active{
    transition: all 0.4s ease;
}
.div1{
    display:flex;
    /* position: absolute; */
    background-color: #198754;
    font-size: 30px;
    text-align: center;
    color:#fff;
    height: 5vh;
    width:100%;
    /* justify-items: stretch; */
    /* justify-content:space-between; */

}
.divFake{
    width:300px;
}
.div2{
    /* display:inline-flex; */
    /* justify-content: center; */
    /* position:sticky; */
    /* position:absolute; */
    flex:1;
}
.div3{
    /* display:inline-flex; */
    /* flex:1; */
    text-align: center;
    /* font-size: 20px; */
    width:300px;
    justify-content: flex-end;
    /* position:absolute; */
}
#footer{
    position: fixed;
    bottom: 0;
    width:100%;
    text-align: center;
    height: 5vh;
    /* height:5%; */
    vertical-align: center;
    font-size: 20px;
}

.slide-wrapper {
    position: fixed;
    width: 100%;
    top: 20px;
  }
.slide {
    /* padding: 5px; */
    /* color: white; */
    /* background: #ff0062; */
    background: #FFC107;
    color: black;
    border-radius: 10px;
    box-shadow: 1px 3px 5px rgba(0,0,0,0.2);
    /* max-width: 400px; */
    /* margin: 0 auto; */
  }
</style>