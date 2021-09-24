<template>
  <div class="register-container">
    <span class="my-title title-3">회원가입</span>
    <img @click="clickCloseButton" src="../../assets/images/close.png" class="close-icon" />

    <!-- info modal -->
    <RegisterCloseModal
      v-if="this.openInfoModal"
      @closeInfoModal="closeInfoModal"
      @closeRegisterModal="closeRegisterModal"
    ></RegisterCloseModal>
    <div class="wrap">
      <div class="nickname-section">
        <div class="nickname-title">
          <span class="title-4 title middle-title">닉네임 설정</span>
        </div>
        <input class="nickname-input" placeholder="닉네임을 입력하세요" />
      </div>
      <div class="category-section">
        <div class="title-wrap">
          <span class="title-4 title middle-title">관심 카테고리</span>
          <span class="title-6 register-interest" @click="clickInterestCategory"
            >관심 카테고리 등록</span
          >
        </div>
        <div class="category-list">
          <div class="category-row" v-if="this.categoryList.length > 0">
            <category-tag v-for="item in categoryList" :key="item.id" :category="item">
            </category-tag>
          </div>
          <div v-else>
            <span class="title-5 no-category">관심 카테고리가 없습니다</span>
          </div>
        </div>
      </div>
      <div class="location-section">
        <div class="title-wrap">
          <span class="title-4 title middle-title">관심 지역</span>
          <span class="title-6 register-interest">관심 지역 등록</span>
        </div>
        <div v-if="this.locationList.length > 0" class="location-list">
          <location-tag
            v-for="item in locationList"
            :key="item.sigunguId"
            :name="item.name"
          ></location-tag>
        </div>
        <div v-else>
          <span class="title-5 no-category">관심 지역이 없습니다</span>
        </div>
      </div>
      <div class="register-btn" @click="success">
        <span class="title-5" style="color: black">가입 완료</span>
      </div>
    </div>
    <!-- 관심 카테고리 등록 modal-->
    <interest-category
      v-if="this.openInterestCategory"
      @closeInterestCategory="closeInterestCategory"
    ></interest-category>
  </div>
</template>
<script>
import CategoryTag from "../common/CategoryTag.vue";
import LocationTag from "../common/LocationTag.vue";
import InterestCategory from "../common/InterestCategory.vue";
import RegisterCloseModal from "./RegisterCloseModal.vue";

export default {
  name: "Register",
  components: {
    CategoryTag,
    LocationTag,
    RegisterCloseModal,
    InterestCategory,
  },
  data() {
    return {
      openInfoModal: false,
      openInterestCategory: false,
      categoryList: [
        {
          id: 1,
          name: "드로잉",
        },
        {
          id: 2,
          name: "금속,가죽,도자기",
        },
        {
          id: 3,
          name: "베이킹",
        },
        {
          id: 4,
          name: "작곡,프로듀싱",
        },
        {
          id: 5,
          name: "액티비티",
        },
        {
          id: 6,
          name: "요가,필라테스",
        },
      ],
      locationList: [
        {
          sigunguId: 1,
          name: "서울 전체",
        },
        {
          sigunguId: 2,
          name: "강남,서초",
        },
        {
          sigunguId: 3,
          name: "노원,강북,도봉,중량",
        },
      ],
    };
  },
  methods: {
    //회원가입 종료하시겠습니까 창 열기
    clickCloseButton() {
      this.openInfoModal = true;
    },
    // 회원가입 중단
    closeRegisterModal() {
      console.log("closeRegisterModal");
      this.openInfoModal = false;
      this.$emit("quitRegister");
    },
    // 회원가입 종료하시겠습니까 창 닫기
    closeInfoModal() {
      console.log("closeInfoModal");
      this.openInfoModal = false;
    },
    //가입완료
    success() {
      this.$emit("registerSuccess");
    },
    //관심카테고리 모달 열기
    clickInterestCategory() {
      this.openInterestCategory = true;
    },
    //관심카테고리 모달 닫기
    closeInterestCategory() {
      this.openInterestCategory = false;
    },
  },
};
</script>
<style scoped src="./Register.css"></style>
