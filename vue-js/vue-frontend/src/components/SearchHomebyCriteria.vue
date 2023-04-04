<template>
    <div>
      <h3 style="text-align: left">&nbsp;</h3>
      <div class="header">
        <h3 style="text-align: left">&nbsp;</h3>
        <h3 style="text-align: left">Search Home by criteria&nbsp;</h3>
      </div>
      <div class="container2">
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="Floorspace"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <input v-model="criteria.floorSpace" placeholder="sqft" />
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="No of Floors"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.numFloors">
              <option disabled value="select floors">Please select one</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="No of Bedrooms"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.numBedrooms">
              <option disabled value="select Bedrooms">Please select one</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="No of FullBaths"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.fullBaths">
              <option disabled value="select floors">Please select one</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="No of halfbaths"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.halfBaths">
              <option disabled value="select floors">Please select one</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
            </select>
          </div>
        </div>
  
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="Landsize"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <input v-model="criteria.landSize" placeholder="acres" />
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="yearBuilt"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <input v-model="criteria.yearBuilt" placeholder="year(YYYY)" />
          </div>
        </div>
  
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="Home type"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.homeType">
              <option disabled value="hometypes">Please select one</option>
              <option>M</option>
              <option>C</option>
              <option>A</option>
              <option>T</option>
              <option>N</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col col-sm-4">
            <Button
              class="btn1"
              text="Is for Sale"
              color="lightblue"
              disabled
              role="link"
            ></Button>
          </div>
          <div class="col col-sm-4">
            <select v-model="criteria.isForSale">
              <option disabled value="makernames">Please select one</option>
              <option>true</option>
              <option>false</option>
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
              v-on:click="this.getHomesbycriteriaResponse()"
            ></Button>
          </div>
        </div>
      </div>
  
      <!-- table for search by criteria -->
      <div class="container2">
        Search Results : Showing results for {{ this.selected_city }}
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Area</th>
              <th>Floors</th>
              <th>Bedrooms</th>
              <th>Full Baths</th>
              <th>half Baths</th>
              <th>Land Size</th>
              <th>Year Built</th>
              <th>Type</th>
              <th>Is for Sale</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(home, index) in homes_c" v-bind:key="home.homeId">
              <td>{{ home.floorSpace }}</td>
              <td>{{ home.numFloors }}</td>
              <td>{{ home.numBedrooms }}</td>
              <td>{{ home.fullBaths }}</td>
              <td>{{ home.halfBaths }}</td>
              <td>{{ home.landSize }}</td>
              <td>{{ home.yearBuilt }}</td>
              <td>{{ home.homeType }}</td>
              <td>{{ home.latestPrice }}</td>
              <td>{{ home.isForSale }}</td>
              <td>
                <button @click="expandRow(index)">
                  {{ isRowExpanded(index) ? "Collapse" : "GetHomeDetails" }}
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
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import Button from "../components/Button";
  import HomeService from "@/services/HomeService";
//   import expandHome from "../components/expandHome.vue";
//   import expandOwner from "../components/expandOwner.vue";
  
  export default {
    name: "HomeService",
    components: {
      Button,
    //   expandHome,
    //   expandOwner,
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

      getHomesbycriteriaResponse() {
        console.log(this.criteria);
        this.homes_c = [];
        //Delete key values which are empty
        for (const [key, value] of Object.entries(this.criteria)) {
          if (value === "") {
            delete this.criteria[key];
          }
        }
        HomeService.getHomesbycriteria(this.criteria)
          .then((response) => {
            this.homes_c = response.data;
          })
          .catch((error) => {
            this.homes_c = [];
            console.error(error);
          });
        console.log(this.homes_c);
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
  