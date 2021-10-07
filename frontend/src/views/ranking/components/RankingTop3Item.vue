<template>
  <div
    class="top3-item"
    @click="$emit('open', item)"
    :class="{ myRanking: item.id == userId }"
  >
    <div class="top3-img-wrapper">
      <div class="top3-img" :class="'category-' + item.bigcategoryId">
        <img
          v-if="item.imageUrl"
          :src="item.imageUrl"
          alt="Main Character Image"
        />
      </div>
      <div class="ranking-idx title-4">{{ idx + 1 }}</div>
    </div>
    <div class="top3-name">
      <span class="sub-title title-7 text-ellipsis">
        {{ item.secondcharacterNicknameAdj }}
        {{ item.secondcharacterNicknameNoun }}
      </span>
      <span
        class="title title-5 text-ellipsis"
        style="color: var(--main-lime)"
        >{{ item.username }}</span
      >
    </div>
    <div class="ranking-score">
      <img src="@/assets/images/diamond.png" />
      <span class="title-6">{{ item.totalExp }}</span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "RankingTop3Item",
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
  computed: {
    ...mapState(["userId"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./RankingTop3Item.css"></style>
