<template>
  <div class="diary-list">
    <diary-item
      v-for="(diaryItem, index) in diaryList"
      :key="index"
      :item="diaryItem"
    />
  </div>
</template>

<script>
import DiaryItem from "./DiaryItem.vue";

export default {
  name: "DiaryList",
  components: {
    DiaryItem,
  },
  // props
  props: {
    diaryList: Array,
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

<style scoped src="./DiaryList.css"></style>
