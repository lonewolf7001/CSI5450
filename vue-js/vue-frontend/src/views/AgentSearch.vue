<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Search your agent&nbsp;</h3>
    </div>

    <div class="container2">
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Company Name"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <!-- <div class= "col2"> <input v-model="home_sale.companyId" placeholder="companyid" /></div> -->
        <div class="col2">
          <select
            v-model="selected_company_id"
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
          <select v-model="selected_agent_id">
            <option value="">Select an Agent</option>
            <option v-for="agent in agents" :key="agent.id" :value="agent.id">
              {{ agent.firstName }} {{ agent.lastName }}
            </option>
          </select>
        </div>
      </div>
      <div class="row1">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text=" Search"
            color="lightgreen"
            v-on:click="this.getAgentsdetailsResponse()"
          ></Button>
        </div>
      </div>
    </div>

    <div class="container2">
      Search Results :
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Agent-ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Commission</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ agent_detail.id }}</td>
            <td>{{ agent_detail.firstName }}</td>
            <td>{{ agent_detail.lastName }}</td>
            <td>{{ agent_detail.phone }}</td>
            <td>{{ agent_detail.email }}</td>
            <td>{{ selected_company_details.commission }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import AgentService from "../services/AgentService";

export default {
  name: "AgentSearch",
  components: {
    Button,
  },
  data() {
    return {
      agents: [],
      selected_company_id: "",
      selected_agent_id: "",
      companies: [],
      agent_detail: [],
      selected_company_details: [],
    };
  },
  created() {
    AgentService.getAllcompanies().then((response) => {
      this.companies = response.data;
      console.log(this.companies);
    });
  },
  methods: {
    getAgentsdetailsResponse() {
      AgentService.getAgentsdetails(this.selected_agent_id).then((response) => {
        this.agent_detail = response.data;
      });
    },
    getagentofcompanyResponse() {
      AgentService.getAgentofcompany(this.selected_company_id).then(
        (response) => {
          this.agents = response.data;
          console.log(this.agents);
          this.agent_detail = "";
        }
      );
      this.selected_company_details = this.companies.find(
        (companies) => companies.id === this.selected_company_id
      );
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

.row1 {
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
