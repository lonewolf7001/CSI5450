<template>
<div>
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left">Search your dream home by city</h3>
    <div class="container2">
        <div class="row">
            <div class="col-sm-4"><Button class="btn1" text="City" color="lightblue" disabled role="link"></Button></div>
            <div class="col-sm-6">
                <select v-model="selected_city" v-on:change=getHomesbycityResponse()>
                    <option value="">Select an city</option>
                    <option v-for="city in cities"
                            :key="city.name"
                            :value="city.name">{{ city.name }}
                    </option>
                </select>
            </div>
        </div>
        <div class= "row3">
                <div class="col-sm-4"><Button class="btn1" text="Select Home" color="lightblue" disabled role="link"></Button></div>
                <div class="col-sm-6">
                    <select v-model="selected_homeId"  v-on:change=selectedHome()>
                        <option value="">Select an Home </option>
                        <option v-for="home in homes"
                                :key="home.homeId" 
                                :value="home.homeId">{{ home.homeId }}
                        </option>
                    </select>
                </div>
        </div>
        <div>
            <h3> Home ID {{ selected_homeId }}</h3>
            <p> FloorSpace: {{ selectedHome_details.floorSpace }}</p>
            <p> NumFloors : {{ selectedHome_details.numFloors }}</p>
            <p> Bedrooms : {{ selectedHome_details.numBedrooms }}</p>
            <p> Details : {{ selectedHome_details.fullBaths }}</p>
            <p> Details : {{ selectedHome_details.halfBaths }}</p>
            <p> Details : {{ selectedHome_details.landSize }}</p>
            <p> Details : {{ selectedHome_details.yearBuilt }}</p>
            <p> Details : {{ selectedHome_details.homeType }}</p>
            <p> Details : {{ selectedHome_details.isForSale }}</p>
            <p> Details : {{ selectedHome_details.latestPrice }}</p>
            <p> <b>Address : </b></p>
            <p>{{ home_address.houseNum }} , {{ home_address.street }} , {{ home_address.aptNum }}</p>
            <p>{{ home_address.city }} , <b>COUNTY : </b>{{ home_address.county }} </p>
            <p>ZIP : {{ home_address.zip }}</p>
            <p> <b>Owner details : </b></p>
            <p>{{ home_owner_details.firstName }} {{ home_owner_details.lastName }} </p>
            <p>Dependents    :  {{ home_owner_details.numDependents }} </p>
            <p>Annual Income :  {{ home_owner_details.annualIncome }} </p>
            <p>DOB           :  {{ home_owner_details.dateOfBirth }} </p>
            <p>Profession    :  {{ home_owner_details.profession }} </p>
            <p>{{ home_owner_details.phone }}, {{ home_owner_details.email }} </p>
        </div>
        <div class="row3">
            <div class="col1"></div>
            <div class="col2"><Button class="btn1" text="Delete Home" color="lightred" v-on:click="this.deleteHomeResponse()"></Button></div>
        </div>
</div>
</div>
</template>

<script>
import Button from '../components/Button'
import HomeService from '@/services/HomeService'
import OwnerService from '../services/OwnerService'

export default {
  name: 'DeleteHome',
  components: {
    Button,
  },
  data(){
    return{
        selected_city:'',
        selected_home_id:'',
        homes:[],
        selectedHome_details:[],
        home_owner_details:[],
        home_address :[],
        cities:[],
    }
  },
  created(){
        HomeService.getAllcities().then((response) => {
        this.cities = response.data;
        console.log(this.cities)
    });
},
methods :{
    selectedHome() {
            this.selectedHome_details = this.homes.find(homes => homes.homeId === this.selected_homeId)
            console.log(this.selected_homeId);
            console.log(this.selectedHome_details);
            this.home_address = this.selectedHome_details.addressInfo
            this.getHomeownerbyIDResponse();
            return null
        },
        getHomesbycityResponse(){
            HomeService.getHomesbycity(this.selected_city).then((response) => {
            this.homes = response.data;
            })
            .catch(error => {
            this.homes = []
            console.error(error);
        });
        },
        getHomeownerbyIDResponse(){
            OwnerService.getHomeownerbyID(this.selectedHome_details.currentOwner).then((response) => {
            this.home_owner_details = response.data;
            });
        },
        deleteHomeResponse(){
            HomeService.deleteHomebyId(this.selected_homeId).then(response => {
                console.log(response.data)
                this.home_owner_details= [];
                this.selectedHome_details=[]
            })
            .catch(error => {
                console.error(error);
            });
        },
    }
}


</script>
