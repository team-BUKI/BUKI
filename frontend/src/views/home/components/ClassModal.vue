<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="class-info">
        <img :src="item.imageUrl" alt="Class Image" />
        <div class="class-sub-info">
          <div :class="'category-' + item.bigcategoryId">
            <span class="body-2">{{ smallcategoryName }}</span>
          </div>
          <span class="body-2 sub-color">{{ classAddress }}</span>
        </div>
        <span class="strong-1 class-title">{{ item.title }}</span>
        <div class="class-sub-info">
          <span class="body-2 sub-color">{{ "♥ " + item.likeCnt }}</span>
          <span class="strong-1 class-price">{{ classPrice }}</span>
        </div>
        <div class="title-5 button-2" @click="clickSiteButton">
          {{ item.site + "에서 확인하기" }}
        </div>
      </div>
    </div>
    <div
      class="modal-background"
      @click="clickClose"
      @change="clickClose"
    ></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ClassModal",
  components: {},
  // props
  props: {
    item: Object,
    position: Number,
  },
  // data
  data() {
    return {
      smallcategoryName: "",
      classAddress: "",
      classPrice: "",
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["smallcategory", "sido", "sigungu"]),
  },
  // lifecycle hook
  mounted() {
    // 세부 카테고리 설정
    this.smallcategoryName = this.smallcategory[this.item.smallcategoryId];
    // 클래스 주소 설정
    this.classAddress = this.sido[this.item.sidoId].name;
    if (this.item.sidoId != 9) {
      this.classAddress += " " + this.sigungu[this.item.sigunguId];
    }
    // 클래스 가격 설정
    this.classPrice = this.item.price.toLocaleString("ko-KR") + "원";
  },
  // methods
  methods: {
    // 클래스 정보 모달 닫기
    clickClose() {
      this.$emit("closeModal");
    },
    // 해당 클래스 사이트로 이동
    clickSiteButton() {
      window.open(this.item.siteUrl);
    },
  },
};
</script>

<style scoped src="./ClassModal.css"></style>
