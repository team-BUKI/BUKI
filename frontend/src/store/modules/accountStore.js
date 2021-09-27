import axios from "axios";
import router from "@/router";
import { API_SERVER_URL } from "@/constant/index.js";

const accountStore = {
  namespaced: true,
  state: {
    socialType: "",
    token: "",
    nickname: "",
    email: "",
    id: "",
    interestCategory: [],
    interestLocation: [],
  },
  getters: {
    getId(state) {
      return state.id;
    },
    getToken(state) {
      return state.token;
    },
    getSocialType(state) {
      return state.socialType;
    },
    getNickname(state) {
      return state.nickname;
    },
    getHeader(state) {
      return `{Authorization: Bearer ${state.token}}`;
    },
    getInterestCategoryLength(state) {
      return state.interestCategory.length;
    },
    getInterestLocationLength(state) {
      return state.interestLocation.length;
    },
  },
  mutations: {
    SET_ID(state, data) {
      state.id = data;
    },
    SET_TOKEN(state, data) {
      state.token = data;
      // axios.defaults.headers.common["Authorization"] = `Bearer ${data}`;
    },
    SET_SOCIAL_TYPE(state, data) {
      state.socialType = data;
    },
    SET_NICKNAME(state, data) {
      state.nickname = data;
    },
    SET_EMAIL(state, data) {
      state.email = data;
    },
    REMOVE_TOKEN(state) {
      state.token = "";
      localStorage.removeItem("token");
    },
    ADD_INTEREST_CATEGORY(state, data) {
      state.interestCategory.push(data);
    },
    REMOVE_INTEREST_CATEGORY(state, data) {
      const idx = state.interestCategory.indexOf(data);
      if (idx > -1) state.interestCategory.splice(idx, 1);
    },
    ADD_INTEREST_LOCATION(state, data) {
      state.interestLocation.push(data);
    },
    REMOVE_INTEREST_LOCATION(state, data) {
      const idx = state.interestLocation.indexOf(data);
      if (idx > -1) state.interestLocation.splice(idx, 1);
    },
  },
  actions: {
    removeUserInfo({ dispatch }) {
      //delete user
      dispatch("setEmail", "");
      dispatch("setId", "");
      dispatch("removeToken");
      dispatch("setSocialType", "");
      dispatch("setNickname", "");
    },
    setEmail({ commit }, data) {
      commit("SET_EMAIL", data);
    },
    setId({ commit }, data) {
      commit("SET_ID", data);
    },
    setToken({ commit }) {
      commit("SET_TOKEN", localStorage.getItem("token"));
    },
    setSocialType({ commit }, data) {
      commit("SET_SOCIAL_TYPE", data);
    },
    setNickname({ commit }, data) {
      commit("SET_NICKNAME", data);
    },

    removeToken({ commit }) {
      commit("REMOVE_TOKEN");
    },

    removeNickname({ commit }) {
      commit("REMOVE_NICKNAME");
    },
    addInterestCategory({ commit }, data) {
      commit("ADD_INTEREST_CATEGORY", data);
    },
    removeInterestCategory({ commit }, data) {
      commit("REMOVE_INTEREST_CATEGORY", data);
    },
    addInterestLocation({ commit }, data) {
      commit("ADD_INTEREST_LOCATION", data);
    },
    removeInterestLocation({ commit }, data) {
      commit("REMOVE_INTEREST_LOCATION", data);
    },

    // 회원 등록하기
    async registerUserInfo({ getters, rootGetters, dispatch }, nickname) {
      // 관심지역, 관심카테고리 등록
      if (getters.getToken != "") {
        console.log(getters.getHeader);
        await axios({
          methods: "post",
          headers: { Authorization: `Bearer ${getters.getToken}` },
          url: API_SERVER_URL + "/api/user/info",
          data: {
            region: this.interestLocation,
            category: this.interestCategory,
          },
        })
          .then(({ data }) => {
            console.log(data);
            axios({
              methods: "put",
              headers: { Authorization: `Bearer ${getters.getToken}` },
              url: API_SERVER_URL + "/api/user/nickname/" + nickname,
            })
              .then(({ data }) => {
                console.log("로그인 성공", data);
                dispatch("setNickname", nickname);
                router.push({ name: "MyPage" });
              })
              .catch((err) => {
                console.log(err);
              });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
export default accountStore;
