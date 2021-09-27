import axios from "axios";
import router from "@/router";
import { API_SERVER_URL } from "@/constant/index.js";

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
    setToken({ commit }, data) {
      localStorage.setItem("token", data);
      commit("SET_TOKEN", data);
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
    //구글 로그인
    async googleLogin({ dispatch, rootGetters }) {
      try {
        const googleUser = await this.$gAuth.signIn();
        const profile = googleUser.getBasicProfile();
        const email = profile.getEmail();
        this.email = email;

        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";
        axios({
          method: "post",
          url: API_SERVER_URL + "/api/user/login",
          data: {
            socialType: "GOOGLE",
            email: email,
          },
        })
          .then(({ data }) => {
            console.log(data);
            // id, email, token, socialType 저장
            this.setId(data.id);
            this.setEmail(email);
            this.setToken(data.token);
            this.setSocialType("GOOGLE");
            if (data.first) {
              // 회원가입 페이지로 보내기
              return true;
              // 만약 회원가입 취소하면 localstorage 삭제하고 메인페이지로 보내기
            } else {
              // 로그인, 마이페이지로 보내기
              this.$router.push({ path: "/mypage" });
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } catch (e) {
        console.error(e);
      }
    },

    // 회원 등록하기
    async registerUserInfo({ getters, dispatch }, nickname) {
      // 관심지역, 관심카테고리 등록
      console.log(nickname);
      console.log(getters.getToken);
      if (getters.getToken != "") {
        await axios({
          methods: "post",
          headers: { Authorization: `Bearer ${getters.getToken}` },
          url: API_SERVER_URL + "/api/user/info",
          data: {
            region: this.interestLocation,
            category: this.interestCategory,
            nickname: nickname,
          },
        })
          .then(({ data }) => {
            console.log(data);
            dispatch("setNickname", nickname);
          })
          .catch((err) => {
            console.log(err);
          });
        // let token = getters.getToken;

        // await axios
        //   .put(API_SERVER_URL + "/api/user/nickname/ddd", null, {
        //     headers: { Authorization: "Bearer " + token, "Content-Type": "application/json" },
        //   })
        //   .then(({ data }) => {
        //     console.log(data);
        //   })
        //   .catch((err) => {
        //     console.log(err);
        //   });
      }
    },
    async getInterestCategory({ dispatch, getters }) {
      console.log(API_SERVER_URL + "/api/interest/category");
      await axios({
        methods: "get",
        url: API_SERVER_URL + "/api/interest/category",
        headers: { Authorization: `Bearer ${getters.getToken}` },
      })
        .then(({ data }) => {
          console.log(data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default accountStore;
