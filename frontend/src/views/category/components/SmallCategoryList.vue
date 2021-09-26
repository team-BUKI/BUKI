<template>
  <div class="category-list">
    <div
      v-for="(item, index) in smallcategoryList"
      :key="index"
      :class="{ selected: item.id == smallcategoryId }"
    >
      <span class="text-button title-5" @click="clickButton(item.id)">{{
        item.name
      }}</span>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "SmallCategoryList",
  components: {},
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
    ...mapActions("classStore", ["searchClassByCategory"]),
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
    // 세부 카테고리로 클래스 검색
    clickButton(clickId) {
      // 카테고리 검색 결과 바꾸기
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: clickId == 0 ? undefined : clickId,
          sido: this.$route.query.sido,
          sigungu: this.$route.query.sigungu,
          minPrice: this.$route.query.minPrice,
          maxPrice: this.$route.query.maxPrice,
        },
      });
      this.$router.go();
    },
  },
};
</script>

<style scoped src="./SmallCategoryList.css"></style>
