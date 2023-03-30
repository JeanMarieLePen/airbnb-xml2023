<template>
    <div id="home" style="margin-bottom:100px; width:60%;">
        <div class="container">
            <h1>
                <h1 style="margin-top:10px;color:#35424a;">Welcome <span id='titleEffect'>{{ user.username }}</span>!</h1>
                <hr style='background:#35424a;height:1px;'>
            </h1>
        </div>

        <div v-show="role == 'REG_KOR'">
            <div class="row">
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
                <div class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
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
                <div class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/userFlights" class="nav-link" exact>
                            <img src="../assets/commercial-plane.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled vasih letova</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasim letovima...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <!-- <div class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga ponude</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pretrazite detaljno ponudu svih proizvoda u ponudi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div> -->
            </div>
        </div>
        <div v-show="role == 'ADMINISTRATOR'">
            <div class="row">
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
                <div class="col-xl-3 col-md-6 mb-4" style="margin:20px;">
                    <div class="car border-0 shadow" style="height:100%; margin: 20px; min-width:300px;">
                        <router-link style="text-decoration: none; c" to="/create" class="nav-link" exact>
                            <img src="../assets/commercial-plane.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Novi let</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Dodajte novi let...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</template>


<script>
import parserMixin from '@/mixins/mixin'
export default {

    // mixins:[parserMixin],
    data() {
        return {
            user: {
                username: '',
            },
            role: '',
            userId: -1,
        }
    },
    methods: {

    },
    created() {
        console.log("AAAAAAAAA")
        if (localStorage.getItem('xmljwt')) {
            let userObj = parserMixin.methods.parseXmlJwt();
            this.user.username = userObj.email;
            this.role = userObj.role;
            this.userId = userObj.id;
        }
    }
}
</script>

<style scoped>
#titleEffect {
    color: red;
    font-weight: bold;
}

#home {
    text-align: center;
    width: 100%;
    display: inline-block;
}
</style>