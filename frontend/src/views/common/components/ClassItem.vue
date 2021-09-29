<template>
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
      <div class="category-div" :class="'category-' + item.bigcategoryId">
        <span class="body-3">{{ smallcategoryName }}</span>
      </div>
      <span class="body-3 sub-color">{{ classAddress }}</span>
    </div>
    <span class="strong-2 class-title">{{ item.title }}</span>
    <div class="class-sub-info">
      <span class="body-2 sub-color">{{ "♥ " + item.likeCnt }}</span>
      <span class="strong-2 class-price">{{ classPrice }}</span>
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
    return {};
  },
  // computed
  computed: {
    ...mapState(["token"]),
    ...mapState("classStore", ["smallcategory", "sido", "sigungu"]),
    smallcategoryName: {
      get() {
        return this.smallcategory[this.item.smallcategoryId];
      },
      set() {},
    },
    classAddress: {
      get() {
        if (this.item.sidoId == 9) {
          return this.sido[this.item.sidoId].name;
        } else {
          return this.sigungu[this.item.sigunguId];
        }
      },
      set() {},
    },
    classPrice: {
      get() {
        let price = this.item.price.toLocaleString("ko-KR") + "원";
        if (this.item.type == "일") {
          return price;
        } else if (this.item.type == "월") {
          return "월 " + price;
        } else {
          return price + "/시간";
        }
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions("classStore", ["setInterestClass", "putClickLog"]),
    // 관심 클래스로 등록여부 변경
    clickInterest(event) {
      event.stopPropagation();
      let data = { hobbyClassId: this.item.id, interest: this.item.interest };
      this.setInterestClass(data);
      this.item.interest = !this.item.interest;
    },
    // 해당 클래스 사이트로 이동
    clickCard() {
      window.open(this.item.siteUrl);
      // 클래스 클릭 로그 저장
      if (this.token && this.token != "") {
        this.putClickLog(this.item.id);
      }
    },
  },
};
</script>

<style scoped src="./ClassItem.css"></style>
