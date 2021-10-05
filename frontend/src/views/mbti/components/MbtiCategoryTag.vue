<template>
  <span class="category" :style="getColor()" @click="removeCategoryTag">
    <span class="title-6 category-title">{{ this.name }}</span>
    <img src="@/assets/images/xicon.png" />
  </span>
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
    ...mapActions("accountStore", ["addInterestCategory", "removeInterestCategory"]),
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
.category {
  position: static;
  display: flex;
  flex-direction: row;
  gap: 5px;
  padding: 0 5px;
  height: 30px;

  text-align: center;
  box-shadow: 2px 2px 0px #000000;
  border-radius: 15px;
  margin-right: 6px;
  align-items: center;
  margin-bottom: 5px;
}

.category-title {
  color: black;
  line-height: 30px;
  vertical-align: middle;
}
.category > img {
  padding: 4px;

  width: 10px;
  height: 10px;
}
</style>
