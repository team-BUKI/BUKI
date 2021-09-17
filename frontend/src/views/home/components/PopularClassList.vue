<template>
  <div>
    <div
      v-for="(classList, index) in popularClassList"
      :key="index"
      :classList="classList"
    >
      <span class="sub-title title-5">{{ bigcategoryName(index) }}</span>
      <class-list :classList="classList" @openModal="openModal" />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ClassList from "./ClassList.vue";

export default {
  name: "PopularClassList",
  components: {
    ClassList,
  },
  // props
  props: {
    popularClassList: Array,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    // 카테고리 대분류 이름을 반환
    bigcategoryName(index) {
      return this.bigcategory[index + 1];
    },
    // 클래스 정보 모달 띄우기
    openModal(classItem) {
      this.$emit("openModal", classItem);
    },
  },
};
</script>

<style scoped src="./PopularClassList.css"></style>
