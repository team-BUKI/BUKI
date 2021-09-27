import axios from "axios";
import SERVER from "@/api/api";

const accountStore = {
  namespaced: true,
  state: {
    socialType: "",
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
    SET_SOCIAL_TYPE(state, data) {
      state.socialType = data;
    },
    SET_NICKNAME(state, data) {
      state.nickname = data;
    },
    SET_EMAIL(state, data) {
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
  },
  actions: {
    removeUserInfo({ dispatch, rootGetters }) {
      //delete user
      dispatch("setEmail", "");
      dispatch("setId", "");
      dispatch("setToken", "", { root: true });
      dispatch("setSocialType", "");
      dispatch("setNickname", "");
    },
    setEmail({ commit }, data) {
      commit("SET_EMAIL", data);
    },
    setId({ commit }, data) {
      commit("SET_ID", data);
    },
    setSocialType({ commit }, data) {
      commit("SET_SOCIAL_TYPE", data);
    },
    setNickname({ commit }, data) {
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
    dispatchLoginInfo({ dispatch }, data) {
      dispatch("setId", data.id);
      dispatch("setEmail", data.email);
      dispatch("setToken", data.token, { root: true });
      dispatch("setSocialType", data.socialType);
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
      console.log(state.interestLocation);
      axios
        .post(SERVER.URL + SERVER.ROUTES.setInterestRegion, state.interestLocation, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          console.log(data);
          console.log("region");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 관심 카테고리 등록
    async setInterestCategory({ rootGetters, state }) {
      console.log(state.interestCategory);
      axios
        .post(SERVER.URL + SERVER.ROUTES.setInterestCategory, state.interestCategory, {
          headers: rootGetters.authorization,
        })
        .then(({ data }) => {
          console.log(data);
          console.log("category");
        })
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
          console.log("nickname");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default accountStore;
