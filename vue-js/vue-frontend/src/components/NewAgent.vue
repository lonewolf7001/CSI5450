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
import Button from '../components/Button'
import AgentService from '@/services/AgentService';

export default {
  name: 'NewAgent',
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
        success_message: "",
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
        this.success_message = "CREATED NEW AGENT SUCCESSFULLY";
      })
      .catch(error => {
        console.error(error);
        this.success_message = "ERROR :"+error;
      });
    }
  }
}
</script>