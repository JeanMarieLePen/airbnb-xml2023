<template>
  <div>
    <navigation :addNotification="addNotification" :anullNotifications="anullNotifications"></navigation>
    
    <div style="height: 85vh; overflow: auto">
      <!-- <button @click="addReservation()">REZERVACIJA</button>
      <button @click="connect()">Connect</button> -->
      <router-view></router-view>
      
    </div>
    <div class="div1">
      <div>
        <transition-group style="margin-bottom:70px;" tag="ul" mode="out-in" name="notification">
          <div v-for="(item) in rezervacije.slice(0,5)" class="notification" :key="item">
            <p style="font-size:15px; font-style: italic;">novo obavestenje</p>
            <div style="font-size:20px;">
              TEKST:{{item}}
              <!-- Ime: {{item.ime}} -->
            </div>
          </div>
        </transition-group>
      </div>
    </div>
   
    <app-footer></app-footer>
    
  </div>
  
</template>


<script>
import 'bootstrap/dist/css/bootstrap.css'
import Footer from './components/Footer.vue'
import Navigation from './components/Navigation.vue'
import {reactive, ref} from 'vue';
import parserMixin from '@/mixins/mixin';
export default {
  name:'app',
  setup(){

    // const state = reactive({
    //   notifications:[],
    // })
    // const addNotification = (notification) => {
    //   state.notifications.push(notification);
    // }
    const showSlide = ref(false);
    const triggerSlide = () => {
        showSlide.value = true;
        setTimeout(() => showSlide.value = false, 6000);
    }
    return {showSlide, triggerSlide}
  },
  watch:{
    // rezervacije(){
    //     console.log("AKTIVIRAN WATCHER 2")
    //     this.triggerSlide();
    // }
    rezervacije:{
      handler: function(val, oldVal){
        console.log("a thing changed");
        this.triggerSlide();
      },
      deep: true
    }
  },
  methods:{
    addNotification(notification){
      setTimeout(() => {
        this.rezervacije.shift();
      }, 5000)
      console.log("DODAVANJE NOTIFIKACIJE");
      this.rezervacije.push(notification);
    },
    anullNotifications(){
      this.rezervacije = [];
    },
    addReservation(){
      let tempRezervacija = {
        id:1,
        let:'OA-2234-22L',
        ime:'Andrija',
        prezime:'Bogdanovic'
      }
      this.rezervacije.push(tempRezervacija);

      let tmpIndex = 0;
      for(let i = 0; i < this.rezervacije.length; i++){
        if(this.rezervacije[i].id == tempRezervacija.id){
          tmpIndex = i;
          break;
        }
      }
      console.log("broj rezerv." + this.rezervacije.length);
      setTimeout(() => {
        this.rezervacije.splice(tmpIndex, 1);
      }, 6000);
      
    }
  },
  created(){
    
  },
  mounted(){
    // setInterval(() => {
    //   this.rezervacije.shift();
    // }, 5000);
    this.emitter.on("notificationOn", (data) => {
      console.log("NOTIFIKACIJE SU UPALJENE: " + data)
      this.notifications = data;
    });
    // this.emitter.on("GuestRezervacijaEvent", (data) => {
    //   console.log("PREBACENO OBAVESTENJE[GUEST REZERVACIJA]: " + data);
    //   this.rezervacije.push(data);
    // });
    console.log("PRVA STRANICA");

    let tempObj = parserMixin.methods.parseXmlJwt();
    console.log("ULOGA JE: " + tempObj.role)
    if(tempObj.role == 'GUEST'){
      console.log("USLO U GOSTA");
      this.emitter.emit("roleAssigned", tempObj.role);
    }else if(tempObj.role == 'HOST'){
      this.emitter.emit("roleAssigned", tempObj.role);
    }else{
      this.emitter.emit("roleAssigned", '');
    }
    
  },
  components:{
    'app-footer':Footer,
    'navigation':Navigation
  },
  data(){
    return{
      rezervacije:[
        
      ],
      connected:false,
      reservationNotificaiton:null,
      notifications:false,
      notificationsShow: false,
      role:'',
    }
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.notification-enter-from{
    opacity: 0;
    transform: translateY(60px);
    transform: translateX(100px);
}
.notification-enter-to{
    opacity: 1;
    transform: translateY(0);
    transform: translateX(0);
}
.notification-enter-active{
    transition: all 0.4s ease;
}
.notification-leave-from{
    opacity: 1;
    transform:translateY(0);
    transform: translateX(0px);
}
.notification-leave-to{
    opacity: 0;
    transform: translateY(60);
    transform: translateX(100px);
}
.notification-leave-active{
    transition: all 0.4s ease;
}


.notification{
  /* color: white;
  background: #ff0062; */
  background: #FFC107;
    color: black;
  border-radius: 10px;
  text-align: center;
  /* height:50px; */
  width:400px;
  box-shadow: 1px 3px 5px rgba(0,0,0,0.2);
  max-width: 400px;
  margin-top:20px;
  margin-bottom:20px;
  /* margin: 0 auto; */
}
.notification-wrapper {
    position: fixed;
    top: 20px;
  }


.div1{
  display:flex;
  position: absolute;
  font-size: 30px;
  text-align: center;
  color:#fff;
  justify-content: flex-end;
  width:100%;
  justify-items: stretch;
  bottom: 0%;
  /* margin-bottom: 30px; */
}
#footer{
    position: fixed;
    bottom: 0;
    width:100%;
    text-align: center;
    height:5%;
    vertical-align: center;
    font-size: 20px;
}
</style>
