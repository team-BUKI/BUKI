<template>
  <div class="top3-list">
    <ranking-top3-item
      v-if="list[1]"
      class="second"
      :idx="1"
      :item="list[1]"
      @open="$emit('open', list[1])"
    />
    <div v-else></div>
    <img class="crown" src="@/assets/images/crown.png" alt="Crown" />
    <ranking-top3-item
      class="first"
      :idx="0"
      :item="list[0]"
      @open="$emit('open', list[0])"
    />
    <ranking-top3-item
      class="third"
      :idx="2"
      :item="list[2]"
      @open="$emit('open', list[2])"
    />
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
