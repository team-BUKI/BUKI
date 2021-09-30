<template>
	<div>
		<div class="left-item">
			<span class="title title-4 ranking-idx">{{ idx + 4 }}</span>
			<div class="left-img" :class="'category-' + item.bigcategoryId">
				<img
					v-if="item.imageUrl"
					:src="item.imageUrl"
					alt="Main Character Image"
				/>
			</div>
			<div class="ranking-info">
				<div class="rankig-name">
					<span class="title title-7">
						{{ item.secondcharacterNicknameAdj }}
						{{ item.secondcharacterNicknameNoun }}
					</span>
					<span class="title title-5" style="color: var(--main-lime)">
						{{ item.username }}
					</span>
				</div>
				<div class="ranking-score">
					<span class="title title-6">{{ item.totalExp }}</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'RankingItem',
	components: {},
	// props
	props: {
		item: Object,
		idx: Number,
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
	methods: {},
};
</script>

<style scoped src="./RankingItem.css"></style>
