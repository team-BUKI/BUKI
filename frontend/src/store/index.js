import Vue from "vue";
import Vuex from "vuex";
import accountStore from "@/store/modules/accountStore";
import classStore from "@/store/modules/classStore";
import characterStore from "@/store/modules/characterStore";
import diaryStore from "@/store/modules/diaryStore";
import mbtiStore from "@/store/modules/mbtiStore";
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
    authorization(state) {
      return { Authorization: "Bearer " + state.token };
    },
  },
  mutations: {
    SET_TOKEN(state, data) {
      state.token = data;
    },
  },
  actions: {
    setToken({ commit }, data) {
      localStorage.setItem("token", data);
      commit("SET_TOKEN", data);
    },
  },
  modules: {
    accountStore,
    classStore,
    characterStore,
    diaryStore,
    mbtiStore,
  },
  plugins: [createPersistedState()],
});
