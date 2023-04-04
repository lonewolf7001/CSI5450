<template>
  <div>
    <h3 style="text-align: left">&nbsp;</h3>

    <br />
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Search Home by sold count&nbsp;</h3>
    </div>
    <!-- //Implementation of with soldcount  min and  max -->
    <div class="container2">
      <div class="row">
        <div class="col col-sm-4">
          <Button
            class="btn1"
            text="Sold count ($)"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col col-sm-4">
          <select v-model="soldcount_min">
            <option disabled value="min">Please select one</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
          </select>
        </div>
        <div class="col col-sm-4">
          <select v-model="soldcount_max">
            <option disabled value="max">Please select one</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
          </select>
        </div>

        <div class="col col-sm-4"></div>
      </div>
      <div class="row">
        <div class="col col-lg"></div>
        <div class="col col-lg"></div>
        <div class="col col-lg"></div>
        <div class="col col-sm">
          <Button
            class="btn1"
            text="SEARCH"
            color="lightgreen"
            v-on:click="this.getHomesbysoldcountResponse()"
          ></Button>
        </div>
      </div>
    </div>

    <!-- table for search by soldcount -->
    <div class="container2">
      Search Results : Showing results for Sold count range
      {{ this.soldcount_min }} to {{ this.soldcount_max }}
      <table class="table table-striped">
        <thead>
          <tr>
            <th>homeId</th>
            <th>floorSpace</th>
            <th>numFloors</th>
            <th>numBedrooms</th>
            <th>fullBaths</th>
            <th>halfBaths</th>
            <th>landSize</th>
            <th>yearBuilt</th>
            <th>homeType</th>
            <th>isForSale</th>
            <th>Sold count</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="homesS in homes_soldcount" v-bind:key="homesS.homeId">
            <td>{{ homesS.homeId }}</td>
            <td>{{ homesS.floorSpace }}</td>
            <td>{{ homesS.numFloors }}</td>
            <td>{{ homesS.numBedrooms }}</td>
            <td>{{ homesS.fullBaths }}</td>
            <td>{{ homesS.halfBaths }}</td>
            <td>{{ homesS.landSize }}</td>
            <td>{{ homesS.yearBuilt }}</td>
            <td>{{ homesS.homeType }}</td>
            <td>{{ homesS.isForSale }}</td>
            <td>{{ homesS.soldCount }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import HomeService from "@/services/HomeService";
// import expandHome from "../components/expandHome.vue";
// import expandOwner from "../components/expandOwner.vue";

export default {
  name: "HomeService",
  components: {
    Button,
    // expandHome,
    // expandOwner,
  },
  data() {
    return {
      selected_city: "",
      criteria: {
        floorSpace: "",
        numFloors: "",
        numBedrooms: "",
        fullBaths: "",
        halfBaths: "",
        landSize: "",
        yearBuilt: "",
        homeType: "",
        isForSale: "",
      },
      criteria1: {
        floorSpace: 1790,
        numFloors: 2,
        numBedrooms: 3,
        fullBaths: 3,
        halfBaths: 1,
        landSize: 0.7,
        yearBuilt: 1972,
        homeType: "N",
        isForSale: true,
      },
      homes: [],
      homes_c: [],
      homes_b: [],
      homes_price: [],
      homes_soldcount: [],
      soldcount_min: "",
      soldcount_max: "",
      cities: [],
      price_min: [],
      price_max: [],
      home_Appliance: [],
      expandedRowIndex: -1,
      expandedRow2Index: -1,
    };
  },
  created() {
    HomeService.getAllcities().then((response) => {
      this.cities = response.data;
      console.log(this.cities);
    });
  },
  methods: {
    getHomesbysoldcountResponse() {
      HomeService.getHomesbysoldcount(this.soldcount_min, this.soldcount_max)
        .then((response) => {
          this.homes_soldcount = response.data;
        })
        .catch((error) => {
          this.homes = [];
          console.error(error);
        });
      console.log(this.homes);
    },
    expandRow(index) {
      this.expandedRowIndex = this.expandedRowIndex === index ? -1 : index;
    },
    isRowExpanded(index) {
      return this.expandedRowIndex === index;
    },

    expandRow2(index) {
      this.expandedRow2Index = this.expandedRow2Index === index ? -1 : index;
    },
    isRow2Expanded(index) {
      return this.expandedRow2Index === index;
    },
  },
  computed: {
    isAnyRowExpanded() {
      return this.expandedRowIndex !== -1;
    },
    isExpandedRow() {
      return this.expandedRowIndex !== -1;
    },
    getExpandedRowData() {
      console.log();
      return this.homes[this.expandedRowIndex];
    },
    isAnyRow2Expanded() {
      return this.expandedRow2Index !== -1;
    },
    isExpandedRow2() {
      return this.expandedRow2Index !== -1;
    },
    getExpandedRow2Data() {
      console.log(this.index2, this.index2);
      return this.homes[this.expandedRow2Index];
    },
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
