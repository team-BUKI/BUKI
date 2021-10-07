<template>
  <div class="modal-container">
    <div class="header-wrapper">
      <span class="title title-3">카테고리</span>
      <div class="icon-wrapper" @click="clickClose">
        <i class="fas fa-times"></i>
      </div>
    </div>
    <div class="contents">
      <category-list :isModal="true" />
    </div>
  </div>
</template>

<script>
import CategoryList from "./CategoryList.vue";

export default {
  name: "CategoryModal",
  components: {
    CategoryList,
  },
  // props
  props: {},
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
    // 카테고리 모달 닫기
    clickClose() {
      this.$emit("closeModal");
    },
  },
};
</script>

<style scoped src="./CategoryModal.css"></style>
