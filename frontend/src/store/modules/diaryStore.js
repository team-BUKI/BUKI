import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const diaryStore = {
  namespaced: true,
  state: {
    diaryList: [],
  },
  getters: {
    diaryList(state) {
      return state.diaryList;
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
    async getDailyDiary({ rootGetters, getters, commit }, data) {
      await axios
        .get(
          SERVER.URL +
            SERVER.ROUTES.getDailyDiary +
            data.userId +
            "/" +
            data.date,
          { headers: rootGetters.authorization }
        )
        .then((res) => {
          commit("SET_DIARY_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 새로운 일기 등록하기
    async writeDiary({ rootGetters }, data) {
      await axios
        .post(SERVER.URL + SERVER.ROUTES.writeDiary, data, {
          headers: rootGetters.authorization,
        })
        .then((res) => {
          Swal.fire({
            text: "일기가 등록 되었습니다",
            showConfirmButton: false,
            timer: 1000,
          }).then(() => {
            router.push({ name: "Diary" });
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 일기 수정하기
    async updateDiary({ rootGetters }, data) {
      await axios
        .put(SERVER.URL + SERVER.ROUTES.updateDiary, data, {
          headers: rootGetters.authorization,
        })
        .then((res) => {
          Swal.fire({
            text: "일기가 수정 되었습니다",
            showConfirmButton: false,
            timer: 1000,
          }).then(() => {
            router.push({ name: "Diary" });
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 일기 삭제하기
    async deleteDiary({ rootGetters }, diaryId) {
      await axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteDiary + diaryId, {
          headers: rootGetters.authorization,
        })
        .then((res) => {
          Swal.fire({
            text: "일기가 삭제 되었습니다",
            showConfirmButton: false,
            timer: 1000,
          }).then(() => {
            router.go(); // 화면 새로고침
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default diaryStore;
