<template>
  <div class="interest-category-wrapper" v-if="this.title != ''">
    <div class="title-wrapper">
      <span class="title-5 interest-title">{{ this.title }}</span>
    </div>
    <div class="row-wrapper">
      <div class="row-layout">
        <span class="row-content-wrapper" v-for="category in smallcategoryList" :key="category">
          <interest-category-tag
            :name="getSmallcategoryName(category)"
            :idx="category"
          ></interest-category-tag>
        </span>
      </div>
    </div>
  </div>
</template>
<script>
import InterestCategoryTag from "./InterestCategoryTag.vue";
import { mapState } from "vuex";

export default {
  name: "InterestCategoryRow",
  components: {
    InterestCategoryTag,
  },
  props: {
    title: String,
    smallcategoryList: Array,
  },
  data() {
    return {};
  },

  mounted() {},
  computed: {
    ...mapState("classStore", ["smallcategory"]),
  },
  methods: {
    getSmallcategoryName(num) {
      if (this.smallcategory != null) return this.smallcategory[num];
      return "";
    },
  },
};
</script>
<style scoped src="./InterestCategoryRow.css"></style>
