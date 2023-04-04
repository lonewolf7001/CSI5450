<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Search your Owner&nbsp;</h3>
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
          <input v-model="ssn" placeholder="XXX XX XXXX" />
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
        <div class="col2"><input v-model="city" placeholder="City name" /></div>
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
            <th>addressInfo</th>
            <th>street</th>
            <th>city</th>
            <th>zip</th>
            <th>homeId</th>
            <th>Id</th>
            <th>latestPrice</th>
            <th>currentOwner</th>
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
            <td>{{ owner.addressInfo }}</td>
            <td>{{ owner.street }}</td>
            <td>{{ owner.city }}</td>
            <td>{{ owner.zip }}</td>
            <td>{{ owner.homeId }}</td>
            <td>{{ owner.ID }}</td>
            <td>{{ owner.latestPrice }}</td>
            <td>{{ owner.currentOwner }}</td>
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
        <div class="col2">
          <select v-model="hometype">
            <option disabled value="hometypes">Please select one</option>
            <option>M</option>
            <option>C</option>
            <option>A</option>
            <option>T</option>
            <option>N</option>
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
            <th>addressInfo</th>
            <th>street</th>
            <th>city</th>
            <th>zip</th>
            <th>homeId</th>
            <th>Id</th>
            <th>latestPrice</th>
            <th>currentOwner</th>
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
            <td>{{ owner.addressInfo }}</td>
            <td>{{ owner.street }}</td>
            <td>{{ owner.city }}</td>
            <td>{{ owner.zip }}</td>
            <td>{{ owner.homeId }}</td>
            <td>{{ owner.ID }}</td>
            <td>{{ owner.latestPrice }}</td>
            <td>{{ owner.currentOwner }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import OwnerService from "../services/OwnerService";
export default {
  name: "OwnerSearch",
  components: {
    Button,
  },
  data() {
    return {
      owners: [],
      hometype: [],
      city: "",
      ssn: "",
    };
  },
  methods: {
    getAllownersResponse() {
      OwnerService.getAllowners().then((response) => {
        this.owners = response.data;
      });
    },
    getHomesbyownercityResponse() {
      OwnerService.getHomesbyownercity(this.ssn, this.city)
        .then((response) => {
          this.owners = response.data;
        })
        .catch((error) => {
          this.owners = [];
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
