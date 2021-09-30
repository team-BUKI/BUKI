<template>
  <div class="interest-container">
    <div class="my-title title-3">관심 지역 등록</div>
    <i @click="clickCloseButton" class="fas fa-times close-icon" />
    <!-- 관심 지역 결과 표시 -->
    <div class="result-area">
      <location-with-x
        v-for="location in interestLocation"
        :key="location"
        :name="getLocationName(location)"
        :idx="location"
      ></location-with-x>
    </div>
    <!-- 관심 지역 등록 -->
    <div class="my-title title-3" style="margin-top: 80px">지역</div>
    <!-- 대분류 -->
    <div class="sido-container">
      <sido
        v-for="(item, index) in this.sido"
        :key="item.name"
        :name="item.name"
        :idx="index"
        :selectedSigungu="selectedSigungu"
        @clickSigungu="clickSigungu"
      ></sido>
    </div>
    <!-- 소분류 -->
    <div class="sigungu-container">
      <sigungu
        v-for="item in this.sido[this.selectedSigungu].sigunguList"
        :key="item"
        :name="getSigunguName(item)"
        :idx="item"
      ></sigungu>
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import LocationWithX from "./LocationWithX.vue";
import Sido from "./Sido.vue";
import Sigungu from "./Sigungu.vue";

export default {
  name: "InterestLocation",
  components: {
    LocationWithX,
    Sido,
    Sigungu,
  },
  data() {
    return {
      selectedSigungu: 1,
    };
  },
  computed: {
    ...mapState("accountStore", ["interestLocation"]),
    ...mapState("classStore", ["sigungu", "sido"]),
  },
  methods: {
    ...mapActions("accountStore", ["setInterestRegion"]),
    clickCloseButton() {
      this.setInterestRegion();
      this.$emit("closeInterestLocation");
    },
    getLocationName(num) {
      if (this.sigungu != null) return this.sigungu[num];
      return "";
    },
    getSigunguName(num) {
      return this.sigungu[num];
    },
    clickSigungu(idx) {
      this.selectedSigungu = idx;
    },
  },
};
</script>
<style scoped src="./InterestLocation.css"></style>
