<template>
    <!-- <div id="app">
        <input type="text"
        placeholder="Citysearch"
        class="search-input"
        v-model="searchValue"
        />
    </div> -->
    <!-- <p>City : {{ message }}</p><input v-model="message" placeholder="edit me" /> -->
     <!-- <div class="row">
        <input @change="handleChange" 
              v-model="house.city" 
              name="name" 
              type="text" 
              required>
      </div> -->
      <div>
      <h3 style="text-align:left">&nbsp;</h3>
      <h3 style="text-align:left">Search your dream home</h3>
        <div class="container2">
            <div class="row">
                <div class="col-sm-4"><Button class="btn1" text="City" color="lightblue" disabled role="link"></Button></div>
                <div class="col-sm-6"><input v-model="house.city" placeholder="Enter city" /></div>
            </div>
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Property Type" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-8">
                    <input type="checkbox" id="any" value="any" v-model="house.checkedHouses"><label for="any">&nbsp;&nbsp;Any&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="mansion" value="mansion" v-model="house.checkedHouses"><label for="mansion">&nbsp;&nbsp;Mansion&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="townhomes" value="townhomes" v-model="house.checkedHouses"><label for="townhomes">&nbsp;&nbsp;Townhomes&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="condos" value="condos" v-model="house.checkedHouses"><label for="condos">&nbsp;&nbsp;Condos&nbsp;&nbsp;&nbsp;</label>
                </div>
            </div>
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Price range ($)" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-4">  
                    <select v-model="house.price_min">
                        <option disabled value="min">Please select one</option>
                        <option>50k</option>
                        <option>100k</option>
                        <option>200k</option>
                        <option>300k</option>
                        <option>400k</option>
                        <option>500k</option>
                        <option>800k</option>
                        <option>1M</option>
                    </select>
                </div>
                <div class="col col-sm-4">  
                    <select v-model="house.price_max">
                        <option disabled value="max">Please select one</option>
                        <option>50k</option>
                        <option>100k</option>
                        <option>200k</option>
                        <option>300k</option>
                        <option>400k</option>
                        <option>500k</option>
                        <option>800k</option>
                        <option>1M</option>
                    </select>
                </div>  
                <div class="col col-sm-4"></div>         
            </div>
                        <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Home size (sqft)" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-4">  
                    <select v-model="house.size_min">
                        <option disabled value="min">Please select one</option>
                        <option>500</option>
                        <option>1000</option>
                        <option>2000</option>
                        <option>3000</option>
                        <option>4000</option>
                        <option>5000</option>
                        <option>10000</option>
                    </select>
                </div>
                <div class="col col-sm-4">  
                    <select v-model="house.size_max" >
                        <option disabled value="max" placeholder="min">Please select one</option>
                        <option>500</option>
                        <option>1000</option>
                        <option>2000</option>
                        <option>3000</option>
                        <option>4000</option>
                        <option>5000</option>
                        <option>10000</option>
                    </select>
                </div>  
                <div class="col col-sm-4"></div>         
            </div>
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Bedrooms" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-8">
                    <input type="checkbox" id="bedone" value="bedone" v-model="house.bedone"><label for="one">&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="bedtwo" value="bedtwon" v-model="house.bedtwo"><label for="two">&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="bedthree" value="bedthreees" v-model="house.bedthree"><label for="three">&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="bedfour" value="bedfour" v-model="house.bedfour"><label for="four">&nbsp;&nbsp;4&nbsp;+&nbsp;&nbsp;&nbsp;</label>
                </div>
            </div>
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Bathrooms" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-8">
                    <input type="checkbox" id="bathone" value="Halfbath" v-model="house.Halfbath"><label for="1/2">&nbsp;&nbsp;1/2&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="bathone" value="bathone" v-model="house.bath1"><label for="1">&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="bathtwo" value="bathtwon" v-model="house.bath2"><label for="2">&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="baththree" value="baththree" v-model="house.bath3"><label for="3+">&nbsp;&nbsp;3&nbsp;+&nbsp;&nbsp;</label>
                </div>
            </div>          
            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Listing status" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-6"><input type="checkbox" id="checkbox" v-model="house.sale"><label for="checkbox">&nbsp;&nbsp;Is for sale ?</label></div>
            </div>
            <div class="row">
                <div class="col col-sm-4 top20"><Button class="btn1" text="Sold more than once" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-6"><input type="checkbox" id="checkbox" v-model="house.sold"><label for="checkbox">&nbsp;&nbsp;</label></div>
            </div>
            <div class="row">
                <div class="col col-sm-4 top20"><Button class="btn1" text="Expensive > 1M$" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-6"><input type="checkbox" id="checkbox" v-model="house.expensive"><label for="checkbox">&nbsp;&nbsp;</label></div>
            </div>

            <div class="row">
                <div class="col col-sm-4"><Button class="btn1" text="Appliances Manufacture" color="lightblue" disabled role="link"></Button></div>
                <div class="col col-sm-4">  
                    <select v-model="house.Appliance">
                        <option disabled value="makernames">Please select one</option>
                        <option>Maker1</option>
                        <option>Maker2</option>
                        <option>Maker3</option>
                        <option>Maker4</option>
                
                    </select>
                </div>
                </div>
            
            <div class="row">
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <div class="col col-lg"></div>
                <div class="col col-sm"><Button class="btn1" text="SEARCH" color="lightgreen"  v-on:click="owners = this.getAllhomesResponse()"></Button></div>
            </div>
            




        </div>
     <!-- <form action="/" @keydown="debug">
        Second  : <input type="text" name="fname2"><br>
    </form> -->
    <br/>

    <div class="container2">
        Search Results : 
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
                    <th> homeType</th>                                    
                </tr>
            </thead>
                <tbody>
                    <tr v-for="home in homes " v-bind:key="home.homeId">
                        <td> {{home.homeId}}</td>
                        <td> {{home.floorSpace}}</td>
                        <td> {{home.numFloors}}</td>
                        <td> {{home.numBedrooms}}</td>
                        <td> {{home.fullBaths}}</td>
                        <td> {{home.halfBaths}}</td>
                        <td> {{home.landSize}}</td>
                        <td> {{home.yearBuilt}}</td>
                        <td> {{home.homeType}}</td>                        
                        <td> {{home.isForSale}}</td>                        
                    </tr>
                </tbody>
        </table>
    </div>
