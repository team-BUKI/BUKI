<template>
  <div>
    <div class="modal-container">
      <div class="header-wrapper">
        <span class="title title-3">지역</span>
        <div class="icon-wrapper" @click="clickClose">
          <i class="fas fa-times"></i>
        </div>
      </div>
      <div v-if="sidoList.length > 0" class="contents">
        <sido-list
          :sidoList="sidoList"
          :sidoId="sidoId"
          @clickSido="changeSido"
        />
        <sigungu-list
          :sigunguList="sigunguList"
          :sigunguId="sigunguId"
          @clickSigungu="changeSigungu"
        />
      </div>
      <div v-else>
        <span class="message title-5">선택할 수 있는 지역이 없습니다</span>
      </div>
    </div>
  </div>
</template>

<script>
import SidoList from "./SidoList.vue";
import SigunguList from "./SigunguList.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "RegionModal",
  components: {
    SidoList,
    SigunguList,
  },
  // props
  props: {
    prevSidoId: Number,
    prevSigunguId: Number,
  },
  // data
  data() {
    return {
      sidoId: this.prevSidoId,
      sigunguId: this.prevSigunguId,
      sidoList: [],
      sigunguList: [],
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["sido", "sigungu", "isSearchable"]),
  },
  // lifecycle hook
  mounted() {
    this.setSidoList();
    this.setSigunguList();
  },
  // methods
  methods: {
    ...mapActions("classStore", ["searchClassByCategoryTest"]),
    // 선택가능한 시도 목록 설정
    async setSidoList() {
      // 시도 목록 초기화
      this.sidoList = [];
      // 클래스가 있는 시도만 배열로 만들기
      for (var i = 1; i < this.sido.length; i++) {
        let query = "?bigcategoryId=" + this.$route.query.bigcategory;
        if (this.$route.query.smallcategory > 0) {
          query += "&smallcategoryId=" + this.$route.query.smallcategory;
        }
        query += "&sigunguId=" + this.sido[i].sigunguList[0];
        if (this.$route.query.minPrice) {
          query += "&minPrice=" + this.$route.query.minPrice * 10000;
        }
        if (this.$route.query.maxPrice) {
          query +=
            "&maxPrice=" +
            (this.$route.query.maxPrice == 30
              ? 99990000
              : this.$route.query.maxPrice * 10000);
        }
        await this.searchClassByCategoryTest(query);
        if (this.isSearchable) {
          this.sidoList.push({
            name: this.sido[i].name,
            id: i,
          });
        }
      }
    },
    // 선택한 시도의 시군구 목록 설정
    async setSigunguList() {
      // 시군구 목록 초기화
      this.sigunguList = [];
      // 클래스가 있는 시군구만 배열로 만들기
      let arr = this.sido[this.sidoId].sigunguList;
      for (var i = 0; i < arr.length; i++) {
        let query = "?bigcategoryId=" + this.$route.query.bigcategory;
        if (this.$route.query.smallcategory > 0) {
          query += "&smallcategoryId=" + this.$route.query.smallcategory;
        }
        query += "&sigunguId=" + arr[i];
        if (this.$route.query.minPrice) {
          query += "&minPrice=" + this.$route.query.minPrice * 10000;
        }
        if (this.$route.query.maxPrice) {
          query +=
            "&maxPrice=" +
            (this.$route.query.maxPrice == 30
              ? 99990000
              : this.$route.query.maxPrice * 10000);
        }
        await this.searchClassByCategoryTest(query);
        if (this.isSearchable) {
          this.sigunguList.push({
            name: this.sigungu[arr[i]],
            id: arr[i],
          });
        }
      }
    },
    // 선택한 시도 변경
    changeSido(sidoId) {
      this.sidoId = sidoId;
      this.setSigunguList();
    },
    // 선택한 시군구 변경
    changeSigungu(sigunguId) {
      this.sigunguId = sigunguId;
      this.searchByRegion(sigunguId);
    },
    // 선택한 지역으로 클래스 검색
    searchByRegion() {
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: this.$route.query.smallcategory,
          sido: this.sidoId,
          sigungu: this.sigunguId,
          minPrice: this.$route.query.minPrice,
          maxPrice: this.$route.query.maxPrice,
        },
      });
      this.$router.go();
    },
    // 지역 필터 모달 닫기
    clickClose() {
      this.$emit("closeModal");
    },
  },
};
</script>

<style scoped src="./RegionModal.css"></style>
