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
    getInterestCategoryLength(state) {
      return state.interestCategory.length;
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
    REMOVE_EMAIL(state) {
      state.email = "";
    },
    REMOVE_ID(state) {
      state.id = "";
    },
    REMOVE_TOKEN(state) {
      state.token = "";
      localStorage.removeItem("token");
    },
    REMOVE_SOCIAL_TYPE(state) {
      state.socialType = "";
    },
    REMOVE_NICKNAME(state) {
      state.nickname = "";
    },
    ADD_INTEREST_CATEGORY(state, data) {
      state.interestCategory.push(data);
    },
    REMOVE_INTEREST_CATEGORY(state, data) {
      const idx = state.interestCategory.indexOf(data);
      if (idx > -1) state.interestCategory.splice(idx, 1);
    },
  },
  actions: {
    removeUserInfo({ dispatch }) {
      //delete user ∞Ë¡§
      dispatch("removeEmail");
      dispatch("removeId");
      dispatch("removeToken");
      dispatch("removeSocialType");
      dispatch("removeNickname");
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
    removeEmail({ commit }) {
      commit("REMOVE_EMAIL");
    },
    removeId({ commit }) {
      commit("REMOVE_ID");
    },
    removeToken({ commit }) {
      commit("REMOVE_TOKEN");
    },
    removeSocialType({ commit }) {
      commit("REMOVE_SOCIAL_TYPE");
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
  },
};
export default accountStore;
