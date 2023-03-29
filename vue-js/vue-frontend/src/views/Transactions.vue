<template>
<div>
  <div class = "header">
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left"> New Agent Registration&nbsp;</h3>
  </div>

  <div class= "container2">
    <div class= "row3"> 
    <div class="col1"><Button class="btn1" text="FirstName" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="new_agent.firstName" placeholder="FirstName" /></div>
    </div>
    <div class= "row3"> 
    <div class="col1"><Button class="btn1" text="LastName" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="new_agent.lastName" placeholder="LastName" /></div>
    </div>
    <div class= "row3"> 
        <div class="col1"><Button class="btn1" text="Phone" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="new_agent.phone" placeholder="Phone number" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Email" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="new_agent.email" placeholder="AgentEmail" /></div>
    </div>
    <div class="row3">
      <div class="col1"></div>
      <div class="col2"><Button class="btn1" text="Add Agent" color="lightgreen" v-on:click="agents = this.CreateAgent()"></Button></div>
    </div>
  </div>

  <div class = "header">
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left"> Home owner registration&nbsp;</h3>
  </div>

  <div class= "container2">
    <div class= "row3">
      <div class="col1"><Button class="btn1" text="SSN" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input  v-model="agent.Ssn" placeholder="XXX XX XXXX"/></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="FirstName" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Fname" placeholder="" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="LastName" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Lname" placeholder="" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Number of Dependents" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.NumDependents" placeholder=""  /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Annual Income" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.AnnualIncome" placeholder="$" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Date of Birth" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.DateofBirth" placeholder="MMDDYYYY" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Profession" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Profession" placeholder="" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Phone" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Phone" placeholder="" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Email" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Email" placeholder="" /></div>
    </div>
    <div class="row3">
      <div class="col1"></div>
      <div class="col2"><Button class="btn1" text="Add Owner" color="lightgreen" role="link"></Button></div>
    </div>
  </div>
</div>
</template>

<script>
import Button from '../components/Button'
import AgentService from '@/services/AgentService';
export default {
  name: 'TransactionS',
  components: {
    Button
  },
  data() {
    return {
      new_agent: {firstName: '',
                  lastName:'',
                  phone : null ,
                  email :''
      },
    }
  },
  methods: {
    CreateAgent(){
      AgentService.create(this.new_agent).then(response => {
        console.log(response.data);
        this.new_agent.firstName = '';
        this.new_agent.lastName = '';
        this.new_agent.phone = '';
        this.new_agent.email = '';
      })
      .catch(error => {
        console.error(error);
      });
    },
    CreateHomeowner(){
      HomeService.create(this.new_owner).then(response => {
        console.log(response.data);
        this.new_owner.firstName = '';
        this.new_owner.lastName = '';
        this.new_agent.phone = '';
        this.new_agent.email = '';
      })
      .catch(error => {
        console.error(error);
      });
    }
  }
}

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

.row3 {
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

