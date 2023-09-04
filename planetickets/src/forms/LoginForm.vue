<template>
<div id="Login">
    <div class="container-fluid">
      <div class="row no-gutter">
          <div id='bgImage' class="d-none d-md-flex col-md-4 col-lg-6 ">
          <img src='../assets/login-green.png'>
          </div>
          <div class="col-md-8 col-lg-6">
          <div class="login d-flex align-items-center py-5">
              <div class="container text-center">
              <div class="row">
                  <div class="col-md-9 col-lg-8 mx-auto">
                  <h3 class="login-heading mb-4">Welcome back!</h3>

                  
                  <form v-on:keyup.enter="submition()">
                      <div class="form-label-group">
                      <input maxlength="50" v-model="emailAddress"  id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                      <label for="inputEmail">Email address</label>
                      </div>

                      <div class="form-label-group">
                      <input maxlength="50" v-model ="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                      <label for="inputPassword">Password</label>
                      </div>

                      <button type="button" class="btn btn-lg btn-success btn-block btn-login text-uppercase font-weight-bold mb-2" v-on:click='submition()'>
                          Sign in
                      </button>
                      <a class="d-block text-center mt-2 small" href="/register">Registruj se</a>
                  </form>
                  <div v-if="errorMessage" v-html="errorMessage" class="alert alert-danger"></div>
                  </div>
              </div>
              </div>
          </div>
        </div>
    </div>
    </div>

  </div></template>


<script>
import dataService from '../services/dataService'
import axios from 'axios'
import parserMixin from '@/mixins/mixin'
export default {
    name:'LoginForm',
    data(){
        return{
            emailAddress:'',
            username:'',
            password:'',

            praznaPolja: false,
            neispravniPodaci: false,

            errorMessage:'',
            ipAdresa:'0.0.0.0',            
        }
    },
    created(){
      if(localStorage.getItem('xmljwt')!= null){
        this.$router.push(`/`);
      }
    },
    methods:{
        submition(){
          console.log("UNETI EMAIL I PASSWORD:")
          console.log(this.emailAddress + ", " + this.password);
          let loginUser = new Object();
          loginUser.email = this.emailAddress;
          loginUser.password = this.password;
            dataService.login(loginUser).then(response => {
                if(response.status === 200){
                    console.log("LOGIN USPESAN");
                    this.token = response.data.odgovor;
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.odgovor;
                    axios.defaults.headers.common['IPadresa']= this.ipAdresa;
                    
                    localStorage.setItem('xmljwt', JSON.stringify(this.token));
                    localStorage.setItem('xmlIP', JSON.stringify(this.ipAdresa));

                    this.emitter.emit("loggedIn", true);
                    this.$router.push(`/dash`);
                }else{
                  console.log("Neuspesan login");
                  this.errorMessage = "<h4>Neispravan email ili lozinka.</h4>";
                  setTimeout( () => {
                    this.errorMessage = '';
                  }, 4000);
                };
            });

        },
        async getIp(){
          //console.log("FETHC BEGIN: ", Date.now())
          return fetch('https://api.ipify.org?format=json')
            .then(x => x.json())
            .then(({ ip }) => {
          console.log("IP: " ,ip )//, " end", Date.now())
          this.ipAdresa=ip;
          });
        }
    }
}
</script>


<style scoped>

:root {
  --input-padding-x: 1.5rem;
  --input-padding-y: 0.75rem;
}

.input{
    font-size: 30px;
}
.login,
.image {
    max-height: 95vh;
    min-height: 90vh;
    
}

.login-heading {
  font-weight: 300;
  font-size:50px;
}

.btn-login {
    width: 150px;
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.75rem 1rem;
  border-radius: 2rem;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
  height: 50px;
  border-radius: 2rem;
  font-size: 25px;
  font-style: italic;
}

.form-label-group>label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0;
  /* Override default `<label>` margin */
  line-height: 1.5;
  color: #495057;
  cursor: text;
  /* Match the input under the label */
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown)~label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}

/* Fallback for Edge
-------------------------------------------------- */

@supports (-ms-ime-align: auto) {
  .form-label-group>label {
    display: none;
  }
  .form-label-group input::-ms-input-placeholder {
    color: #777;
  }
}

/* Fallback for IE
-------------------------------------------------- */

@media all and (-ms-high-contrast: none),
(-ms-high-contrast: active) {
  .form-label-group>label {
    display: none;
  }
  .form-label-group input:-ms-input-placeholder {
    color: #777;
  }
}
</style>