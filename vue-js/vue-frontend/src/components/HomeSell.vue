<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Update Home Sale Data &nbsp;</h3>
    </div>
    <div class="container2">
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Home ID"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="home_sale.homeId" placeholder="homeID" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Sale Date"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <input v-model="home_sale.saleDate" type="date" />
        </div>
        <div>
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Owner ID"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col2">
          <select v-model="home_sale.ownerId">
            <option value="">Select a Owner</option>
            <option v-for="owner in owners" :key="owner.ssn" :value="owner.ssn">
              {{ owner.firstName }} {{ owner.lastName }}
            </option>
          </select>
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Company ID"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <!-- <div class= "col2"> <input v-model="home_sale.companyId" placeholder="companyid" /></div> -->
        <div class="col2">
          <select
            v-model="home_sale.companyId"
            v-on:change="getagentofcompanyResponse()"
          >
            <option value="">Select a Company</option>
            <option
              v-for="company in companies"
              :key="company.id"
              :value="company.id"
            >
              {{ company.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Agent ID"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <!-- <div class= "col2"> <input v-model="home_sale.agentId" placeholder="agentId" /></div> -->
        <div class="col2">
          <select v-model="home_sale.agentId">
            <option value="">Select an Agent</option>
            <option v-for="agent in agents" :key="agent.id" :value="agent.id">
              {{ agent.firstName }} {{ agent.lastName }}
            </option>
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
          <input v-model="home_sale.price" placeholder="price" />
        </div>
      </div>
      <div class="row3">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text="UpdateHomesale"
            color="lightgreen"
            v-on:click="this.UpdateHomeSellResponse()"
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
import AgentService from "@/services/AgentService";
import OwnerService from "../services/OwnerService";
export default {
  name: "HomeSell",
  // vuetify: new Vuetify(),
  components: {
    Button,
  },
  data() {
    return {
      home_sale: {
        homeId: "",
        saleDate: "",
        ownerId: null,
        agentId: null,
        companyId: null,
        price: "",
      },
      agents: [],
      companies: [],
      owners: [],
      success_message: "",
    };
  },
  created() {
    AgentService.getAllcompanies().then((response) => {
      this.companies = response.data;
      console.log(this.companies);
    });
    OwnerService.getAllowners().then((response) => {
      this.owners = response.data;
    });
  },

  methods: {
    UpdateHomeSellResponse() {
      console.log(this.home_sale.saleDate);
      console.log(this.home_sale);
      HomeService.sell(this.home_sale)
        .then((response) => {
          console.log(response.data);
          this.home_sale = [];
          this.success_message = "HOME SALE UPDATED SUCCESSFULLY";
        })
        .catch((error) => {
          console.error(error);
          this.success_message = "ERROR : "+error;
        });
    },
    getagentofcompanyResponse() {
      AgentService.getAgentofcompany(this.home_sale.companyId).then(
        (response) => {
          this.agents = response.data;
          console.log(this.agents);
        }
      );
    },
  },
};
</script>
