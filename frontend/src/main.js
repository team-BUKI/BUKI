import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import GAuth from "vue-google-oauth2";

//google login
const gauthOption = {
  clientId:
    "805536219743-o3lrc58gsei4vh4e8fvb2c2rdf4l4c11.apps.googleusercontent.com",
  scope: "email profile",
  prompt: "select_account",
};

Vue.use(GAuth, gauthOption);

//kakao login
window.Kakao.init("d90e243cba3532f958fc2750f411b28b");

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
