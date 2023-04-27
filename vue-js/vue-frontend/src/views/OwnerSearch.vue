<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Search home by present owner&nbsp;</h3>
    </div>
    <div class="container2">
      <div class="row2">
        <div class="col1">
          <Button
            class="btn1"
            text="OwnerID"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <select v-model="selected_owner_ssn">
            <option value="">Select An Owner</option>
            <option
              v-for="owner_created in owners_created"
              :key="owner_created.ssn"
              :value="owner_created.ssn"
            >
              {{ owner_created.firstName }} {{ owner_created.lastName }}
            </option>
          </select>
        </div>
      </div>
      <div class="row2">
        <div class="col1">
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
      <div class="row2">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text=" Search"
            color="lightgreen"
            v-on:click="getHomesbyownercityResponse()"
          ></Button>
        </div>
      </div>
    </div>
    <div class="container2">
      Search Results :
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Home ID</th>
            <th>Floorspace</th>
            <th>Floors</th>
            <th>Bedrooms</th>
            <th>Baths</th>
            <th>Half Baths</th>
            <th>landSize</th>
            <th>Year Built</th>
            <th>Home Type</th>
            <th>Is For Sale</th>
            <th>House no.</th>
            <th>Street</th>
            <th>City</th>
            <th>Zip</th>
            <th>latestPrice</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="owner in owners" v-bind:key="owner.city">
            <td>{{ owner.homeId }}</td>
            <td>{{ owner.floorSpace }}</td>
            <td>{{ owner.numFloors }}</td>
            <td>{{ owner.numBedrooms }}</td>
            <td>{{ owner.fullBaths }}</td>
            <td>{{ owner.halfBaths }}</td>
            <td>{{ owner.landSize }}</td>
            <td>{{ owner.yearBuilt }}</td>
            <td>{{ owner.homeType }}</td>
            <td>{{ owner.isForSale }}</td>
            <td>{{ owner.addressInfo.houseNum }}</td>
            <td>{{ owner.addressInfo.street }}</td>
            <td>{{ owner.addressInfo.city }}</td>
            <td>{{ owner.addressInfo.zip }}</td>
            <td>{{ owner.latestPrice }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">
        Search your Owners based on hometype&nbsp;
      </h3>
    </div>

    <div class="container2">
      <div class="row2">
        <div class="col1">
          <Button
            class="btn1"
            text="HomeType"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <!-- <div class="col2">
          <select v-model="hometype">
            <option disabled value="hometypes">Please select one</option>
            <option>M</option>
            <option>C</option>
            <option>A</option>
            <option>T</option>
            <option>N</option>
          </select> -->
        <!-- </div> -->
        <div class="col2">
          <label v-for="homeType in homeTypes" :key="homeType.id">
            <input
              type="checkbox"
              :value="homeType.id"
              v-model="selectedHomeTypes"
            />
            {{ homeType.name }}
          </label>
        </div>
      </div>

      <div class="row2">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text=" Search"
            color="lightgreen"
            v-on:click="getOwnerbyhometypeResponse()"
          ></Button>
        </div>
      </div>
    </div>

    <div class="container2">
      Search Results :
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Profession</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="owner_h in owners_h" v-bind:key="owner_h.ssn">
            <td>{{ owner_h.firstName }} {{ owner_h.lastName }}</td>
            <td>{{ owner_h.phone }}</td>
            <td>{{ owner_h.email }}</td>
            <td>{{ owner_h.profession }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import OwnerService from "../services/OwnerService";
import HomeService from "@/services/HomeService";
export default {
  name: "OwnerSearch",
  components: {
    Button,
  },
  data() {
    return {
      owners: [],
      owners_created: [],
      owners_h: [],
      hometype: [],
      city: "",
      ssn: "",
      homeTypes: [
        { id: "M", name: "Mansion" },
        { id: "C", name: "Condo" },
        { id: "A", name: "Apartment" },
        { id: "T", name: "Townhome" },
        { id: "N", name: "Other" },
      ],
      selectedHomeTypes: [],
      selected_owner_ssn : "",
      selected_city : ""
    };
  },
  created() {
    HomeService.getAllcities().then((response) => {
      this.cities = response.data;
      console.log(this.cities);
    }),
      OwnerService.getAllowners().then((response) => {
        this.owners_created = response.data;
        console.log(this.owners_created);
      });
  },
  methods: {
    getAllownersResponse() {
      OwnerService.getAllowners().then((response) => {
        this.owners = response.data;
      });
    },
    getHomesbyownercityResponse() {
      OwnerService.getHomesbyownercity(this.selected_owner_ssn, this.selected_city)
        .then((response) => {
          this.owners = response.data;
          console.log(this.owners)
        })
        .catch((error) => {
          this.owners = [];
          console.error(error);
        });
    },
    getOwnerbyhometypeResponse() {
      console.log(this.selectedHomeTypes);
      OwnerService.getOwnerbyhometype(this.selectedHomeTypes)
        .then((response) => {
          this.owners_h = response.data;
          this.owners.h;
        })
        .catch((error) => {
          this.owners_h = [];
          console.error(error);
        });
    },

    // created() {
    //     this.getAllownersResponse();
    // }
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

.row2 {
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
