<template>
	<div>
		<p>{{ item.totalExp }}</p>
		<p>{{ item.bigcategoryId }}</p>
		<p>{{ item.image }}</p>
		<p>{{ item.totalExp }}</p>
	</div>
</template>

<script>
export default {
	name: 'RankingItem',
	components: {},
	// props
	props: {
		item: Object,
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
