<template>
  <div id="app">
      <LogIn @logged-in="logIn" v-if="!this.loggedIn"/>
      <Home @log-out="logOut" v-if="this.loggedIn" v-bind="this.user"/>
      <!-- <Home/> -->
  </div>  
</template>

<script>
import LogIn from './components/LogIn.vue';
import Home from './components/Home.vue';

export default {
  name: 'app',
  components: {
    LogIn,
    Home
  },
  mounted() {
   console.log(this.$route.query);
  },
  data() {
    return {
      loggedIn: false,
      user: {
        caseID: "",
        userName: "",
        password: "",
        isAdmin: 0,
        postAnon: 0,
      },
    }
  },
  methods: {
    logIn(user){
      this.loggedIn = true;
      this.user = user;
    },
    logOut(){
      this.$router.push({path: '/'});
      this.loggedIn = false;
      this.user = null;
    },
  }
}


</script>