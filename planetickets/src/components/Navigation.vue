<template>
    <div id="#navigation">
        <nav id="navbar1" class="navbar navbar-expand-lg navbar-dark static-top">
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
                    <li class="nav-item">
                        <button class="btn btn-warning" style="font-weight: bold;" v-show="!loggedIn" @click="$router.push('/login')">Log in</button> 
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-danger" style="font-weight: bold;" v-show="loggedIn" v-on:click="logOut()">Log Out</button>
                    </li>
                </ul>
                </div>
                
            </div>
        </nav>
    </div>    
</template>




<script>
import axios from 'axios'
export default {
    name:'Navigation',
    data(){
        return{
            loggedIn : localStorage.getItem('xmljwt') ? true : false
        }
    },
    mounted(){
        //bez $ znaka ispred emitera
        this.emitter.on("loggedIn", (data) => {
            this.loggedIn = data;
        });
    },
    methods:{
        logOut(){
            if(confirm('Da li ste sigurni da zelite da se odjavite?')){
                if(localStorage.getItem('xmljwt')){
                    localStorage.removeItem('xmljwt');
                    axios.defaults.headers.common['Authorization'] = undefined;
                    this.loggedIn = false;
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
#navbar1{
    background-color: firebrick;
}
#pageTitle{
  margin-right: 100px;
  font-size: 30px;
  font-family: Verdana;
  color:oldlace
}
#navigation{
  color:#fff;
  min-height: 100px;
}
.nav-link{
  font-size: 20px;
}
</style>