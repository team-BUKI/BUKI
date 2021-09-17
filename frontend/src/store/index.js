import Vue from "vue";
import Vuex from "vuex";

import accountStore from "./modules/account/accountStore";

Vue.use(Vuex);

import classStore from "@/store/modules/classStore";

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    classStore,
    accountStore
  },
});
