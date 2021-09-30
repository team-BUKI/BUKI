<template>
  <div class="interest-container">
    <div class="my-title title-3">관심 카테고리 등록</div>
    <i @click="clickCloseButton" class="fas fa-times close-icon"></i>
    <div class="interest-wrapper">
      <interest-category-row
        v-for="category in this.bigcategory"
        :key="category.name"
        :title="category.name"
        :smallcategoryList="category.smallcategoryList"
      ></interest-category-row>
    </div>
    <div class="result-area">
      <CategoryWithX
        v-for="category in this.interestCategory"
        :key="category"
        :name="getSmallcategoryName(category)"
        :idx="category"
      ></CategoryWithX>
    </div>
  </div>
</template>

<script>
import InterestCategoryRow from "./InterestCategoryRow.vue";
import CategoryWithX from "./CategoryWithX.vue";
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  name: "InterestCategory",
  components: {
    InterestCategoryRow,
    CategoryWithX,
  },
  // props
  props: {},
  // data
  data() {
    return {};
  },

  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "smallcategory"]),
    ...mapGetters("accountStore", ["interestCategory"]),
  },
  // lifecycle hook
  mounted() {
    this.getInterestCategory();
  },
  // methods
  methods: {
    ...mapActions("accountStore", ["getInterestCategory", "setInterestCategory"]),
    clickCloseButton() {
      this.setInterestCategory();
      this.$emit("closeInterestCategory");
    },
    getSmallcategoryName(num) {
      if (this.smallcategory != null) return this.smallcategory[num];
      return "";
    },
  },
};
</script>

<style scoped src="./InterestCategory.css"></style>
