<template>
<div>

  <div class = "header">
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left"> Search your agent&nbsp;</h3>


  </div>

  <div class= "container2">

  <div class= "row1"> 
  <div class="col1"><Button class="btn1" text="Agent" color="lightblue" disabled role="link"></Button></div>
    <div class= "col2"> <input v-model="agent.name" placeholder="Agent Name" /></div>
  </div>

  <div class= "row1"> 
      <div class="col1"><Button class="btn1" text="AgentID" color="lightblue" disabled role="link"></Button></div>
    <div class= "col2"> <input v-model="agent.ID" placeholder="AgentID" /></div>
  </div>

  <div class= "row1"> 
  <div class="col1"><Button class="btn1" text="Company" color="lightblue" disabled role="link"></Button></div>
    <div class= "col2"> <input v-model="agent.company" placeholder="CompanyName" /></div>
  </div>


    <div class= "row1"> 
      <div class="col1"><Button class="btn1" text="CITY" color="lightblue" disabled role="link"></Button></div>
    <div class= "col2"> <input v-model="agent.city" placeholder="City Name" /></div>
  </div>

  <div class= "row1"> 
      <div class="col1"><Button class="btn1" text="Commission Rate" color="lightblue" disabled role="link"></Button></div>
    <div class= "col2"> <input v-model="agent.city" placeholder="Purchased Price" /><label for="+15%">&nbsp;&nbsp;+15%&nbsp;&nbsp;&nbsp;</label></div>
  </div>



  <div class="row1">
                  <div class="col1"></div>
                  <div class="col2"><Button class="btn1" text=" Search" color="lightgreen" v-on:click="owners = this.getAllagentsResponse()"></Button></div>
              </div>
  </div>

  <div class="container2">
        Search Results : 
        <table class = "table table-striped">
            <thead>
                <tr>
                  <th> Agent-ID </th>
                    <th> First Name</th>
                    <th> Last Name</th>
                    <th> Phone</th>
                    <th> Email</th>
                </tr>
              </thead>
                <tbody>
                    <tr v-for="agent in agents " v-bind:key="agent.id">
                        <td> {{agent.id}}</td>
                        <td> {{agent.firstName}}</td>
                        <td> {{agent.lastName}}</td>    
                        <td> {{agent.phone}}</td>
                        <td> {{agent.email}} </td>
                    </tr>
                </tbody>
        </table>
    </div>


</div>
</template>

<script>
import Button from '../components/Button'
import AgentService from '../services/AgentService';

export default {
  name: 'AgentSearch',
  components: {
    Button
  },
  data() {
    return {
      agent: {city: ''
      },
      agents: []
    }
  },
  methods:{
    getAllagentsResponse(){
      AgentService.getAllagents().then((response) => {
        this.agents = response.data;
      });
  }
}
};

</script>

<style>
#app{
align-content: left;
font: optional
}
.search-input{
    
    width:200px;
    height:30px;
    border-radius:2px;

}

.row1 {
  display: grid;
  grid-template-columns: 1fr 1fr;
  vertical-align: left;
  margin: 0.1em;
}

.top5 { margin-top:5px; }
.top7 { margin-top:7px; }
.top10 { margin-top:10px; }
.top15 { margin-top:15px; }
.top17 { margin-top:17px; }
.top30 { margin-top:30px; }

</style>




