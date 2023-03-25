<template>
  <!----<h3>OwnerSearch</h3>
  <router-link to="/">Go Back</router-link>
</template>

<script>
export default {
  name: 'OwnerSearch',
  inheritAttrs: false, // disable 'non-props' warning
};
</script>-->
<div>
  <div class = "header">
    <h3 style="text-align:left">&nbsp;</h3>
    <h3 style="text-align:left"> Search your Owner&nbsp;</h3>
  </div>
  <div class="container2">
    <div class="row2">
      <div class="col1"><Button class="btn1" text="Owner" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="owner.name" placeholder="owner name" /></div>
    </div>
    <div class="row2"> 
      <div class="col1"><Button class="btn1" text="City" color="lightblue" disabled role="link"></Button></div>
      <div class= "col2"> <input v-model="owner.city" placeholder="City name" /></div>
    </div>

    <div class="row2"> 
      <div class="col1"><Button class="btn1" text="Expensive Homes" color="lightblue" disabled role="link"></Button></div>
      <div class="col2">
                    <input type="checkbox" id="1million" value="1 million" v-model="owner.city"><label for=" 1million-5 million">&nbsp;&nbsp;1million-5 million&nbsp;&nbsp;&nbsp;</label>
                    <input type="checkbox" id="5million+" value="5million+" v-model="owner.city"><label for="5million+">&nbsp;&nbsp;5million+&nbsp;&nbsp;&nbsp;</label>

                </div>
    </div>
    <div class="row2"> 
      <div class="col1"><Button class="btn1" text="Previously Owned" color="lightblue" disabled role="link"></Button></div>
      <div class="col2"><input type="checkbox" id="preOwned" value="" v-model="owner.city"><label for="preowned ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></div>
    </div>

    <div class="row2">
      <div class="col1"></div>
      <div class="col2"><Button class="btn1" text=" Search" color="lightgreen" role="link"></Button></div>
    </div>
  </div>
</div>
</template>

<script>


import Button from '../components/Button'
export default {
  name: 'OS',
  components: {
    Button
  },
  data() {
    return {
      owner: {city: ''
              
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

.row2 {
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



