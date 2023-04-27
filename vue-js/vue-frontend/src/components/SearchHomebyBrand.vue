<template>
  <div>
    <h3 style="text-align: left">&nbsp;</h3>
    <br />
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Search Home by same brand&nbsp;</h3>
    </div>

    <div class="container2">
      <div class="row">
        <div class="col col-sm-4">
          <Button
            class="btn1"
            text="Appliances Manufacturer"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col col-sm-4">
          <select v-model="manufacturer">
            <option disabled value="makernames">Please select one</option>
            <option>MAYTAG</option>
            <option>SAMSUNG</option>
            <option>LG</option>
            <option>INSIGNIA</option>
            <option>THERMADOR</option>
          </select>
        </div>
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
            v-on:click="this.getHomesbysinglebrandResponse()"
          ></Button>
        </div>
      </div>
    </div>

    <!-- table for search by samebrand -->

    <div class="container2">
      Search Results : Showing results for {{ this.selected_city }}
      <table class="table table-striped">
        <thead>
          <tr>
            <th>homeId</th>
            <th>floorSpace</th>
            <th>landSize</th>
            <th>yearBuilt</th>
            <th>homeType</th>
            <th>isForSale</th>
            <!-- <th>ownerId</th> -->
            <th>Manufacturer</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(home, index3) in homes" v-bind:key="home.homeId">
            <td>{{ home.homeId }}</td>
            <td>{{ home.floorSpace }}</td>
            <td>{{ home.landSize }}</td>
            <td>{{ home.yearBuilt }}</td>
            <td>{{ home.homeType }}</td>
            <td>{{ home.isForSale }}</td>
            <!-- <td>{{ home.ownerId }}</td> -->
            <td>{{ home.manufacturer }}</td>
            <td>
              <button @click="expandRow(index3)">
                {{ isRowExpanded(index3) ? "Collapse" : "GetHomeDetails" }}
              </button>
            </td>
            <td>
              <button @click="expandRow2(index)">
                {{ isRow2Expanded(index) ? "Collapse" : "GetOwnerDetails" }}
              </button>
            </td>
          </tr>
          <tr v-if="isAnyRowExpanded">
            <td colspan="3">
              <div v-if="isExpandedRow">
                <expandHome :data="getExpandedRowData" />
              </div>
            </td>
          </tr>
          <tr v-if="isAnyRow2Expanded">
            <td colspan="3">
              <div v-if="isExpandedRow2">
                <expandOwner :data="getExpandedRow2Data" />
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import HomeService from "@/services/HomeService";
import expandHome from "../components/expandHome.vue";
import expandOwner from "../components/expandOwner.vue";

export default {
  name: "SearchHomebyBrand",
  components: {
    Button,
    expandHome,
    expandOwner,
  },
  data() {
    return {
      manufacturer: "",
      // selected_city: "",
      // criteria: {
      //   floorSpace: "",
      //   numFloors: "",
      //   numBedrooms: "",
      //   fullBaths: "",
      //   halfBaths: "",
      //   landSize: "",
      //   yearBuilt: "",
      //   homeType: "",
      //   isForSale: "",
      // },
      // criteria1: {
      //   floorSpace: 1790,
      //   numFloors: 2,
      //   numBedrooms: 3,
      //   fullBaths: 3,
      //   halfBaths: 1,
      //   landSize: 0.7,
      //   yearBuilt: 1972,
      //   homeType: "N",
      //   isForSale: true,
      // },
      homes: [],
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
    getHomesbysinglebrandResponse() {
      console.log(this.manufacturer);
      HomeService.getHomesbysinglebrand(this.manufacturer)
        .then((response) => {
          this.homes = response.data;
        })
        .catch((error) => {
          this.homes = [];
          console.error(error);
        });
      // console.log(this.homes)
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
