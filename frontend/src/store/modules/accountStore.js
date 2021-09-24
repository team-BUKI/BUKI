const accountStore = {
    namespaced: true,
    state: {
        socialType: "",
        token: "",
        nickname: "",
        email: "",
        id: "",
    },
    getters: {
        getId(state){
            return state.id;
        },
        getToken(state){
            return state.token;
        },
        getSocialType(state){
            return state.socialType;
        },
        getNickname(state){
            return state.nickname;
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
        }
    },
    actions: {
        setEmail({ commit }, data) {
            commit("SET_EMAIL", data);
        },
        setId({ commit }, data) {
            commit("SET_ID", data);
        },
        setToken({ commit }) {
            commit("SET_TOKEN", localStorage.getItem('token'));
        },
        setSocialType({ commit }, data) {
            commit("SET_SOCIAL_TYPE", data);
        },
        setNickname({ commit }, data) {
            commit("SET_NICKNAME", data);
        },
    }
};
export default accountStore;
