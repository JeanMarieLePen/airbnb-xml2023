<template>
    <div id="#navigation">
        <nav class="navbar navbar-expand-lg navbar-dark bg-success static-top">
        <a class="navbar-brand" href="/"><h1><span id="pageTitle" style="margin-left:20px;">XML2023-APP</span></h1></a>
            <div class="collapse navbar-collapse" style="display:flex">
                <ul class="navbar-nav mr-auto" >
                    <li v-show="!loggedIn" class="nav-item active">
                        <router-link to='/dash' class="nav-link" exact>Home</router-link>
                    </li>
                    <li v-show="loggedIn" class="nav-item active">
                        <router-link class="nav-link" to='/dash' exact>Home</router-link>
                    </li>
                    <li class="nav-item active">
                        <router-link to='/about' class="nav-link" exact>About</router-link>
                    </li>
                </ul>
                <div style="margin-left: auto; margin-right:20px;">
                    <ul class="nav my-2 my-lg-0">
                        <li v-if="role === 'GUEST' || role === 'HOST'" class="nav-item">
                            <button style="padding: 0; border: none; background: none; margin-right:10px;" :title="showNotificationButton" @click="enableNotifications()">
                                <div v-show="!notifications">
                                    <img id="imgNotifications" src="../assets/bell.png" style="max-width:42px; max-height:38px;" />
                                </div>
                                <div v-show="notifications">
                                    <img id="imgNotifications" src="../assets/bell-disabled.png" style="max-width:42px; max-height:38px;" />
                                </div>
                            </button>
                        </li>
                        <!-- <li class="nav-item">
                            <button style="padding: 0; border: none; background: none; margin-right:10px;" @click="addNotification('dwadawdawadwdaw')">
                                <div v-show="!notifications">
                                    <img id="imgNotifications" src="../assets/bell.png" style="max-width:42px; max-height:38px;" />
                                </div>
                                <div v-show="notifications">
                                    <img id="imgNotifications" src="../assets/bell-disabled.png" style="max-width:42px; max-height:38px;" />
                                </div>
                            </button>
                        </li> -->
                        <li class="nav-item">
                            <button class="btn btn-warning" style="font-weight: bold;" v-show="!loggedIn" @click="$router.push('/login')">Log in</button> 
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-danger" style="font-weight: bold;" v-show="loggedIn" v-on:click="logOut()">Log Out</button>
                        </li>
                        
                        <!-- <li class="nav-item">
                            <button style="font-weight: bold;" v-on:click="enableNotifications()"></button>
                        </li> -->
                    </ul>
                </div>
                
            </div>
        </nav>
    </div>    
</template>




<script>
import axios from 'axios'
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import parserMixin from '@/mixins/mixin';

export default {
    name:'Navigation',
    data(){
        return{
            loggedIn : localStorage.getItem('xmljwt') ? true : false,
            notifications : false,
            connected : false,
            receivedNotification : null,

            userObj:{

            },
            role: '',
        }
    },
    props:{
        addNotification:Function,
        anullNotifications:Function,
    },
    mounted(){
        //bez $ znaka ispred emitera
        this.emitter.on("loggedIn", (data) => {
            this.loggedIn = data;
        });
        this.emitter.on("roleAssigned", (data) => {
            console.log("DODELJANA ULOGA: " + data);
            this.role = data;
        })
    },
    computed:{
        showNotificationButton(){
            if(this.notifications == true){
                return "Iskljucite notifikacije"
            }else{
                return "Ukljucite notifikacije"
            }
        }
    },
    created(){
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.userObj = userObj;
        }
    },
    methods:{
        // addNotification(){
        //     let tempRezervacija = {
        //         id:1,
        //         let:'OA-2234-22L',
        //         ime:'Andrija',
        //         prezime:'Bogdanovic'
        //     }
        //     this.emitter.emit('add-to-list', tempRezervacija);
        // },
        connect(){
            if(this.userObj.role === "GUEST"){
                console.log("ADWDADWDAWDAW")
                this.socket = new SockJS("http://localhost:8082/main-app-websockets");
                this.stompClient = Stomp.over(this.socket);
                this.stompClient.connect(
                    {},
                    frame => {
                        this.connected = true;
                        console.log(frame);
                        this.stompClient.subscribe("/queue/notifications", tick => {
                            console.log(tick);
                            console.log("SADRZAJ ODGOVORA: " + tick.body);
                            this.receivedNotification = JSON.parse(tick.body);
                            // this.emitter.emit("GuestRezervacijaEvent", this.receivedNotification);
                            console.log("REZERVACIJA NAKON PARSIRANJA: " + this.receivedNotification);
                            this.addNotification(this.receivedNotification.idRezervacije);

                            // this.emitter.emit('addNotification', this.receivedNotification.idRezervacije);
                            this.receivedNotification = null;
                            console.log(JSON.stringify(this.receivedNotification));
                        });

                    }, error => {
                        console.log(error);
                        this.connected = false;
                    }
                )
            }
            else if(this.userObj.role === "HOST"){
                this.socket = new SockJS("http://localhost:8082/main-app-websockets/host");
                this.stompClient = Stomp.over(this.socket);
                this.stompClient.connect(
                    {},
                    frame => {
                        this.connected = true;
                        console.log(frame);
                        this.stompClient.subscribe("/queue/notificationsHost", tick => {
                            console.log(tick);
                            console.log("SADRZAJ ODGOVORA: " + tick.body);
                            this.receivedNotification = JSON.parse(tick.body);
                            // this.emitter.emit("GuestRezervacijaEvent", this.receivedNotification);
                            console.log("REZERVACIJA NAKON PARSIRANJA: " + this.receivedNotification);
                            this.addNotification(this.receivedNotification.tekst);

                            // this.emitter.emit('addNotification', this.receivedNotification.idRezervacije);
                            this.receivedNotification = null;
                            console.log(JSON.stringify(this.receivedNotification));
                        });

                    }, error => {
                        console.log(error);
                        this.connected = false;
                    }
                )
            }
            
            
        },
        disconnect(){
            if(this.stompClient){
                this.stompClient.disconnect();
            }
            this.connected = false;
        },
        tickleConnection(){
            this.connected ? this.disconnect() : this.connect();
        },
        
        enableNotifications(){
            this.notifications = !this.notifications;
            if(this.notifications==true){
                this.emitter.emit("notificationOn", true);
            }else{
                this.emitter.emit("notificationOn", false);
                this.anullNotifications();
            }
            console.log("NOTIFIKACIJE: " + this.notifications);
            if(this.connected){
                this.disconnect();
            }else{
                this.connect();
            }
        },
        logOut(){
            if(confirm('Da li ste sigurni da zelite da se odjavite?')){
                if(localStorage.getItem('xmljwt')){
                    localStorage.removeItem('xmljwt');
                    axios.defaults.headers.common['Authorization'] = undefined;
                    this.loggedIn = false;
                    this.role = '';
                    this.$router.push("/");
                    if(localStorage.getItem('parsedToken')){
                        localStorage.removeItem('parsedToken');
                    }
                }else{
                    localStorage.removeItem('xmljwt');
                    axios.defaults.headers.common['Authorization'] = undefined;
                    this.loggedIn = false;
                    this.$router.push('/');
                }
            }
        }
    }
}
</script>


<style scoped>
#pageTitle{
  margin-right: 100px;
  font-size: 30px;
  font-family: Verdana;
  color:oldlace
}
#navigation{
  color:#fff;
  /* min-height: 100px; */
  height: 10vh;
}
.nav-link{
  font-size: 20px;
}
</style>