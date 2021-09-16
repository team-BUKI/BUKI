import Vue from "vue";
import Vuex from "vuex";

import accountStore from "./modules/account/accountStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    account: accountStore
  },
});
