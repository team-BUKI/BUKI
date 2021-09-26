<template>
  <div class="interest-container">
    <div class="my-title title-3">관심 카테고리 등록</div>
    <img @click="clickCloseButton" src="@/assets/images/close.png" class="close-icon" />
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
import { mapState, mapActions } from "vuex";

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
    return {
      categoryList: [],
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "smallcategory"]),
    ...mapState("accountStore", ["interestCategory"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    clickCloseButton() {
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
