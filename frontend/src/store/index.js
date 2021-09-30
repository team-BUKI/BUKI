import Vue from 'vue';
import Vuex from 'vuex';
import accountStore from '@/store/modules/accountStore';
import classStore from '@/store/modules/classStore';
import characterStore from '@/store/modules/characterStore';
import diaryStore from '@/store/modules/diaryStore';
import mbtiStore from '@/store/modules/mbtiStore';
import rankingStore from '@/store/modules/rankingStore';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		token: localStorage.getItem('token'),
		userId: 0,
	},
	getters: {
		token(state) {
			return state.token;
		},
		authorization(state) {
			return { Authorization: 'Bearer ' + state.token };
		},
		userId(state) {
			return state.userId;
		},
	},
	mutations: {
		SET_TOKEN(state, data) {
			state.token = data;
		},
		SET_USER_ID(state, data) {
			state.userId = data;
		},
	},
	actions: {
		setToken({ commit }, data) {
			localStorage.setItem('token', data);
			commit('SET_TOKEN', data);
		},
		setUserId({ commit }, data) {
			commit('SET_USER_ID', data);
		},
	},
	modules: {
		accountStore,
		classStore,
		characterStore,
		diaryStore,
		mbtiStore,
		rankingStore,
	},
	plugins: [createPersistedState()],
});
