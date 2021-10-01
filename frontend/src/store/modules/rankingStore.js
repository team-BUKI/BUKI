import SERVER from "@/api/api";
import axios from "axios";

const rankingStore = {
  namespaced: true,
  state: {
    rankingList: [],
  },
  getters: {
    getRankingList(state) {
      return state.rankingList;
    },
  },
  mutations: {
    SET_RANKING_LIST(state, data) {
      state.rankingList = data;
    },
  },
  actions: {
    async getRankingData({ commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getRankingList)
        .then((res) => {
          commit("SET_RANKING_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default rankingStore;
