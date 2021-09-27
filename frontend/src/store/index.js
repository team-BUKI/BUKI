import Vue from "vue";
import Vuex from "vuex";
import accountStore from "@/store/modules/accountStore";
import classStore from "@/store/modules/classStore";
import characterStore from "@/store/modules/characterStore";
import diaryStore from "@/store/modules/diaryStore";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
  },
  getters: {
    token(state) {
      return state.token;
    },
    config: (state) => ({
      headers: { Authorization: "Bearer " + state.token },
    }),
  },
  mutations: {},
  actions: {},
  modules: {
    accountStore,
    classStore,
    characterStore,
    diaryStore,
  },
  plugins: [createPersistedState()],
});
