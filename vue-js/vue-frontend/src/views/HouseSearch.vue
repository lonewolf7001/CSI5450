<template>
      <div>
      <h3 style="text-align:left">&nbsp;</h3>
      <h3 style="text-align:left">Search your dream home by city and price</h3>
        <div class="container2">
            <div class="row">
                <div class="col-sm-4"><Button class="btn1" text="City" color="lightblue" disabled role="link" :style="{ fontSize: '16px', padding: '0px 10px' }"></Button></div>
                <div class="col-sm-6">
                    <select v-model="selected_city" style="display: inline-block; margin: 100 10px;">
                        <option value="">Select an city</option>
                        <option v-for="city in cities"
                                :key="city.name" 
                                :value="city.name">{{ city.name }}
                        </option>
                    </select>
                        
                </div>
            </div> 

            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Price range ($)" color="lightblue" disabled role="link" :style="{ fontSize: '16px', padding: '0px 10px' }"></Button></div>
                <div class="col col-sm-4">  
                    <select v-model="price_min">
                        <option disabled value="min">Please select one</option>
                        <option>1000</option>
                        <option>100000</option>
                        <option>200000</option>
                        <option>300000</option>
                        <option>400000</option>
                        <option>500000</option>
                        <option>800000</option>
                        <option>1000000</option>
                        <option>10000000</option>
                    </select>
                </div>
                <div class="col col-sm-4">  
                    <select v-model="price_max" style="display: inline-block; margin: 0 -100px;">
                        <option disabled value="max">Please select one</option>
                        <option>1000</option>
                        <option>100000</option>
                        <option>200000</option>
                        <option>300000</option>
                        <option>400000</option>
                        <option>500000</option>
                        <option>800000</option>
                        <option>1000000</option>
                        <option>10000000</option>
                    </select>
                </div>  
                <div class="col col-sm-4"></div>         
            </div> 
            <!-- <div class="row">
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <div class="col col-lg"></div> 
                <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click=this.getHomesbypriceResponse()></Button></div>            
            </div> -->
            
            

            <div class="row">
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <!-- <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click="owners = this.getAllhomesResponse()"></Button></div> -->
                <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click=this.getHomesbycityandpriceResponse()></Button></div>            
            </div>
            
    </div>
           

        
    <br/>

    <div class="container2">
        Search Results : Showing results for {{this.selected_city}}
        <table class = "table table-striped">
            <thead>
                <tr>
                    <th> ID</th>
                    <th> Area</th>
                    <th> Floors</th>
                    <th> Bedrooms</th>
                    <th> Full Baths</th>
                    <th> Year Built</th>                      
                    <th> Type</th>
                    <th> Latest Price</th>
                    <th> Is for Sale</th>
                    <th></th>                                      
                </tr>
            </thead>
                <tbody>
                    <tr v-for="(home, index) in homes " v-bind:key="home.homeId">
                        <td> {{home.homeId}}</td>
                        <td> {{home.floorSpace}}</td>
                        <td> {{home.numFloors}}</td>
                        <td> {{home.numBedrooms}}</td>
                        <td> {{home.fullBaths}}</td>
                        <td> {{home.yearBuilt}}</td>
                        <td> {{home.homeType}}</td>                        
                        <td> {{home.latestPrice}}</td>
                        <td> {{home.isForSale}}</td>
                        <td>
                            <button @click="expandRow(index)">
                                {{ isRowExpanded(index) ? 'Collapse' : 'Expand' }}
                            </button>
                        </td>                        
                    </tr>
                    <tr v-if="isAnyRowExpanded">
                        <td colspan="3">
                            <div v-if="isExpandedRow">
                            <expandHome :data="getExpandedRowData" />
                            </div>
                        </td>
                    </tr>
                </tbody>
        </table>
    </div>
   
            <div class = "header">
        <h3 style="text-align:left">&nbsp;</h3>
        <h3 style="text-align:left"> Search Home by sold count&nbsp;</h3>
    </div> 
    <!-- //Implementation of with Price min and price max -->
    <div class= "container2">
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Sold count ($)" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-4">  
                    <select v-model="soldcount_min">
                        <option disabled value="min">Please select one</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                    </select>
                </div>
                <div class="col col-sm-4">  
                    <select v-model="soldcount_max">
                        <option disabled value="max">Please select one</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                    </select>
                </div>
             
                <div class="col col-sm-4"></div>         
            </div> 
            <div class="row">
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <div class="col col-lg"></div> 
                <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click=this.getHomesbysoldcountResponse()></Button></div>            
            </div> 
        </div> 
     
                
    <div class="container2">
    Search Results : Showing results for Sold count range {{this.soldcount_min}} to {{this.soldcount_max}}
        <table class = "table table-striped">
            <thead>
                <tr>
                    <th> homeId</th>
                    <th> floorSpace</th>
                    <th> numFloors</th>
                    <th> numBedrooms</th>
                    <th> fullBaths</th>
                    <th> halfBaths</th>
                    <th> landSize</th>  
                    <th> yearBuilt</th>                      
                    <th> homeType</th>
                    <th> isForSale</th>
                    <th> Sold count</th>
                    <th></th> 
                </tr>
            </thead>
                <tbody>
                    <tr v-for="homesS in homes_soldcount " v-bind:key="homesS.homeId">
                        <td> {{homesS.homeId}}</td>
                        <td> {{homesS.floorSpace}}</td>
                        <td> {{homesS.numFloors}}</td>
                        <td> {{homesS.numBedrooms}}</td>
                        <td> {{homesS.fullBaths}}</td>
                        <td> {{homesS.halfBaths}}</td>
                        <td> {{homesS.landSize}}</td>
                        <td> {{homesS.yearBuilt}}</td>
                        <td> {{homesS.homeType}}</td>                        
                        <td> {{homesS.isForSale}}</td>
                        <td> {{homesS.soldCount}}</td>
                    </tr>
                </tbody>
                        
        </table>
    </div>
        <!-- //Implementation of with sold count min and max -->


