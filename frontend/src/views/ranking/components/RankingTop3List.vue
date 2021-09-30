<template>
  <div>
    <div class="top3-list">
      <ranking-top3-item
        :idx="1"
        :item="list[1]"
        @click="$emit('open', rankingItem)"
      />
      <ranking-top3-item
        :idx="0"
        :item="list[0]"
        @click="$emit('open', rankingItem)"
      />
      <ranking-top3-item
        :idx="2"
        :item="list[2]"
        @click="$emit('open', rankingItem)"
      />
    </div>
  </div>
</template>

<script>
import RankingTop3Item from "./RankingTop3Item.vue";

export default {
  name: "RankingTop3List",
  components: {
    RankingTop3Item,
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

<style scoped src="./RankingTop3List.css"></style>
