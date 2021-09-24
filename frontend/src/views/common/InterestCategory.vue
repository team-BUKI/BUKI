<template>
  <div class="interest-container">
    <div class="my-title title-3">관심 카테고리 등록</div>
    <img @click="clickCloseButton" src="@/assets/images/close.png" class="close-icon" />
    <div class="interest-wrapper">
      <div class=""></div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "InterestCategory",
  components: {},
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
    ...mapState("caategoryStore", ["getCategoryList"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    clickCloseButton() {
      this.$emit("closeInterestCategory");
    },
  },
};
</script>

<style scoped src="./InterestCategory.css"></style>
