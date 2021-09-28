<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="class-info">
        <img :src="item.imageUrl" alt="Class Image" />
        <div class="interest-div" @click="clickInterest">
          <i v-if="item.interest" class="fas fa-heart interest"></i>
          <i v-else class="far fa-heart no-interest"></i>
        </div>
        <div class="class-sub-info">
          <div :class="'category-' + item.bigcategoryId">
            <span class="body-2">{{ smallcategoryName }}</span>
          </div>
          <span class="body-2 sub-color">{{ classAddress }}</span>
        </div>
        <span class="strong-1 class-title">{{ item.title }}</span>
        <div class="class-sub-info">
          <span class="body-1 sub-color">{{ "♥ " + item.likeCnt }}</span>
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
import { mapState, mapActions } from "vuex";

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
    return {};
  },
  // computed
  computed: {
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
    ...mapActions("classStore", ["setInterestClass"]),
    // 클래스 정보 모달 닫기
    clickClose() {
      this.$emit("closeModal");
    },
    // 관심 클래스 등록여부 변경
    clickInterest(event) {
      event.stopPropagation();
      let data = { hobbyClassId: this.item.id, interest: this.item.interest };
      this.setInterestClass(data);
      this.item.interest = !this.item.interest;
    },
    // 해당 클래스 사이트로 이동
    clickSiteButton() {
      window.open(this.item.siteUrl);
    },
  },
};
</script>

<style scoped src="./ClassModal.css"></style>
