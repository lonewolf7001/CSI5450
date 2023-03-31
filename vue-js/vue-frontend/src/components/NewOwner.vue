<template>
    <div>
        <div class = "header">
            <h3 style="text-align:left">&nbsp;</h3>
            <h3 style="text-align:left"> Home owner registration&nbsp;</h3>
        </div> 

        <div class= "container2">
            <div class= "row3">
            <div class="col1"><Button class="btn1" text="SSN" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input  v-model="new_owner.ssn" placeholder="XXX XX XXXX"/></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="FirstName" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.firstName" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="LastName" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.lastName" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Number of Dependents" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.numDependents" placeholder=""  /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Annual Income" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.annualIncome" placeholder="$" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Date of Birth" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.dateOfBirth" placeholder="MMDDYYYY" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Profession" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.profession" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Phone" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.phone" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Email" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_owner.email" placeholder="" /></div>
            </div>
            <div class="row3">
            <div class="col1"></div>
            <div class="col2"><Button class="btn1" text="Add Owner" color="lightgreen" v-on:click= this.CreateOwner() ></Button></div>
            </div>
        </div> 
    </div>
</template>
<script>
import Button from '../components/Button'
import OwnerService from '@/services/OwnerService';

export default {
  name: 'NewOwner',
  components: {
    Button
  },
  data() {
    return {
      new_owner: { ssn:'',
                  firstName:'',
                  lastName:'',
                  numDependents:'',
                  annualIncome:'',
                  dateOfBirth:'',
                  profession:'',
                  phone:'',
                  email:''
                 }
      }
    
  },
  methods: {
    CreateOwner(){
      OwnerService.create(this.new_owner).then(response => {
        console.log(response.data);
        this.new_owner.ssn = '';
        this.new_owner.firstName = '';
        this.new_owner.lastName = '';
        this.new_owner.numDependents = '';
        this.new_owner.annualIncome = '';
        this.new_owner.dateOfBirth = '';
        this.new_owner.profession = '';
        this.new_owner.phone = '';
        this.new_owner.email = '';
        
      })
      .catch(error => {
        console.error(error);
      });
    },
  }
}
</script>