</div>


</template>

<script>

import Button from '../components/Button'
import HomeService from '@/services/HomeService'
import expandHome from '../components/expandHome.vue'

export default {
  name: 'HomeService',
  components: {
    Button,
    expandHome
  },
  data() {
    return {
        selected_city: '',
        homesearch:{
            floorSpace : null,
            numFloors : null,
            numBedrooms : null,
            fullBaths : null,
            halfBaths : null,
            landSize : null,
            yearBuilt : null,
            homeType : null,
            isForSale: null
        },
        // this.$set(this.homesearch, 'floorSpace', 'Mal)
        homes: [],
        homes_price: [],
        homes_soldcount: [],
        soldcount_min:'',
        soldcount_max:'',
        cities: [],
       price_min : [], 
        price_max : [],
        // expandedRows: [],
        expandedRowIndex: -1,
        // address: {
        //     houseNum: '',
        //     street: '',
        //     aptNum: '',
        //     city: '',
        //     county: '',
        //     zip: '',
        //     homeId: '',
        //     id: ''
        // }
                // checkedHouses: [], 
                // sale : false, 
                // sold : false, 
                // expensive : false, 
                // price_min : [], 
                // price_max : [], 
                // size_min : [],
                // size_max : [],
                // bedroom : [],
                // bathroom : [],
                // Appliance : [] 
    };
  },
  created(){
        HomeService.getAllcities().then((response) => {
        this.cities = response.data;
        console.log(this.cities)
    });
},
  methods:{
    getAllhomesResponse(){
      HomeService.getAllhomes().then((response) => {
        this.homes = response.data;
      });
  },
//   getHomesbycityResponse(){
//       HomeService.getHomesbycity(this.selected_city).then((response) => {
//         this.homes = response.data;
//       })
//       .catch(error => {
//         this.homes = []
//         console.error(error);
//       });
//       console.log(this.homes)
//   },
  getHomesbysoldcountResponse(){
      HomeService.getHomesbysoldcount(this.soldcount_min,this.soldcount_max).then((response) => {
        this.homes_soldcount = response.data;
      })
      .catch(error => {
        this.homes = []
        console.error(error);
      });
      console.log(this.homes)
  },
  expandRow(index) {
      this.expandedRowIndex = this.expandedRowIndex === index ? -1 : index;
    },
    isRowExpanded(index) {
      return this.expandedRowIndex === index;
    },

    getHomesbycityandpriceResponse(){
        console.log(this.price_min)
        console.log(this.price_max)
        console.log(this.selected_city)

        HomeService.getHomesbycityandprice(this.price_min,this.price_max,this.selected_city).then((response)=>{
        this.homes=response.data;
      })
      .catch(error => {
        this.homes = []
        console.error(error);
      });
    }
},
computed: {
    isAnyRowExpanded() {
      return this.expandedRowIndex !== -1;
    },
    isExpandedRow() {
      return this.expandedRowIndex !== -1;
    },
    getExpandedRowData() {
        console.log()
      return this.homes[this.expandedRowIndex];
    },
  },
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

.row {
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