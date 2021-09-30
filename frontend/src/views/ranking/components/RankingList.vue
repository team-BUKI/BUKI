<template>
  <div>
    <div class="left-list">
      <ranking-item
        v-for="(rankingItem, index) in list"
        :key="index"
        :idx="index"
        :item="rankingItem"
        @click="$emit('open', rankingItem)"
      />
    </div>
  </div>
</template>

<script>
import RankingItem from "./RankingItem.vue";
export default {
  name: "RankingList",
  components: {
    RankingItem,
  },
  // props
  props: {
    list: Array,
    open: Function,
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

<style scoped src="./RankingList.css"></style>
