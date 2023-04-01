<template>
    <div>
        <div class = "header">
            <h3 style="text-align:left">&nbsp;</h3>
            <h3 style="text-align:left"> Update Home Sale Data &nbsp;</h3>
        </div>
    
        <div class= "container2">
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="HomeId" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.homeId" placeholder="homeID" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="SaleDate" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.saleDate" placeholder="saledate" /></div>
            </div>
            <div class= "row3"> 
                <div class="col1"><Button class="btn1" text="OwnerId" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.ownerId" placeholder="ownerid" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="agentId" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.agentId" placeholder="agentId" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="CompanyId" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.companyId" placeholder="companyid" /></div>
            </div>
            <div class= "row3"> 
            <div class="col1"><Button class="btn1" text="price" color="lightblue" disabled role="link"></Button></div>
            <div class= "col2"> <input v-model="home_sale.price" placeholder="price" /></div>
            </div>

            <div class="row3">
            <div class="col1"></div>
            <div class="col2"><Button class="btn1" text="UpdateHomesale" color="lightgreen" v-on:click=" this.UpdateHomeSellResponse()"></Button></div>
            </div>
        </div>
    </div>
    </template>
    <script>
    import Button from '../components/Button'
    import HomeService from '@/services/HomeService';
    
    export default {
      name: 'HomeSell',
      components: {
        Button
      },
      data() {
        return {
          home_sale: {  homeId: '',
                        saleDate:'',
                        ownerId : null ,
                        agentId :null,
                        companyId: null,
                        price: ''
            }
          }
        },
      methods: {
        UpdateHomeSellResponse(){
          // console.log(home_sale.homeId)
          // console.log( home_sale.saleDate)
          // console.log(home_sale.ownerId)
          // console.log( home_sale.agentId)
          // console.log(home_sale.companyId)
          // console.log( home_sale.price)
          console.log(this.home_sale);
          HomeService.sell(this.home_sale).then(response => {
            console.log(response.data);

            // this.home_sale.homeId = '';
            // this.home_sale.SaleDate = '';
            // this.home_sale.ownerId = '';
            // this.home_sale.agentId = '';
            // this.home_sale.companyId = '';
            // this.home_sale.price = '';
          })
          .catch(error => {
            console.error(error);
          });
        }
      }
    }
    </script>