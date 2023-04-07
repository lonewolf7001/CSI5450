import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
// import Vue from 'vue'
// import Vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.min.css'

// Vue.use(Vuetify)
// const vuetify = new Vuetify()
// new Vue({
//     vuetify,
//     // other Vue options
//   }).$mount('#app')

createApp(App).use(router).mount("#app");