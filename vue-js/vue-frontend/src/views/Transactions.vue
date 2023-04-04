<template>
  <div>
    <h1>&nbsp;</h1>

    <h1></h1>
    <h3>Transactions :</h3>
    <h3>&nbsp;</h3>
    <div>
      <div class="container2">
        <div class="row3">
          <div class="col col-lg">
            <router-link to="/transactions/newhome" v-slot="{ navigate }"
              ><Button
                text="Add / Register New Home"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <div class="col col-lg">
            <router-link to="/transactions/edithome" v-slot="{ navigate }"
              ><Button
                text="Edit Home"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <!-- <div class="col col-lg"><router-link to="/transactions/deletehome" v-slot="{ navigate }"><Button text="Delete Home" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class="row3">
          <div class="col1">
            <router-link to="/transactions/newagent" v-slot="{ navigate }"
              ><Button
                text="Add New Agent"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <div class="col1">
            <router-link to="/transactions/editagent" v-slot="{ navigate }"
              ><Button
                text="Edit Agent"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <!-- <div class="col1"><router-link to="/transactions/deleteagent" v-slot="{ navigate }"><Button text="Delete Agent" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class="row3">
          <div class="col1">
            <router-link to="/transactions/newowner" v-slot="{ navigate }"
              ><Button
                text="Add New Owner"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <div class="col2">
            <router-link to="/transactions/editowner" v-slot="{ navigate }"
              ><Button
                text="Edit Owner"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <!-- <div class="col3"><router-link to="/transactions/deleteowner" v-slot="{ navigate }"><Button text="Delete Owner" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class="row3">
          <div class="col1">
            <router-link
              to="/transactions/getallappliances"
              v-slot="{ navigate }"
              ><Button
                text="New Appliance"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <div class="col2">
            <router-link to="/transactions/addappliances" v-slot="{ navigate }"
              ><Button
                text="Add Appliances to home"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
          <!-- <div class="col3"><router-link to="/transactions/deleteowner" v-slot="{ navigate }"><Button text="Delete Owner" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class="row3">
          <router-link to="/transactions/homesell" v-slot="{ navigate }"
            ><Button
              text="UPDATE HOME SALE"
              color="red"
              @click="navigate"
              role="link"
            ></Button
          ></router-link>
        </div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";

export default {
  name: "TransactionS",
  components: {
    Button,
  },
};
</script>

<style>
#app {
  align-content: left;
  font: optional;
}

.search-input {
  width: 200px;
  height: 30px;
  border-radius: 2px;
}

.row3 {
  display: grid;
  grid-template-columns: 1fr 1fr;
  vertical-align: left;
  margin: 0.1em;
}

.top5 {
  margin-top: 5px;
}
.top7 {
  margin-top: 7px;
}
.top10 {
  margin-top: 10px;
}
.top15 {
  margin-top: 15px;
}
.top17 {
  margin-top: 17px;
}
.top30 {
  margin-top: 30px;
}
</style>
