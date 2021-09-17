<template>
  <div class="class-list">
    <class-item
      v-for="(classItem, index) in classList"
      :key="index"
      :item="classItem"
      @openModal="openModal"
    />
  </div>
</template>

<script>
import ClassItem from "./ClassItem.vue";

export default {
  name: "ClassList",
  components: {
    ClassItem,
  },
  // props
  props: {
    classList: Array,
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
    // 클래스 정보 모달 띄우기
    openModal(classItem) {
      this.$emit("openModal", classItem);
    },
  },
};
</script>

<style scoped src="./ClassList.css"></style>
