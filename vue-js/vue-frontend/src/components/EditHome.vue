<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Edit Home &nbsp;</h3>
    </div>
    <div class="container2">
      <div class="row3">
        <div class="col-sm-4">
          <Button
            class="btn1"
            text="Select a city"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col-sm-4">
          <select
            v-model="selected_city"
            v-on:change="getHomesbycityResponse()"
          >
            <option value="">Select an city</option>
            <option v-for="city in cities" :key="city.name" :value="city.name">
              {{ city.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="row3">
        <div class="col-sm-4">
          <Button
            class="btn1"
            text="Select Home"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col-sm-6">
          <select v-model="selected_homeId" v-on:change="selectedHome()">
            <option value="">Select an Home</option>
            <option
              v-for="home in homes"
              :key="home.homeId"
              :value="home.homeId"
            >
              {{ home.homeId }}
            </option>
          </select>
          <div class="row3">
            <!-- <div class="col1"><Button class="btn1" text="FirstName" color="lightblue" disabled></Button></div> -->
            <!-- <div class= "col2"> <input v-model="selected_agent" placeholder="FirstName" /></div> -->
          </div>
        </div>
      </div>
    </div>
    <div class="container2">
      <div>
        <h3>Home ID {{ selected_homeId }}</h3>
        <p><b>Address : </b></p>
        <p>
          {{ home_address.houseNum }} , {{ home_address.street }} ,
          {{ home_address.aptNum }}
        </p>
        <p>
          {{ home_address.city }} , <b>COUNTY : </b>{{ home_address.county }}
        </p>
        <p>ZIP : {{ home_address.zip }}</p>
        <p><b>Owner details : </b></p>
        <p>
          {{ home_owner_details.firstName }} {{ home_owner_details.lastName }}
        </p>
        <p>Dependents : {{ home_owner_details.numDependents }}</p>
        <p>Annual Income : {{ home_owner_details.annualIncome }}</p>
        <p>DOB : {{ home_owner_details.dateOfBirth }}</p>
        <p>Profession : {{ home_owner_details.profession }}</p>
        <p>{{ home_owner_details.phone }}, {{ home_owner_details.email }}</p>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Floor Space in Sqft. "
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.floorSpace" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Number of Floors"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.numFloors" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Number of Bedrooms"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.numBedrooms" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="No. of Full bathrooms"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.fullBaths" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="No. of Half bathrooms"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.halfBaths" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Landsize acres"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.landSize" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Year Built"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateHome.yearBuilt" placeholder="" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Home type"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <select v-model="updateHome.homeType">
            <option disabled value="makernames">Please select one</option>
            <option>M</option>
            <option>C</option>
            <option>A</option>
            <option>T</option>
            <option>O</option>
          </select>
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Is for Sale"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <select v-model="updateHome.isForSale">
            <option disabled value="makernames">Please select one</option>
            <option>true</option>
            <option>false</option>
          </select>
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Price"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="home_price" placeholder="" disabled />
        </div>
      </div>
      <div class="row3">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text="Update Home"
            color="lightgreen"
            v-on:click="this.UpdateHome()"
          ></Button>
        </div>
      </div>
      <div class="row3">
        <div class="col1"></div>
        <div class="col2">
          {{ success_message }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Button from "../components/Button";
import HomeService from "@/services/HomeService";
import OwnerService from "@/services/OwnerService";

export default {
  name: "EditHome",
  components: {
    Button,
  },
  data() {
    return {
      homes: [],
      selected_homeId: "",
      selected_city: "",
      selected_home: "",
      home_owner: "",
      home_price: "",
      home_owner_details: [],
      updateHome: [],
      cities: [],
      home_address: [],
      success_message: "",
    };
  },
  created() {
    HomeService.getAllcities().then((response) => {
      this.cities = response.data;
      console.log(this.cities);
    });
  },
  methods: {
    selectedHome() {
      this.updateHome = this.homes.find(
        (homes) => homes.homeId === this.selected_homeId
      );
      this.home_address = this.updateHome.addressInfo;
      this.home_owner = this.updateHome.currentOwner;
      this.home_price = this.updateHome.latestPrice;
      console.log(this.selected_homeId);
      console.log(this.updateHome);
      this.getHomeownerbyIDResponse();
      return null;
    },

    UpdateHome() {
      HomeService.update(this.updateHome.homeId, this.updateHome)
        .then((response) => {
          console.log(response.data);
          this.updateHome = [];
          (this.home_address = []), (this.home_owner_details = []);
          this.success_message = "UPDATED HOME INFO. SUCCESSFULLY";
        })
        .catch((error) => {
          console.error(error);
          this.success_message = "ERROR : " + error;
        });
    },
    getAddressofhomeResponse() {
      HomeService.getAddressofhome(this.selected_homeId).then((response) => {
        this.home_address = response.data;
      });
    },
    getHomeownerbyIDResponse() {
      OwnerService.getHomeownerbyID(this.home_owner).then((response) => {
        this.home_owner_details = response.data;
      });
    },
    getHomesbycityResponse() {
      HomeService.getHomesbycity(this.selected_city)
        .then((response) => {
          this.homes = response.data;
        })
        .catch((error) => {
          this.homes = [];
          console.error(error);
        });
      console.log(this.homes);
    },
  },
};
</script>
