<template>
  <div class="interest-container">
    <div class="top-title title-3">나의 관심 카테고리 수정</div>
    <i @click="clickCloseButton" class="fas fa-times close-icon"></i>
    <div class="interest-wrapper">
      <div class="recommended-section">
        <div class="title title-4">추천된 카테고리</div>
        <div class="recommend-tag-wrapper">
          <mbti-interest-tag
            v-for="item in recommendCategory"
            :key="item.bigcategoryId"
            :smallcategoryId="item.smallcategoryId"
          ></mbti-interest-tag>
        </div>
      </div>
      <div class="got-category-section">
        <div class="title title-4">나의 관심 카테고리</div>
        <div v-if="interestCategory.length == 0">
          <span class="title-5 middle-title">관심 카테고리가 없습니다.</span>
        </div>
        <div v-else-if="this.interestCategory.length > 0" class="tag-wrapper">
          <mbti-category-tag
            v-for="item in interestCategory"
            :key="item"
            :name="getSmallcategoryName(item)"
            :idx="item"
          ></mbti-category-tag>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MbtiInterestTag from "./MbtiInterestTag.vue";
import MbtiCategoryTag from "./MbtiCategoryTag.vue";
import Swal from "sweetalert2";

import { mapActions, mapState } from "vuex";
export default {
  name: "MbtiInterestCategory",
  components: {
    MbtiInterestTag,
    MbtiCategoryTag,
  },
  props: {
    recommendCategory: Array,
  },
  mounted() {
    this.getInterestCategory();
  },

  computed: {
    ...mapState("accountStore", ["interestCategory"]),
    ...mapState("classStore", ["smallcategory"]),
  },
  methods: {
    ...mapActions("accountStore", ["getInterestCategory", "setInterestCategory"]),
    clickCloseButton() {
      this.setInterestCategory();
      Swal.fire({
        text: "관심 카테고리가 수정되었습니다.",
        showConfirmButton: false,
        timer: 1000,
      });
      setTimeout(() => {
        this.$emit("clickCloseButton");
      }, 1000);
    },
    getSmallcategoryName(num) {
      if (this.smallcategory != null) return this.smallcategory[num];
      return "";
    },
  },
};
</script>
<style scoped src="./MbtiInterestCategory.css"></style>
