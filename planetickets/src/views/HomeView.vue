<template>
  <div id="div1" class="home_class" style="margin-bottom: 20px">
    <section id="sec1" @click="goToDash()">
      <h2>
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Qui
        consequuntur repudiandae quidem ipsam illo reiciendis obcaecati, aliquid
        facilis porro asperiores tempora suscipit pariatur itaque dicta odio
        unde veniam quae veritatis!<span id="AppBold"> TempNameApp</span> Lorem
        ipsum dolor sit amet consectetur, adipisicing elit.!
      </h2>
      <!-- <p style="font-size:25px;">
                <b><center>U svega nekoliko sekundi pretrazite ponudu stranice i pronadjite uslugu koja najbolje odgovara vasim zahtevima.</center></b>
            </p> -->
    </section>

    <section
      id="newsletter"
      style="margin-top: 5px; height: 100px"
    >
      <div class="container" style="display: flex; align-item: center">
        <div
          style="display: flex; justify-content: center; align-items: center"
        >
          <h2>Niste registrovani? Registrujte se klikom na dugme...</h2>
          <form>
            <router-link to="/register" class="nav-link" exact>
              <button
                type="submit"
                style="margin-left: 30px; font-weight: 500"
                class="btn btn-warning my-0"
              >
                Registracija
              </button>
            </router-link>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>


<script>
export default {
  data() {
    return {};
  },
  methods: {
    goToDash() {
      this.$router.push("/dash");
    },
  },
};
</script>


<style scoped>
.home_class {
  background-color: #f4f4f4;
  color: #fff;
  text-align: center;
  /* height: 100%; */
  /* background-image:url('../assets/hospital.jpg');
    background-size: 100% 100%;
    background-repeat: no-repeat; */
  min-height: 600px;
}

/* div:first-child{
    height: 100vh;
} */
#div1 {
  height: 80vh;
}
/* .home_class div:first-child{
    height: 10vh;
} */

#sec1 {
  background-image: url("../assets/airplane.jpg");
  background-size: 100% 100%;
  min-height: 700px;
  /* background-size: cover; */
  background-repeat: no-repeat;
  height: 80%;
  margin-bottom: 5px;
}

#AppBold {
  text-shadow: 1px 0 0 white, 0 -1px 0 white, 0 1px 0 white, -1px 0 0 white;
  color: firebrick;
  font-weight: bold;
}
#newsletter{
    background-color: firebrick;
}
</style>