<template>
  <div>
    <div class="top3-item" @click="$emit('open', item)">
      <div class="top3-img" :class="'category-' + item.bigcategoryId">
        <img
          v-if="item.imageUrl"
          :src="item.imageUrl"
          alt="Main Character Image"
        />
      </div>
      <span class="title title-4">{{ idx + 1 }}</span>
      <div class="name">
        <p class="title-7 title">
          {{ item.secondcharacterNicknameAdj }}
          {{ item.secondcharacterNicknameNoun }}
        </p>
        <span class="title title-5" style="color: var(--main-lime)">{{
          item.username
        }}</span>
      </div>
      <div>
        <span class="title title6">{{ item.totalExp }}</span>
      </div>
    </div>
  </div>
</template>

<script>
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
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./RankingTop3Item.css"></style>
