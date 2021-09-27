const accountStore = {
  namespaced: true,
  state: {
    socialType: "",
    nickname: "",
    email: "",
    id: "",
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
  },
  actions: {
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
  },
};
export default accountStore;
