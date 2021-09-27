import SERVER from "@/api/api";
import axios from "axios";

const diaryStore = {
  namespaced: true,
  state: {
    diaryList: [],
    userId: 7,
  },
  getters: {
    diaryList(state) {
      return state.diaryList;
    },
    userId(state) {
      return state.userId;
    },
  },
  mutations: {
    SET_DIARY_LIST(state, data) {
      state.diaryList = data;
    },
  },
  actions: {
    // setters
    setDiaryList({ commit }, data) {
      commit("SET_DIARY_LIST", data);
    },
    // 일기 목록 불러오기 (전체)
    async getAllDiary({ rootGetters, getters, commit }, data) {
      await axios
        .get(
          SERVER.URL + SERVER.ROUTES.getAllDiary + data.id + "/" + data.userId,
          { headers: rootGetters.authorization }
        )
        .then((res) => {
          if (res.data.length == 0) {
            data.state.complete();
          } else {
            setTimeout(() => {
              let arr = getters.diaryList;
              arr = arr.concat(res.data);
              commit("SET_DIARY_LIST", arr);
              data.state.loaded();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 일기 목록 불러오기 (먼슬리)
    async getMonthlyDiary() {},
    // 일기 목록 불러오기 (데일리)
    async getDailyDiary() {},
  },
};

export default diaryStore;
