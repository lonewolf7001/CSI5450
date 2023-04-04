<template>
  <div>
    <h1>&nbsp;</h1>

    <h1></h1>
    <h3>Search by :</h3>
    <h3>&nbsp;</h3>
    <div>
      <div class="container2">
        <div class="row3">
          <div class="col col-lg">
            <router-link
              to="/homesearch/searchbycityandprice"
              v-slot="{ navigate }"
              ><Button
                text="City and price"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
        </div>
        <div class="row3">
          <div class="col col-lg">
            <router-link to="/homesearch/searchbycriteria" v-slot="{ navigate }"
              ><Button
                text="Mutiple Criteria"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
        </div>
        <div class="row3">
          <div class="col1">
            <router-link
              to="/homesearch/searchbysoldcount"
              v-slot="{ navigate }"
              ><Button
                text="House sold-count"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
        </div>
        <div class="row3">
          <div class="col1">
            <router-link to="/homesearch/searchbybybrand" v-slot="{ navigate }"
              ><Button
                text="Appliance brands"
                color="lightblue"
                @click="navigate"
                role="link"
              ></Button
            ></router-link>
          </div>
        </div>
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";

export default {
  name: "HomeSearch",
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

.row {
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
