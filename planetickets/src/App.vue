<template>
  <div>
    <navigation></navigation>
    <div style="height: 85vh; overflow: auto"><router-view></router-view></div>
    
    <app-footer></app-footer>
  </div>
  
</template>


<script>
import 'bootstrap/dist/css/bootstrap.css'
import Footer from './components/Footer.vue'
import Navigation from './components/Navigation.vue'
export default {
  name:'app',
  components:{
    'app-footer':Footer,
    'navigation':Navigation
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
  color: firebrick;
}
</style>
