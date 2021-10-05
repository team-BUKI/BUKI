<template>
  <span class="recommended-category" @click="clickRecommendTag()">
    <span class="title-5 black-title">{{ getSmallcategoryName }}</span>
  </span>
</template>
<script>
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";
export default {
  name: "MbtiInterestTag",
  props: {
    smallcategoryId: Number,
  },
  computed: {
    ...mapState("classStore", ["smallcategory"]),
    ...mapState("accountStore", ["interestCategory"]),
    getSmallcategoryName() {
      if (this.smallcategoryId != null)
        return this.smallcategory[this.smallcategoryId];
      return "";
    },
  },
  methods: {
    ...mapActions("accountStore", ["addInterestCategory"]),
    clickRecommendTag() {
      if (this.interestCategory.includes(this.smallcategoryId)) {
        Swal.fire({
          text: "이미 등록된 카테고리입니다",
          showConfirmButton: false,
          timer: 1000,
        });
        return;
      } else {
        if (this.interestCategory.length == 6) {
          Swal.fire({
            text: "관심 카테고리는 6개까지 등록 가능합니다.",
            showConfirmButton: false,
            timer: 1000,
          });
          return;
        } else {
          this.addInterestCategory(this.smallcategoryId);
        }
      }
    },
  },
};
</script>
<style>
.recommended-category {
  position: static;
  display: inline-block;
  height: 30px;

  border-radius: 15px;
  background-color: var(--text-violet);
  padding: 0 10px;
  transition: background-color 0.2s ease-out;
}
.recommended-category:active {
  background-color: var(--button-violet);
}
.black-title {
  color: black;
}
</style>
