<template>
  <span
    id="category-tag"
    class="title-5"
    @click="onClick"
    :class="{
      'row-content-selected': this.isClick,
      'row-content': !this.isClick,
    }"
    >{{ this.name }}</span
  >
</template>
<script>
import { mapActions, mapState, mapGetters } from "vuex";
import Swal from "sweetalert2";
export default {
  name: "InterestCategoryTag",
  props: {
    name: String,
    idx: Number,
  },
  data() {
    return {
      isClick: false,
    };
  },
  created() {
    if (this.interestCategory.includes(this.idx)) {
      this.isClick = true;
    }
  },
  computed: {
    ...mapState("accountStore", ["interestCategory"]),
    ...mapGetters("accountStore", ["getInterestCategoryLength"]),
  },
  watch: {
    interestCategory: {
      deep: true,
      handler: function () {
        if (!this.interestCategory.includes(this.idx)) {
          this.isClick = false;
        }
      },
    },
  },
  methods: {
    ...mapActions("accountStore", [
      "addInterestCategory",
      "removeInterestCategory",
    ]),

    onClick() {
      if (!this.isClick) {
        if (this.getInterestCategoryLength >= 6) {
          Swal.fire({
            text: "관심 카테고리는 6개까지 등록 가능합니다.",
            showConfirmButton: false,
            timer: 1000,
          });
          return;
        }
        this.addInterestCategory(this.idx);
        this.isClick = true;
      } else {
        this.removeInterestCategory(this.idx);
        this.isClick = false;
      }
    },
  },
};
</script>
<style>
.row-content {
  display: inline-block;
  line-height: 30px;
  vertical-align: middle;
  padding: 0 5px;
}

.row-content-selected {
  display: inline-block;
  line-height: 30px;
  vertical-align: middle;
  padding: 0 5px;
  background-color: var(--main-lime);
  border-radius: 15px;
  color: black;
}

.category-tag:active {
  background-color: var(--button-green);
}
</style>
