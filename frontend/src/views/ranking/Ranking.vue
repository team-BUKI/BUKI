<template>
	<div class="container">
		<div class="title title-3">랭킹</div>
		<div class="contents">
			<ranking-top3-list :list="rankingTop3List" />
			<ranking-list :list="rankingLeftList" />
		</div>
		<my-footer :selected="'ranking'" />
	</div>
</template>

<script>
import MyFooter from '@/views/common/MyFooter.vue';
import RankingTop3List from './components/RankingTop3List.vue';
import RankingList from './components/RankingList.vue';
import { mapState, mapActions, mapGetters } from 'vuex';

export default {
	name: 'Ranking',
	components: {
		MyFooter,
		RankingTop3List,
		RankingList,
	},
	// props
	props: {},
	// data
	data() {
		return {
			rankingTop3List: [],
			rankingLeftList: [],
			rankingList: [],
		};
	},
	//created
	created() {
		this.getRankingAllList();
		if (this.rankingList.length > 3) {
			this.rankingTop3List = this.rankingList.slice(0, 3);
			this.rankingLeftList = this.rankingList.slice(3);
		} else {
			this.rankingTop3List = this.rankingList;
		}
	},
	// computed
	computed: {},
	// lifecycle hook
	mounted() {},
	// methods
	methods: {
		...mapGetters('rankingStore', ['getRankingList']),

		getRankingAllList() {
			this.rankingList = this.getRankingList();
		},
	},
};
</script>

<style scoped src="./Ranking.css"></style>
