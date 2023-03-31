<template>
    <div>
      <h3> Home ID {{ data.homeId}}</h3>
      <p> <b>Address : </b></p>
      <p>{{ address.houseNum }} , {{ address.street }} , {{ address.aptNum }}</p>
      <p>{{ address.city }} , <b>COUNTY : </b>{{ address.county }} </p>
      <p>ZIP : {{ address.zip }}</p>
      <p> <b>Details : </b></p>
      <p>YearBuilt      : {{ data.yearBuilt }}</p>
      <p>Floor Space    : {{ data.floorSpace }}, No. of Floors  : {{ data.numFloors }}</p>
      <p>Full bathrooms : {{ data.fullBaths }}, Half bathrooms : {{ data.halfBaths }}</p>
      <p>Land size      : {{ data.landSize }}, Home Type      : {{ data.homeType }}</p>
      <p>Is for sale    : {{ data.isForSale }}</p>
    </div>
  </template>
  
  <script>
  import HomeService from '@/services/HomeService';
  export default {
    props: {
      data: {
        type: Object,
        required: true,
      },
    },
    data(){
        return{
        address : []
    };
},
    created(){
        HomeService.getAddressofhome(this.data.homeId).then((response) => {
        this.address = response.data;
        console.log(this.address)
    });
  }
}
  </script>
  