import SERVER from '@/api/api';
import axios from 'axios';
import router from '@/router';

const rankingStore = {
	namespaced: true,
	state: {
		rankingList: [],
	},
	getters: {
		getRankingList(state) {
			return state.rankingList;
		},
	},
	mutations: {
		SET_RANKING_LIST(state, data) {
			state.rankingList = data;
		},
	},
	actions: {
		async getRankingData({ rootGetters, commit }) {
			await axios
				.get(SERVER.URL + SERVER.ROUTES.getRankingList, {
					headers: rootGetters.authorization,
				})
				.then((res) => {
					console.log(res);
					if (res.data.length <= 0) {
						console.log('empty');
					} else {
						commit('SET_RANKING_LIST', res.data);
					}
				})
				.catch((err) => {
					console.log(err);
				});
		},
	},
};

export default rankingStore;
