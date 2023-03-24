<template>
<div>
  <div class = "header">
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left"> New Agent Registration&nbsp;</h3>
  </div>

  <div class= "container2">
    <div class= "row3"> 
    <div class="col1"><Button class="btn1" text="First name" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Fname" placeholder="First name" /></div>
    </div>
    <div class= "row3"> 
    <div class="col1"><Button class="btn1" text="Last name" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Lname" placeholder="LastName" /></div>
    </div>
    <div class= "row3"> 
        <div class="col1"><Button class="btn1" text="Phone" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Phone" placeholder="Contactnumber" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Email" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Email" placeholder="agentemail" /></div>
    </div>
    <div class="row3">
      <div class="col1"></div>
      <div class="col2"><Button class="btn1" text="Add Agent" color="lightgreen" role="link"></Button></div>
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
      <div class="col1"><Button class="btn1" text="First Name" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="agent.Fname" placeholder="" /></div>
    </div>
    <div class= "row3"> 
      <div class="col1"><Button class="btn1" text="Last Name" color="lightblue" disabled role="link"></Button></div>
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
export default {
  name: 'TS',
  components: {
    Button
  },
  data() {
    return {
      agent: {city: ''
      },
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

