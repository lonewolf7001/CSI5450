<template>
    <div>
        <div class = "header">
            <h3 style="text-align:left">&nbsp;</h3>
            <h3 style="text-align:left"> Edit Agent &nbsp;</h3>
        </div>
        <div class= "container2">
            <div class= "row3">
                <div class="col-sm-4"><Button class="btn1" text="Select Agent" color="lightblue" disabled role="link"></Button></div>
                <div class="col-sm-6">
                    <select v-model="selected_agent_id"  v-on:change=selectedAgent()>
                        <option value="">Select an Agent</option>
                        <option v-for="agent in agents"
                                :key="agent.id" 
                                :value="agent.id">{{ agent.firstName }} {{ agent.lastName }}
                        </option>
                    </select>
                    <div class= "row3"> 
            </div>
                </div>
            </div>
        </div>
        <div class= "container2">
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="FirstName" color="lightblue" disabled></Button></div>
            <div class= "col2"> <input v-model="updateAgent.firstName" placeholder="FirstName" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="LastName" color="lightblue" disabled></Button></div>
            <div class= "col2"> <input v-model="updateAgent.lastName" placeholder="LastName" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="Phone" color="lightblue" disabled></Button></div>
            <div class= "col2"> <input v-model="updateAgent.phone" placeholder="Phone number" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Email" color="lightblue" disabled></Button></div>
            <div class= "col2"> <input v-model="updateAgent.email" placeholder="AgentEmail" /></div>
            </div>
            <div class="row3">
            <div class="col1"></div>
            <div class="col2"><Button class="btn1" text="Update Agent" color="lightgreen" v-on:click="this.UpdateAgent()"></Button></div>
        </div>
        </div>
    </div>
</template>
<script>

import Button from '../components/Button'
import AgentService from '@/services/AgentService';

    
    export default {
      name: 'EditAgent',
      components: {
        Button
      },
      data() {
        return {
            // agent: {firstName: '',
            //             lastName:'',
            //             phone : null ,
            //             email :''
            // },
            agents:[],
            selected_agent_id:'',
            updateAgent:[]
          }
        },
        created(){
        AgentService.getAllagents().then((response) => {
        this.agents = response.data;
        console.log(this.agents)
    });
},
    methods: {
        selectedAgent() {
            this.updateAgent = this.agents.find(agents => agents.id === this.selected_agent_id)
            return null
        },
        UpdateAgent(){
                AgentService.update(this.updateAgent).then(response => {
                console.log(response.data);
                this.updateAgent= [];
                // this.updateAgent.lastName = '';
                // this.updateAgent.phone = '';
                // this.updateAgent.email = '';
            })
            .catch(error => {
                console.error(error);
            });
        }
    },
}
    //   methods: {
    //     CreateAgent(){
    //       AgentService.create(this.new_agent).then(response => {
    //         console.log(response.data);
    //         this.new_agent.firstName = '';
    //         this.new_agent.lastName = '';
    //         this.new_agent.phone = '';
    //         this.new_agent.email = '';
    //       })
    //       .catch(error => {
    //         console.error(error);
    //       });
    //     }
    //   }
    // }

    </script>