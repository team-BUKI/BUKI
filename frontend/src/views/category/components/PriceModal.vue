<template>
  <div class="modal-container">
    <div class="header-wrapper">
      <span class="title title-3">가격</span>
      <div class="icon-wrapper" @click="clickClose">
        <i class="fas fa-times"></i>
      </div>
    </div>
    <div class="contents">
      <div class="sub-title">
        <span class="title-4">검색 범위</span>
        <span class="price-range title-4">{{ priceRange }}</span>
      </div>
      <div class="price-slider">
        <vue-slider
          v-model="priceMinMax"
          :process="process"
          :enable-cross="false"
          :min="0"
          :max="30"
          :min-range="1"
          :marks="marks"
        >
          <template v-slot:mark="{ pos, label }">
            <div class="custom-mark" :style="{ left: `${pos}%` }">
              {{ label }}
            </div>
          </template>
          <template v-slot:tooltip="{ value }">
            <div class="custom-tooltip">{{ value }}</div>
          </template>
        </vue-slider>
      </div>
      <div class="button-4 title-5" @click="searchByPrice">적용하기</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PriceModal",
  components: {},
  // props
  props: {
    minPrice: Number,
    maxPrice: Number,
  },
  // data
  data() {
    return {
      priceMinMax: [this.minPrice, this.maxPrice],
      process: (dotsPos) => [
        [-1, dotsPos[0], { backgroundColor: "var(--main-purple)" }],
        [dotsPos[0], dotsPos[1], { backgroundColor: "var(--main-lime)" }],
        [dotsPos[1], 101, { backgroundColor: "var(--main-purple)" }],
      ],
      marks: { 0: "0원", 30: "30만원 이상" },
    };
  },
  // computed
  computed: {
    priceRange: {
      get() {
        return (
          this.priceMinMax[0] +
          " ~ " +
          this.priceMinMax[1] +
          "만원" +
          (this.priceMinMax[1] == 30 ? " 이상" : "")
        );
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    // 선택한 가격 범위로 클래스 검색
    searchByPrice() {
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: this.$route.query.smallcategory,
          sido: this.$route.query.sido,
          sigungu: this.$route.query.sigungu,
          minPrice: this.priceMinMax[0],
          maxPrice: this.priceMinMax[1],
        },
      });
      this.$router.go();
    },
    // 카테고리 모달 닫기
    clickClose() {
      this.$emit("closeModal");
    },
  },
};
</script>

<style scoped src="./PriceModal.css"></style>
