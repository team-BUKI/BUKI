<template>
  <div>
    <div class="container">
      <div class="contents">
        <div class="title-div" @click="openCategoryModal">
          <span class="title title-3">{{ bigcategoryName }}</span>
          <div class="icon-wrapper">
            <i class="fas fa-caret-down"></i>
          </div>
        </div>
        <small-category-list
          :bigcategoryId="bigcategoryId"
          :smallcategoryId="smallcategoryId ? smallcategoryId : 0"
        />
        <div class="button-div">
          <div
            class="button-3 title-7"
            :class="{ active: this.sigunguId }"
            @click="openRegionModal"
          >
            {{ sigunguId ? sigunguName : "지역" }}
            <div
              v-if="sigunguId"
              class="icon-wrapper small"
              @click="turnOffRegionFilter"
            >
              <i class="fas fa-times"></i>
            </div>
          </div>
          <div
            class="button-3 title-7"
            :class="{ active: this.minPrice && this.maxPrice }"
            @click="openPriceModal"
          >
            {{ minPrice && maxPrice ? priceRange : "가격" }}
            <div
              v-if="minPrice && maxPrice"
              class="icon-wrapper small"
              @click="turnOffPriceFilter"
            >
              <i class="fas fa-times"></i>
            </div>
          </div>
        </div>
        <class-list :classList="searchClassList" />
      </div>
      <infinite-loading
        @infinite="searchCategory"
        spinner="waveDots"
        class="infinite-div"
      >
        <div slot="no-more" class="infinite-message title-5">
          더 이상 클래스가 없습니다
        </div>
        <div slot="no-results" class="infinite-message title-5">
          검색된 클래스가 없습니다
        </div>
      </infinite-loading>
      <my-footer :selected="'category'" />
    </div>
    <category-modal v-if="isOpenCategory" @closeModal="closeCategoryModal" />
    <region-modal
      v-if="isOpenRegion"
      :prevSidoId="sidoId ? sidoId : 1"
      :prevSigunguId="sigunguId"
      @closeModal="closeRegionModal"
    />
    <price-modal
      v-if="isOpenPrice"
      :minPrice="minPrice ? minPrice * 1 : 0"
      :maxPrice="maxPrice ? maxPrice * 1 : 30"
      @closeModal="closePriceModal"
    />
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "@/views/common/components/ClassList.vue";
import SmallCategoryList from "./components/SmallCategoryList.vue";
import CategoryModal from "./components/CategoryModal.vue";
import RegionModal from "./components/searchFilter/RegionModal.vue";
import PriceModal from "./components/searchFilter/PriceModal.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "CategorySearch",
  components: {
    MyFooter,
    ClassList,
    SmallCategoryList,
    CategoryModal,
    RegionModal,
    PriceModal,
  },
  // props
  props: {},
  // data
  data() {
    return {
      bigcategoryId: this.$route.query.bigcategory * 1,
      smallcategoryId: this.$route.query.smallcategory * 1,
      sidoId: this.$route.query.sido * 1,
      sigunguId: this.$route.query.sigungu * 1,
      minPrice: this.$route.query.minPrice,
      maxPrice: this.$route.query.maxPrice,
      pageId: 0,
      isOpenCategory: false,
      isOpenRegion: false,
      isOpenPrice: false,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "sigungu", "searchClassList"]),
    bigcategoryName: {
      get() {
        return this.bigcategory[this.bigcategoryId].name;
      },
      set() {},
    },
    sigunguName: {
      get() {
        if (this.sigunguId) {
          return this.sigungu[this.sigunguId];
        } else {
          return "";
        }
      },
      set() {},
    },
    priceRange: {
      get() {
        if (this.minPrice && this.maxPrice) {
          return (
            this.minPrice +
            " ~ " +
            this.maxPrice +
            "만원" +
            (this.maxPrice == 30 ? " 이상" : "")
          );
        } else {
          return "";
        }
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    // 클래스 검색 결과 초기화
    this.setSearchClassList([]);
  },
  // methods
  methods: {
    ...mapActions("classStore", [
      "searchClassByCategory",
      "setSearchClassList",
    ]),
    // 카테고리 모달 열기
    openCategoryModal() {
      this.isOpenCategory = true;
    },
    // 카테고리 모달 닫기
    closeCategoryModal() {
      this.isOpenCategory = false;
    },
    // 카테고리로 클래스 검색하기
    searchCategory($state) {
      let query = "?bigcategoryId=" + this.bigcategoryId;
      if (this.smallcategoryId > 0) {
        query += "&smallcategoryId=" + this.smallcategoryId;
      }
      if (this.sigunguId) {
        query += "&sigunguId=" + this.sigunguId;
      }
      if (this.minPrice) {
        query += "&minPrice=" + this.minPrice * 10000;
      }
      if (this.maxPrice && this.maxPrice < 30) {
        query += "&maxPrice=" + this.maxPrice * 10000;
      }
      let data = {
        id: this.pageId,
        query: query,
        state: $state,
      };
      this.searchClassByCategory(data);
      this.pageId++;
    },
    // 지역 필터 모달 열기
    openRegionModal() {
      this.isOpenRegion = true;
    },
    // 지역 필터 모달 닫기
    closeRegionModal() {
      this.isOpenRegion = false;
    },
    // 적용된 지역 필터를 해제
    turnOffRegionFilter() {
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: this.$route.query.smallcategory,
          minPrice: this.$route.query.minPrice,
          maxPrice: this.$route.query.maxPrice,
        },
      });
      this.$router.go();
    },
    // 가격 필터 모달 열기
    openPriceModal() {
      this.isOpenPrice = true;
    },
    // 지역 필터 모달 닫기
    closePriceModal() {
      this.isOpenPrice = false;
    },
    // 적용된 가격 필터를 해제
    turnOffPriceFilter() {
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: this.$route.query.smallcategory,
          sido: this.$route.query.sido,
          sigungu: this.$route.query.sigungu,
        },
      });
      this.$router.go();
    },
  },
};
</script>

<style scoped src="./CategorySearch.css"></style>
