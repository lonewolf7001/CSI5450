<template>
    <div>
        <div class = "header">
            <h3 style="text-align:left">&nbsp;</h3>
            <h3 style="text-align:left"> Add New Home to Database&nbsp;</h3>
        </div>
        <div class= "container2">
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Floor Space in Sqft. " color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.floorSpace" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Number of Floors" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.numFloors" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Number of Bedrooms" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.numBedrooms" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="No. of Full bathrooms" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.fullBaths" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="No. of Half bathrooms" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.halfBaths" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Landsize acres" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.landSize" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Year Built" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="new_home.yearBuilt" placeholder="" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Home type" color="lightblue" disabled role="link"></Button></div>
            <div class="col2">  
                <select v-model="new_home.homeType">
                    <option disabled value="makernames">Please select one</option>
                    <option>M</option>
                    <option>C</option>
                    <option>A</option>
                    <option>T</option>
                    <option>O</option>      
                </select>
            </div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="Is for Sale" color="lightblue" disabled role="link"></Button></div>
            <div class="col2">  
                <select v-model="new_home.isForSale">
                    <option disabled value="makernames">Please select one</option>
                    <option>true</option>
                    <option>false</option>  
                </select>
            </div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="House Number" color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.houseNum" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="Street" color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.street" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="Apartment No." color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.aptNum" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="City" color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.city" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="County" color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.county" placeholder="" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="ZIP" color="lightblue" disabled role="link"></Button></div>
                <div class= "col2"> <input v-model="new_home.address.zip" placeholder="" /></div>
            </div>

            <div class="row3">
            <div class="col1"></div>
            <div class="col2"><Button class="btn1" text="Add Home" color="lightgreen" v-on:click= this.CreateHome() ></Button></div>
            </div>
        </div>
    </div>
</template>
<script>
import Button from '../components/Button'
import HomeService from '@/services/HomeService';

export default {
  name: 'NewHome',
  components: {
    Button
  },
  data() {
    return {         
      new_home: { floorSpace: null,
                  numFloors : null,
                  numBedrooms: null,
                  fullBaths: null,
                  halfBaths: null,
                  landSize: null,
                  yearBuilt: null,
                  homeType: "",
                  isForSale: false,
                  address : {
                    houseNum : null,
                      street : '',
                      aptNum : null,
                      city : '',
                      county : null,
                      zip : null
                  }
      }
    }
  },
  methods: {
    CreateHome(){
      HomeService.create(this.new_home).then(response => {
        console.log(response.data);
          this.new_home.floorSpace = '';
          this.new_home.numFloors = '';
          this.new_home.numBedrooms = '';
          this.new_home.fullBaths = '';
          this.new_home.halfBaths = '';
          this.new_home.landSize = '';
          this.new_home.yearBuilt = '';
          this.new_home.homeType = '';
          this.new_home.isForSale = '';
          this.new_home.address.houseNum = '';
          this.new_home.address.street = '';
          this.new_home.address.aptNum = '';
          this.new_home.address.city ='';
          this.new_home.address.county ='';
          this.new_home.address.zip='';
      })
      .catch(error => {
        console.error(error);
      });
    }
  }
}


</script>