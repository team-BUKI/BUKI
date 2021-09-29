<template>
	<div>
		<RankingItem
			v-for="(rankingItem, index) in list"
			:key="index"
			:item="rankingItem"
			@click="openDiary"
		/>
	</div>
</template>

<script>
import RankingItem from './RankingItem.vue';
export default {
	name: 'RankingList',
	components: {
		RankingItem,
	},
	// props
	props: {
		list: Array,
	},
	// data
	data() {
		return {};
	},
	// computed
	computed: {},
	// lifecycle hook
	mounted() {},
	// methods
	methods: {
		openDiary(rankingItem) {},
	},
};
</script>

<style scoped src="./RankingList.css"></style>
