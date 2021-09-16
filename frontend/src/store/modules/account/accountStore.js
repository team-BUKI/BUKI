const accountStore = {
    namespaced: true,
    state: {
        socialType: "",
        token: "",
        nickname: "",
        id: ""
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
    mutation: {
        SET_ID(state, email) {
            state.id = email;
        },
        SET_TOKEN(state, token) {
            state.token = token;
        },
        SET_SOCIAL_TYPE(state, socialType) {
            state.socialType = socialType;
        },
        SET_NICKNAME(state, nickname) {
            state.nickname = nickname;
        }
    },
    actions: {
        setId({ commit }, userEmail) {
            commit("SET_ID", userEmail);
        },
        setToken({ commit }) {
            commit("SET_TOKEN", localStorage.getItem('token'));
        },
        setSocialType({ commit }, socialType) {
            commit("SET_SOCIAL_TYPE", socialType);
        },
        setNickname({ commit }, nickname) {
            commit("SET_NICKNAME", nickname);
        },
    }
}
