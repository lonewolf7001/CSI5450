<template>
  <h3>HouseSearch</h3>
  <router-link to="/">Go Back</router-link>
  <p>City : {{ message }}</p>
  <input v-model="message" placeholder="edit me" />
</template>

<script>
export default {
  name: 'HouseSearch',
  inheritAttrs: false, // disable 'non-props' warning
};
</script>