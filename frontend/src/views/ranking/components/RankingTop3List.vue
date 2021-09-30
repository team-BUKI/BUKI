<template>
  <div>
    <div class="top3-list">
      <ranking-top3-item
        :idx="1"
        :item="list[1]"
        @open="$emit('open', list[1])"
      />
      <ranking-top3-item
        :idx="0"
        :item="list[0]"
        @open="$emit('open', list[0])"
      />
      <ranking-top3-item
        :idx="2"
        :item="list[2]"
        @open="$emit('open', list[2])"
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
