<template>
  <div>
    <div class="card" @click="clickCard">
      <div class="card-image">
        <img :src="item.imageUrl" alt="Class Image" />
      </div>
      <span class="card-site title-7">{{ item.site }}</span>
      <div class="card-interest" @click="clickInterest">
        <i v-if="item.interest" class="fas fa-heart interest"></i>
        <i v-else class="far fa-heart no-interest"></i>
      </div>
      <div class="class-sub-info">
        <div class="card-category" :class="'category-' + item.bigcategoryId">
          <span class="body-3">{{ smallcategoryName }}</span>
        </div>
        <span class="body-3 sub-color">{{ classAddress }}</span>
      </div>
      <span class="strong-2 class-title">{{ item.title }}</span>
      <div class="class-sub-info">
        <span class="body-3 sub-color">{{ "♥ " + item.likeCnt }}</span>
        <span class="strong-2 class-price">{{ classPrice }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "ClassItem",
  components: {},
  // props
  props: {
    item: Object,
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
    this.setClassInfo();
  },
  updated() {
    this.setClassInfo();
  },
  // methods
  methods: {
    ...mapActions("classStore", ["setInterestClass"]),
    // 클래스 정보 설정
    setClassInfo() {
      // 세부 카테고리 설정
      this.smallcategoryName = this.smallcategory[this.item.smallcategoryId];
      // 클래스 주소 설정
      if (this.item.sidoId == 9) {
        this.classAddress = this.sido[this.item.sidoId];
      } else {
        this.classAddress = this.sigungu[this.item.sigunguId];
      }
      // 클래스 가격 설정
      this.classPrice = this.item.price.toLocaleString("ko-KR") + "원";
    },
    // 해당 클래스 사이트로 이동
    clickCard() {
      window.open(this.item.siteUrl);
    },
    // 관심 클래스로 등록여부 변경
    clickInterest(event) {
      event.stopPropagation();
      let data = { hobbyClassId: this.item.id, interest: this.item.interest };
      this.setInterestClass(data);
      this.item.interest = !this.item.interest;
    },
  },
};
</script>

<style scoped src="./ClassItem.css"></style>
