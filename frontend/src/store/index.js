import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import classStore from "@/store/modules/classStore";

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    classStore,
  },
});
