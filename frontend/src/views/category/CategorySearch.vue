<template>
  <div>
    <div class="container">
      <div class="contents">
        <div class="title-div" @click="openModal">
          <span class="title title-3">{{ bigcategoryName }}</span>
          <div class="icon-wrapper">
            <i class="fas fa-caret-down"></i>
          </div>
        </div>
        <small-category-list
          :bigcategoryId="bigcategoryId"
          :smallcategoryId="smallcategoryId"
        />
        <div class="button-div">
          <div
            class="button-3 title-7"
            :class="{ active: this.sigunguId }"
            @click="clickRegion"
          >
            지역
          </div>
          <div
            class="button-3 title-7"
            :class="{ active: this.minPrice && this.maxPrice }"
            @click="clickPrice"
          >
            가격
          </div>
        </div>
        <class-list :classList="searchClassList" />
      </div>
      <my-footer :selected="'category'" />
    </div>
    <category-modal v-if="isOpen" @closeModal="closeModal" />
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "@/views/common/components/ClassList.vue";
import SmallCategoryList from "./components/SmallCategoryList.vue";
import CategoryModal from "./components/CategoryModal.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "CategorySearch",
  components: {
    MyFooter,
    ClassList,
    SmallCategoryList,
    CategoryModal,
  },
  // props
  props: {},
  // data
  data() {
    return {
      bigcategoryId: this.$route.query.bigcategory * 1,
      smallcategoryId: this.$route.query.smallcategory * 1,
      sigunguId: this.$route.query.sigungu * 1,
      minPrice: this.$route.query.minPrice * 10000,
      maxPrice: this.$route.query.maxPrice * 10000,
      filter: "all",
      isOpen: false,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "searchClassList"]),
    // 카테고리 대분류 이름
    bigcategoryName: function () {
      return this.bigcategory[this.bigcategoryId].name;
    },
  },
  // lifecycle hook
  mounted() {
    this.searchCategory();
  },
  updated() {},
  // methods
  methods: {
    ...mapActions("classStore", ["searchClassByCategory"]),
    // 카테고리 모달 열기
    openModal() {
      this.isOpen = true;
    },
    // 카테고리 모달 닫기
    closeModal() {
      this.isOpen = false;
    },
    // 카테고리로 클래스 검색
    searchCategory() {
      let query = "?bigcategoryId=" + this.bigcategoryId;
      if (this.smallcategoryId > 0) {
        query += "&smallcategoryId=" + this.smallcategoryId;
      }
      if (this.sigunguId) {
        query += "&sigunguId=" + this.sigunguId;
      }
      if (this.minPrice) {
        query += "&minPrice=" + this.minPrice;
      }
      if (this.maxPrice) {
        query += "&maxPrice=" + this.maxPrice;
      }
      let data = {
        id: 0,
        query: query,
      };
      this.searchClassByCategory(data);
    },
    // 지역 필터 적용
    clickRegion() {},
    // 가격 필터 적용
    clickPrice() {},
  },
};
</script>

<style scoped src="./CategorySearch.css"></style>
