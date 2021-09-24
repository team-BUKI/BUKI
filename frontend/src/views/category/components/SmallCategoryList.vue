<template>
  <div class="category-list">
    <small-category-item
      v-for="(item, index) in smallcategoryList"
      :key="index"
      :item="item"
      :class="{ selected: item.id == smallcategoryId }"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
import SmallCategoryItem from "./SmallCategoryItem.vue";

export default {
  name: "SmallCategoryList",
  components: {
    SmallCategoryItem,
  },
  // props
  props: {
    bigcategoryId: Number,
    smallcategoryId: Number,
  },
  // data
  data() {
    return {
      smallcategoryList: [{ name: "전체", id: 0 }],
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "smallcategory"]),
  },
  // lifecycle hook
  mounted() {
    this.setSmallCategoryList();
  },
  // methods
  methods: {
    // 하위 카테고리 목록 설정
    setSmallCategoryList() {
      let arr = this.bigcategory[this.bigcategoryId].smallcategoryList;
      for (var i = 0; i < arr.length; i++) {
        this.smallcategoryList.push({
          name: this.smallcategory[arr[i]],
          id: arr[i],
        });
      }
    },
  },
};
</script>

<style scoped src="./SmallCategoryList.css"></style>
