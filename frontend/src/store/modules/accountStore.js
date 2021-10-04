import Vue from "vue";
import axios from "axios";
import SERVER from "@/api/api";
import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.$cookies.config("1d");
const accountStore = {
  namespaced: true,
  state: {
    socialType: "",
    nickname: localStorage.getItem("nickname"),
    email: "",
    id: "",
    interestCategory: [],
    interestLocation: [],
    secondNicknameAdjList: [
      "귀여운",
      "꾸준한",
      "여유로운",
      "낭만적인",
      "다정한",
      "다재다능한",
      "부지런한",
      "싱그러운",
      "섬세한",
      "사랑스러운",
      "우아한",
      "아름다운",
      "적극적인",
      "즐거운",
      "화사한",
      "슬기로운",
      "행복한",
      "훈훈한",
      "끈기있는",
      "발랄한",
      "쿨한",
      "맑은",
      "똘똘한",
      "뛰어난",
      "멋있는",
      "명랑한",
      "쾌활한",
      "눈에띄는",
      "열정적인",
      "매력적인",
      "평화로운",
    ],
    secondNicknameAdj: "",
  },
  getters: {
    getSocialType(state) {
      return state.socialType;
    },
    getNickname(state) {
      state.nickname = Vue.$cookies.get("nickname");
      return state.nickname;
    },
    getEmail(state) {
      state.email = Vue.$cookies.get("email");
      return state.email;
    },
    getInterestCategoryLength(state) {
      return state.interestCategory.length;
    },
    getInterestLocationLength(state) {
      return state.interestLocation.length;
    },
    interestCategory(state) {
      return state.interestCategory;
    },
    interestLocation(state) {
      return state.interestCategory;
    },
    secondNicknameAdj(state) {
      return state.secondNicknameAdj;
    },
  },
  mutations: {
    SET_SOCIAL_TYPE(state, data) {
      state.socialType = data;
    },
    SET_NICKNAME(state, data) {
      Vue.$cookies.set("nickname", data);
      state.nickname = data;
    },
    SET_EMAIL(state, data) {
      Vue.$cookies.set("email", data);
      state.email = data;
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
    GET_INTEREST_CATEGORY(state, data) {
      state.interestCategory = [];
      for (let i = 0; i < data.length; i++) {
        state.interestCategory.push(data[i].id);
      }
    },
    GET_INTEREST_REGION(state, data) {
      state.interestLocation = [];
      for (let i = 0; i < data.length; i++) {
        state.interestLocation.push(data[i].sigunguId);
      }
    },
    SET_SECOND_NICKNAME_ADJ(state, data) {
      state.secondNicknameAdj = data;
    },
  },
  actions: {
    // 유저 정보 삭제
    removeUserInfo({ dispatch, commit }) {
      localStorage.clear();
      dispatch("setEmail", "");
      dispatch("setId", "");
      dispatch("setUserId", "", { root: true });
      dispatch("setToken", "", { root: true });
      dispatch("setSocialType", "");
      dispatch("setNickname", "");
      commit("GET_INTEREST_CATEGORY", []);
      commit("GET_INTEREST_REGION", []);
    },
    setEmail({ commit }, data) {
      commit("SET_EMAIL", data);
    },
    setSocialType({ commit }, data) {
      commit("SET_SOCIAL_TYPE", data);
    },
    setNickname({ commit }, data) {
      localStorage.setItem("nickname", data);
      commit("SET_NICKNAME", data);
    },
    removeNickname({ commit }) {
      commit("REMOVE_NICKNAME");
    },
    // 관심카테고리 추가
    addInterestCategory({ commit }, data) {
      commit("ADD_INTEREST_CATEGORY", data);
    },
    //관심카테고리 삭제
    removeInterestCategory({ commit }, data) {
      commit("REMOVE_INTEREST_CATEGORY", data);
    },
    // 관심지역 추가
    addInterestLocation({ commit }, data) {
      commit("ADD_INTEREST_LOCATION", data);
    },
    //관심지역 삭제
    removeInterestLocation({ commit }, data) {
      commit("REMOVE_INTEREST_LOCATION", data);
    },
    // 로그인 정보
    dispatchLoginInfo({ dispatch, commit }, data) {
      dispatch("setId", data.id);
      dispatch("setUserId", data.id, { root: true });
      dispatch("setEmail", data.email);
      dispatch("setToken", data.token, { root: true });
      dispatch("setSocialType", data.socialType);
      dispatch("setNickname", data.nickname);
    },
    // 회원 등록하기
    async registerUserInfo({ state, rootGetters, dispatch }, payload) {
      // 관심지역, 관심카테고리 등록
      if (rootGetters.token != "") {
        dispatch("updateNickname", payload);
        dispatch("setInterestCategory");
        dispatch("setInterestRegion");
      }
    },
    // 관심 지역 등록
    async setInterestRegion({ rootGetters, state }) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.setInterestRegion, state.interestLocation, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {})
        .catch((err) => {
          console.log(err);
        });
    },
    // 관심 카테고리 등록
    async setInterestCategory({ rootGetters, state }) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.setInterestCategory, state.interestCategory, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {})
        .catch((err) => {
          console.log(err);
        });
    },
    //닉네임 업데이트
    async updateNickname({ rootGetters, dispatch }, payload) {
      await axios
        .put(SERVER.URL + SERVER.ROUTES.updateNickname + payload, null, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          dispatch("setNickname", payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 관심 카테고리 가져오기
    async getInterestCategory({ rootGetters, commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getInterestCategory, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          commit("GET_INTEREST_CATEGORY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 관심 지역 가져오기
    async getInterestLocation({ rootGetters, commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getInterestRegion, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          commit("GET_INTEREST_REGION", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 대표 별칭 형용사 가져오기
    async getSecondNicknameAdj({ rootGetters, commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getSecondNicknameAdj, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          commit("SET_SECOND_NICKNAME_ADJ", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 대표 별칭 형용사 설정하기
    async setSecondNicknameAdj({ rootGetters, commit }, payload) {
      await axios
        .put(SERVER.URL + SERVER.ROUTES.updateSecondNicknameAdj + payload, null, {
          headers: rootGetters.authorization,
        })
        .then(() => {
          commit("SET_SECOND_NICKNAME_ADJ", payload);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 회원 탈퇴
    async signout({ rootGetters, dispatch }) {
      await axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteUser, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          dispatch("removeUserInfo");
        })
        .then((error) => {
          console.log(error);
        });
    },
  },
};
export default accountStore;
