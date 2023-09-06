<template>
    <div id="home" style="margin-bottom:100px; width:60%;">
        <div class="container">
            <h1>
                <h1 style="margin-top:10px;color:#35424a;">Welcome <span id='titleEffect'>{{user.username}}</span>!</h1>
                <hr style='background:#35424a;height:1px;'>
            </h1>
        </div>

        <!-- <div v-show="role == 'GUEST'"> -->
            <div class="row">
                <!-- <div v-show="(role === 'GUEST') || (role === 'ADMINISTRATOR') || (role === 'HOST')" class="col-xl-3 col-md-6 mb-4" style="margin:20px;"> -->
                <div class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga ponude</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pretrazite detaljno ponudu svih proizvoda...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div v-show="role == 'GUEST' || role == 'ADMINISTRATOR' || role == 'HOST'" class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/profile" class="nav-link" exact>
                            <img src="../assets/profile.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled profila</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasem profilu...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div v-show="role == 'HOST'" class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/create" class="nav-link" exact>
                            <img src="../assets/room-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Novi smestaj</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Kreirajte novi smestaj koji ce biti prikaz u vasoj ponudi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div v-show="role == 'GUEST'" class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/reservationsOverview" class="nav-link" exact>
                            <img src="../assets/booking-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled rezervacija...</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte rezervacije koje ste napravili...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div v-show="role == 'HOST'" class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/moderate" class="nav-link" exact>
                            <img src="../assets/booking-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled i moderacija rezervacija...</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte i moderirajte vase rezervacije...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div v-show="!(role !== 'HOST' && role !== 'GUEST')"  class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/rating" class="nav-link" exact>
                            <img src="../assets/rating-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled ocena...</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte ocene korisnika...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        <!-- </div> -->
    </div>
</template>


<script>
import parserMixin from '@/mixins/mixin'
export default {
    
    mixins:[parserMixin],
    data(){
        return{
            user:{
                username:'',
            },
            role:'',
            userId:-1,
        }
    },
    methods:{

    },
    created(){
        if(localStorage.getItem('xmljwt')){
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
        }      
    }
}
</script>

<style scoped>
#titleEffect{
  color: red;
  font-weight: bold;
}
#home{
    text-align: center;
    width: 100%;
    display: inline-block;
}
</style>