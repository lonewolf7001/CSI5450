<template>
    <div class="container">
    <Header
      title="REAL ESTATE MANAGEMENT SYSTEM"
    />
    <router-link to="/housesearch" v-slot="{ navigate }"><Button text="HOUSE SEARCH" color="orange" @click="navigate" role="link"></Button></router-link>
    <router-link to="/ownersearch" v-slot="{ navigate }"><Button text="OWNER SEARCH" color="orange" @click="navigate" role="link"></Button></router-link>
    <router-link to="/agentsearch" v-slot="{ navigate }"><Button text="AGENT SEARCH" color="orange" @click="navigate" role="link"></Button></router-link>
    <router-link to="/transactions" v-slot="{ navigate }"><Button text="TRANSACTIONS" color="orange" @click="navigate" role="link"></Button></router-link>
        
    <router-view></router-view>
        <!-- The springboot msg is : {{ string }} -->
  </div>
</template>

<script>
import Header from './components/Header'
import Button from './components/Button'
import ExampleService from './services/connectspringbootservice'
export default {
  name: 'App',
  components: {
    Button,
    Header,
  },
  data(){
      return {
          string: []
      }
    },
  methods: {
    getExampleResponse(){
      ExampleService.getExampleService().then((response) => {
        this.string = response.data;
      });
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap');
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
body {
  font-family: 'Poppins', sans-serif;
}
.container {
  max-width: 50px;
  margin: 30px auto;
  /* overflow: 10px; */
  min-height: 50px;
  border: 1px solid rgb(248, 248, 248);
  padding: 30px;
  border-radius: 5px;
}

.container1 {
  max-width: 1000px;
  margin: 30px auto;
  /* overflow: 10px; */
  max-height: 50px;
  border: 1px solid rgb(255, 255, 255);
  /* padding: 30px; */
  border-radius: 5px;
}

.container2 {
  max-width: 900px;
  margin: 30px auto;
  /* overflow: 10px; */
  min-height: 100px;
  border: 1px solid rgb(145, 47, 47);
  padding: 30px;
  border-radius: 5px;
}

.btn {
  display: inline-block;
  background: #000;
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  font-family: inherit;
}

.btn1 {width: 200px;}
.btn2 {width: 50%;}
.btn3 {width: 100%;}

.btn:focus {
  outline: none;
}
.btn:active {
  transform: scale(0.98);
}
.btn-block {
  display: block;
  width: 100%;
}


</style>
