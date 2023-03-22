<template>
    <div class="container">
    <Header
      title="REAL ESTATE MANAGEMENT SYSTEM"
    />
    <router-link to="/housesearch" v-slot="{ navigate }"><Button text="HOUSE SEARCH" color="lightgreen" @click="navigate" role="link"></Button></router-link>
    <router-link to="/ownersearch" v-slot="{ navigate }"><Button text="OWNER SEARCH" color="lightgreen" @click="navigate" role="link"></Button></router-link>
    <router-link to="/agentsearch" v-slot="{ navigate }"><Button text="AGENT SEARCH" color="lightgreen" @click="navigate" role="link"></Button></router-link>
    <router-link to="/transactions" v-slot="{ navigate }"><Button text="TRANSACTIONS" color="lightgreen" @click="navigate" role="link"></Button></router-link>
        
    <router-view :showAddTask="showAddTask"></router-view>
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
  max-width: 1000px;
  margin: 30px auto;
  overflow: auto;
  min-height: 100px;
  border: 1px solid rgb(255, 255, 255);
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