</div>
    

    <!-- <h2>Checkbox</h2>
  <input type="checkbox" id="checkbox" v-model="checked">
  <label for="checkbox">Checked: {{ checked }}</label>
  <h2>Multi Checkbox</h2>
  <input type="checkbox" id="any" value="any" v-model="checkedHouses">
  <label for="jack">Jack</label>
  <input type="checkbox" id="mansion" value="mansion" v-model="checkedHouses">
  <label for="john">John</label>
  <input type="checkbox" id="mike" value="Mike" v-model="checkedHouses">
  <label for="mike">Mike</label>
  <p>Checked names: <pre>{{ checkedNames }}</pre></p>

  <h2>Radio</h2>
  <input type="radio" id="one" value="One" v-model="picked">
  <label for="one">One</label>
  <br>
  <input type="radio" id="two" value="Two" v-model="picked">
  <label for="two">Two</label>
  <br> -->
  <!-- <span>Picked: {{ picked }}</span> -->

  <!-- <h2>Select</h2>
  <select v-model="selected">
    <option disabled value="">Please select one</option>
    <option>A</option>
    <option>B</option>
    <option>C</option>
  </select> -->
  <!-- <span>Selected: {{ selected }}</span> -->

  <!-- <h2>Multi Select</h2>
  <select v-model="multiSelected" multiple style="width:100px">
    <option>A</option>
    <option>B</option>
    <option>C</option>
  </select>
  <span>Selected: {{ multiSelected }}</span> -->
</template>

<script>

import Button from '../components/Button'
import HomeService from '@/services/HomeService';

export default {
  name: 'HomeService',
  components: {
    Button
  },
  data() {
    return {
      house: {city: '', 
                checkedHouses: [], 
                sale : false, 
                sold : false, 
                expensive : false, 
                price_min : [], 
                price_max : [], 
                size_min : [],
                size_max : [],
                bedroom : [],
                bathroom : [],
                Appliance : []
                },
                homes: [],
            
    }
  },
  methods:{
    getAllhomesResponse(){
      HomeService.getAllhomes().then((response) => {
        this.homes = response.data;
      });
  }
    // console.log(house.city)
  }
}
    // name :'app',
    // components: {},
    // data: ( ) => ({
    //     searchValue: '', 
    //     users: {
    //         name: 'john',
    //         age: 45,
    //     }
    // }),

// };


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