<template>
  <div class="mbti-category title-6" :style="getColor()">
    {{ this.name }}
    <div class="icon-wrapper small" @click="removeCategoryTag">
      <i class="fas fa-times"></i>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "MbtiCategoryTag",
  props: {
    idx: Number,
    name: String,
  },
  computed: {
    ...mapState("classStore", ["bigcategory"]),
  },

  methods: {
    ...mapActions("accountStore", [
      "addInterestCategory",
      "removeInterestCategory",
    ]),
    getColor() {
      if (this.bigcategory != null) {
        for (let i = 1; i < this.bigcategory.length; i++) {
          if (this.bigcategory[i].smallcategoryList.includes(this.idx)) {
            return "background-color:var(--category-" + i + ")";
          }
        }
      }
      return "background-color:var(--category-9)";
    },
    removeCategoryTag() {
      this.removeInterestCategory(this.idx);
    },
  },
};
</script>
<style>
.mbti-category {
  position: static;
  display: flex;
  flex-direction: row;
  gap: 5px;
  padding: 0 6px 0 8px;
  height: 28px;
  line-height: 28px;

  color: var(--text-black);
  text-align: center;
  box-shadow: 2px 2px 0px #000000;
  border-radius: 15px;
  margin-right: 6px;
  align-items: center;
  margin-bottom: 5px;
}
.mbti-category .icon-wrapper.small {
  width: 12px;
  height: 12px;
}
.mbti-category .icon-wrapper.small > i {
  font-size: 11px;
  color: var(--button-purple);
}
.mbti-category .icon-wrapper.small:hover > i {
  color: var(--text-black);
}
</style>
