<template>


<div>
  <h1>&nbsp;</h1>


  <h1> </h1>
  <h3>Transactions : </h3>
  <h3> &nbsp;</h3>
  <div>
    <div class= "container2">
        <div class= "row3"> 
          <div class="col col-lg"><router-link to="/transactions/newhome" v-slot="{ navigate }"><Button text="Add / Register New Home" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <div class="col col-lg"><router-link to="/transactions/edithome" v-slot="{ navigate }"><Button text="Edit Home" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <!-- <div class="col col-lg"><router-link to="/transactions/deletehome" v-slot="{ navigate }"><Button text="Delete Home" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class= "row3"> 
          <div class="col1"><router-link to="/transactions/newagent" v-slot="{ navigate }"><Button text="Add New Agent" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <div class="col1"><router-link to="/transactions/editagent" v-slot="{ navigate }"><Button text="Edit Agent" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <!-- <div class="col1"><router-link to="/transactions/deleteagent" v-slot="{ navigate }"><Button text="Delete Agent" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class= "row3"> 
          <div class="col1"><router-link to="/transactions/newowner" v-slot="{ navigate }"><Button text="Add New Owner" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <div class="col2"><router-link to="/transactions/editowner" v-slot="{ navigate }"><Button text="Edit Owner" color="lightblue" @click="navigate" role="link"></Button></router-link></div>
          <!-- <div class="col3"><router-link to="/transactions/deleteowner" v-slot="{ navigate }"><Button text="Delete Owner" color="lightblue" @click="navigate" role="link"></Button></router-link></div> -->
        </div>
        <div class= "row3"> 
          <router-link to="/transactions/homesell" v-slot="{ navigate }"><Button text="Home Sale" color="lightblue" @click="navigate" role="link"></Button></router-link>
        </div>
          <router-view></router-view>
      </div>
  </div>  
</div>
</template>

<script>
import Button from '../components/Button'

export default {
  name: 'TransactionS',
  components: {
    Button
  }
  // data() {
  //   return {
  //     new_agent: {firstName: '',
  //                 lastName:'',
  //                 phone : null ,
  //                 email :''
  //     },

  //     new_owner: { ssn:'',
  //                 firstName:'',
  //                 lastName:'',
  //                 numDependents:'',
  //                 annualIncome:'',
  //                 dateOfBirth:'',
  //                 profession:'',
  //                 phone:'',
  //                 email:''
  //                },
                 
  //     new_home: { floorSpace: null,
  //                 numFloors : null,
  //                 numBedrooms: null,
  //                 fullBaths: null,
  //                 halfBaths: null,
  //                 landSize: null,
  //                 yearBuilt: null,
  //                 homeType: "",
  //                 isForSale: false,
  //                 address : {
  //                   houseNum : null,
  //                     street : '',
  //                     aptNum : null,
  //                     city : '',
  //                     county : null,
  //                     zip : null
  //                 }
  //     }
  //   }
  // },
  // methods: {
  //   CreateAgent(){
  //     AgentService.create(this.new_agent).then(response => {
  //       console.log(response.data);
  //       this.new_agent.firstName = '';
  //       this.new_agent.lastName = '';
  //       this.new_agent.phone = '';
  //       this.new_agent.email = '';
  //     })
  //     .catch(error => {
  //       console.error(error);
  //     });
  //   },
  //   CreateOwner(){
  //     OwnerService.create(this.new_owner).then(response => {
  //       console.log(response.data);
  //       this.new_owner.ssn = '';
  //       this.new_owner.firstName = '';
  //       this.new_owner.lastName = '';
  //       this.new_owner.numDependents = '';
  //       this.new_owner.annualIncome = '';
  //       this.new_owner.dateOfBirth = '';
  //       this.new_owner.profession = '';
  //       this.new_owner.phone = '';
  //       this.new_owner.email = '';
        
  //     })
  //     .catch(error => {
  //       console.error(error);
  //     });
  //   },
  //   CreateHome(){
  //     HomeService.create(this.new_home).then(response => {
  //       console.log(response.data);
  //         this.new_home.floorSpace = '';
  //         this.new_home.numFloors = '';
  //         this.new_home.numBedrooms = '';
  //         this.new_home.fullBaths = '';
  //         this.new_home.halfBaths = '';
  //         this.new_home.landSize = '';
  //         this.new_home.yearBuilt = '';
  //         this.new_home.homeType = '';
  //         this.new_home.isForSale = '';
  //         this.new_home.address.houseNum = '';
  //         this.new_home.address.street = '';
  //         this.new_home.address.aptNum = '';
  //         this.new_home.address.city ='';
  //         this.new_home.address.county ='';
  //         this.new_home.address.zip='';
  //     })
  //     .catch(error => {
  //       console.error(error);
  //     });
  //   }
  // }
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

