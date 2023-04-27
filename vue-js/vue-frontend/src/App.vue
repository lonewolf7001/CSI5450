<template>
  <div>
    <div class="container">
      <Header title="REAL ESTATE MANAGEMENT SYSTEM" />
      <router-link to="/" v-slot="{ navigate }"
        ><Button
          text="HOME PAGE"
          color="orange"
          @click="navigate"
          role="link"
        ></Button
      ></router-link>
      <router-link to="/homesearch" v-slot="{ navigate }"
        ><Button
          text="HOME SEARCH"
          color="orange"
          @click="navigate"
          role="link"
        ></Button
      ></router-link>
      <router-link to="/agentsearch" v-slot="{ navigate }"
        ><Button
          text="AGENT SEARCH"
          color="orange"
          @click="navigate"
          role="link"
        ></Button
      ></router-link>
      <router-link to="/transactions" v-slot="{ navigate }"
        ><Button
          text="TRANSACTIONS"
          color="orange"
          @click="navigate"
          role="link"
        ></Button
      ></router-link>
      <router-link to="/ownersearch" v-slot="{ navigate }"
        ><Button
          text="OWNER SEARCH"
          color="orange"
          @click="navigate"
          role="link"
        ></Button
      ></router-link>
      <router-view></router-view>
    </div>
    <!-- <div class="photo-gallery">
    <div class="photo-container"> -->
      <!-- Add your photos here -->
      <!-- <img src="../src/photos/house-1836070__480.jpg" alt="My photo" class="custom-padding"> -->
      <!-- <img src="../src/photos/pexels-david-mcbee-1546168.jpg" alt="My photo" class="custom-padding"> -->
      <!-- <img src="../src/photos/real-estate-agent-offer-house-represented-model-wide-banner-composition-bokeh-background-63596018.jpg" alt="My photo" class="custom-padding"> -->
      <!-- <div v-for="photo in photos" :key="photo.id" class="photo"> -->
        <!-- <img :src="../src/photos/20150622231001-for-sale-real-estate-home-house.jpeg"  class="photo"> -->
      <!-- </div> -->
    <!-- </div> -->
  </div>
</template>

<script>
import Header from "./components/Header";
import Button from "./components/Button";

export default {
  name: "App",
  components: {
    Button,
    Header,
  },
  data() {
    return {
      string: [],
    };
  },
  methods: {},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap");

.photo-gallery {
  width: 100%;
  overflow-x: scroll;
  margin: 0;
  padding: 10;
}

.photo-container {
  display: flex;
}
.custom-padding {
  padding: 50px;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 10;
}
body {
  font-family: "Poppins", sans-serif;
}
.container {
  max-width: 50px;
  margin: 30px auto;
  /* overflow: 10px; */
  overflow-y: auto;
  min-height: 50px;
  border: 1px solid rgb(248, 248, 248);
  padding: 30px;
  border-radius: 5px;
}

.container1 {
  max-width: 1000px;
  margin: 30px auto;
  overflow-y: auto;
  /* overflow: 10px; */
  max-height: 50px;
  border: 1px solid rgb(156, 154, 154);
  /* padding: 30px; */
  border-radius: 5px;
}

.container2 {
  /* max-width: 900px; */
  margin: 30px auto;
  overflow-y: auto;
  /* overflow: 10px; */
  min-height: 100px;
  border: 2px solid rgb(101, 101, 101);
  background-color: rgb(231, 240, 255);
  padding: 30px;
  border-radius: 5px;
}

.btn {
  display: inline-block;
  background: #000;
  color: #000000;
  border: 3px;
  padding: 10px 20px;
  margin: 2px;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 20px;
  font-family: inherit;
}

.btn1 {
  width: 200px;
}
.btn2 {
  width: 50%;
}
.btn3 {
  width: 100%;
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
