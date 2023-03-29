<template>
  <div>
    <input type="text" v-model="query" placeholder="Search for a city" @input="search">
    <ul>
      <li v-for="(city, index) in cities" :key="index">{{ city.name }}</li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import axios from 'axios';

interface City {
  name: string;
  population: number;
}

@Component
export default class CitySearchBar extends Vue {
  query = '';
  cities: City[] = [];

  async search() {
    const response = await axios.get('https://api.openweathermap.org/data/2.5/find', {
      params: {
        q: this.query,
        type: 'like',
        sort: 'population',
        cnt: 10,
        appid: '<your-api-key>',
      },
    });

    this.cities = response.data.list.map((city: any) => ({
      name: city.name,
      population: city.population,
    }));
  }
}
</script>
