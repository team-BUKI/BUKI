<template>
  <div class="diary-list">
    <diary-item
      v-for="(diaryItem, index) in diaryList"
      :key="index"
      :item="diaryItem"
      @openModal="openModal"
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
  methods: {
    // 일기 삭제 모달창 열기
    openModal(id) {
      this.$emit("openModal", id);
    },
  },
};
</script>

<style scoped src="./DiaryList.css"></style>
