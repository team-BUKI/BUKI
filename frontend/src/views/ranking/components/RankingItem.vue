<template>
  <div
    class="left-item"
    @click="$emit('open', item)"
    :class="{ myRanking: item.id == userId }"
  >
    <span class="sub-title title-4 ranking-idx">{{ idx + 4 }}</span>
    <div class="left-img" :class="'category-' + item.bigcategoryId">
      <img
        v-if="item.imageUrl"
        :src="item.imageUrl"
        alt="Main Character Image"
      />
    </div>
    <div class="ranking-info">
      <div class="rankig-name">
        <span class="sub-title title-7 text-ellipsis">
          {{ item.secondcharacterNicknameAdj }}
          {{ item.secondcharacterNicknameNoun }}
        </span>
        <span
          class="title title-5 text-ellipsis"
          style="color: var(--main-lime)"
        >
          {{ item.username }}
        </span>
      </div>
      <div class="ranking-score">
        <img src="@/assets/images/diamond.png" />
        <span class="title-6">{{ item.totalExp }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "RankingItem",
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

<style scoped src="./RankingItem.css"></style>
