import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import GAuth from 'vue-google-oauth2'

const gauthOption = {
  clientId: '805536219743-o3lrc58gsei4vh4e8fvb2c2rdf4l4c11.apps.googleusercontent.com',
  scope: "email profile",
  prompt: "select_account",
}

Vue.use(GAuth, gauthOption);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
