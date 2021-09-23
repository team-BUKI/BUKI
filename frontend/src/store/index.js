import Vue from "vue";
import Vuex from "vuex";


Vue.use(Vuex);

import accountStore from "@/store/modules/accountStore";
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
