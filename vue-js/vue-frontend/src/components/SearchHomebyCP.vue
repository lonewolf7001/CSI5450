<template>
  <div>
    <h3 style="text-align: left">&nbsp;</h3>
    <!-- input felids for price and city -->
    <h3 style="text-align: left">Search your dream home by city and price</h3>
    <div class="container2">
      <div class="row">
        <div class="col-sm-4">
          <Button
            class="btn1"
            text="City"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col-sm-6">
          <select v-model="selected_city">
            <option value="">Select an city</option>
            <option v-for="city in cities" :key="city.name" :value="city.name">
              {{ city.name }}
            </option>
          </select>
        </div>
      </div>

      <div class="row">
        <div class="col col-sm-4">
          <Button
            class="btn1"
            text="Price range ($)"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col col-sm-4">
          <select v-model="price_min">
            <option disabled value="min">Please select one</option>
            <option>0</option>
            <option>1000</option>
            <option>100000</option>
            <option>200000</option>
            <option>300000</option>
            <option>400000</option>
            <option>500000</option>
            <option>800000</option>
            <option>1000000</option>
            <option>10000000</option>
          </select>
        </div>
        <div class="col col-sm-4">
          <select v-model="price_max">
            <option disabled value="max">Please select one</option>
            <option>1000</option>
            <option>100000</option>
            <option>200000</option>
            <option>300000</option>
            <option>400000</option>
            <option>500000</option>
            <option>800000</option>
            <option>1000000</option>
            <option>10000000</option>
          </select>
        </div>
        <div class="col col-sm-4"></div>
      </div>
      <!-- <div class="row">
                  <div class="col col-lg"></div>
                  <div class="col col-lg"></div>
                  <div class="col col-lg"></div> 
                  <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click=this.getHomesbypriceResponse()></Button></div>            
              </div> -->

      <div class="row">
        <div class="col col-lg"></div>
        <div class="col col-lg"></div>
        <div class="col col-lg"></div>
        <!-- <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click="owners = this.getAllhomesResponse()"></Button></div> -->
        <div class="col col-sm">
          <Button
            class="btn1"
            text="SEARCH"
            color="lightgreen"
            v-on:click="this.getHomesbycityandpriceResponse()"
          ></Button>
        </div>
      </div>
    </div>

    <br />
    <!-- table for search by city and price -->
    <div class="container2">
      Search Results : Showing results for {{ this.selected_city }}
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Area</th>
            <th>Floors</th>
            <th>Bedrooms</th>
            <th>Full Baths</th>
            <th>Year Built</th>
            <th>Type</th>
            <th>Latest Price</th>
            <th>Is for Sale</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(home, index) in homes" v-bind:key="home.homeId">
            <td>{{ home.homeId }}</td>
            <td>{{ home.floorSpace }}</td>
            <td>{{ home.numFloors }}</td>
            <td>{{ home.numBedrooms }}</td>
            <td>{{ home.fullBaths }}</td>
            <td>{{ home.yearBuilt }}</td>
            <td>{{ home.homeType }}</td>
            <td>{{ home.latestPrice }}</td>
            <td>{{ home.isForSale }}</td>
            <td>
              <button @click="expandRow(index)">
                {{ isRowExpanded(index) ? "Collapse" : "GetHomeDetails" }}
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
  name: "SearchHomebyCP",
  components: {
    Button,
    expandHome,
    expandOwner,
  },
  data() {
    return {
      selected_city: "",
      homes: [],
      homes_c: [],
      homes_b: [],
      homes_price: [],
      cities: [],
      price_min: [],
      price_max: [],
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

    getHomesbycityandpriceResponse() {
      console.log(this.price_min);
      console.log(this.price_max);
      console.log(this.selected_city);

      HomeService.getHomesbycityandprice(
        this.price_min,
        this.price_max,
        this.selected_city
      )
        .then((response) => {
          this.homes = response.data;
        })
        .catch((error) => {
          this.homes = [];
          console.error(error);
        });
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
