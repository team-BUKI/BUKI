import Vue from "vue";
import Vuex from "vuex";
import accountStore from "@/store//modules/accountStore";
import classStore from "@/store/modules/classStore";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    accountStore,
    classStore,
  },
  plugins: [createPersistedState()],
});
