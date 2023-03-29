<template>
  <div class="container">
    <h1>{{ title }}</h1>
    <!-- <ul> -->
    <Button text="HOUSE SEARCH" color="green" @click="getExampleResponse()"/>
    <Button text="OWNER SEARCH" color="green" @click="getExampleResponse()"/>
    <Button text="AGENT SEARCH" color="green" @click="getExampleResponse()"/>
    <Button text="TRANSACTIONS" color="green" @click="getExampleResponse()"/>
    <!-- </ul> -->
    The springboot msg is : {{ string }}
  </div>
</template>

<script>
// import ExampleService from '../../../../spring-boot/src/main/java/edu/oakland/csi5450/service/ExampleService.java'
// export default {
//   name: 'HelloWorld',
//   props: {
//     msg: String
//   }
// // }
import Button from './Button'
import Header from './Header'
import ExampleService from '../services/connectspringbootservice'
export default {
    name: 'HelloWorld',
    props: {
      title: String
    },
    components:{
      Button,
      Header
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

// }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
} */
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
  min-height: 300px;
  border: 1px solid steelblue;
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
