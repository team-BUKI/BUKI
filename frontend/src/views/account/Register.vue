<template>
  <div class="register-container">
    <span class="my-title title-3">회원가입</span>
    <i class="fas fa-times close-icon" @click="clickCloseButton"></i>

    <!-- info modal -->
    <RegisterCloseModal
      v-if="this.openInfoModal"
      @closeInfoModal="closeInfoModal"
      @closeRegisterModal="closeRegisterModal"
    ></RegisterCloseModal>
    <div class="wrap">
      <!-- 닉네임 section -->
      <div class="nickname-section">
        <div class="nickname-title">
          <span class="title-4 title middle-title">닉네임 설정</span>
        </div>
        <form @submit.prevent="validationHandler" class="nickname-form">
          <input
            class="nickname-input"
            placeholder="닉네임을 입력하세요"
            v-model="nickname"
            ref="nickname"
          />
          <div v-if="errorMsg != null" class="title-7 error-msg">
            <span>{{ errorMsg }}</span>
          </div>
          <button class="nickname-valid-button" type="submit">
            <span class="title-5">중복 검사</span>
          </button>
        </form>
      </div>
      <!-- 관심 카테고리 section -->
      <div class="category-section">
        <div class="title-wrap">
          <span class="title-4 title middle-title">관심 카테고리</span>
          <span class="title-6 register-interest" @click="clickInterestCategory"
            >관심 카테고리 등록</span
          >
        </div>
        <div class="category-list">
          <div class="category-row" v-if="this.interestCategory.length > 0">
            <category-tag
              v-for="item in interestCategory"
              :key="item"
              :idx="item"
              :name="getSmallcategoryName(item)"
            >
            </category-tag>
          </div>
          <div v-else>
            <span class="title-5 no-category">관심 카테고리가 없습니다</span>
          </div>
        </div>
      </div>
      <!-- 관심 지역 section -->
      <div class="location-section">
        <div class="title-wrap">
          <span class="title-4 title middle-title">관심 지역</span>
          <span class="title-6 register-interest" @click="clickInterestLocation"
            >관심 지역 등록</span
          >
        </div>
        <div v-if="this.interestLocation.length > 0" class="location-list">
          <location-tag
            v-for="item in interestLocation"
            :key="item"
            :name="getSigunguName(item)"
          ></location-tag>
        </div>
        <div v-else>
          <span class="title-5 no-category">관심 지역이 없습니다</span>
        </div>
      </div>

      <!-- 가입버튼 -->
      <div
        v-if="nicknameValidate && nicknameDuplicate"
        class="register-btn-active"
        @click="submitRegister"
      >
        <span class="title-5" style="color: black">가입 완료</span>
      </div>
      <div class="register-btn-inactive" v-else>
        <span class="title-5" style="color: black">가입 완료</span>
      </div>
    </div>
    <!-- 관심 카테고리 등록 modal-->
    <interest-category
      v-if="this.openInterestCategory"
      @closeInterestCategory="closeInterestCategory"
    ></interest-category>
    <!-- 관심 지역 등록 modal -->
    <interest-location
      v-if="this.openInterestLocation"
      @closeInterestLocation="closeInterestLocation"
    ></interest-location>
  </div>
</template>
<script>
import CategoryTag from "../mypage/components/Category/CategoryTag.vue";
import LocationTag from "../mypage/components/Location/LocationTag.vue";
import InterestCategory from "../mypage/components/Category/InterestCategory.vue";
import InterestLocation from "../mypage/components/Location/InterestLocation.vue";
import RegisterCloseModal from "./RegisterCloseModal.vue";

import axios from "axios";
import { API_SERVER_URL } from "@/constant/index.js";
import { mapActions, mapState } from "vuex";

export default {
  name: "Register",
  components: {
    CategoryTag,
    LocationTag,
    RegisterCloseModal,
    InterestCategory,
    InterestLocation,
  },
  data() {
    return {
      openInfoModal: false,
      openInterestCategory: false,
      openInterestLocation: false,
      nicknameValidate: false,
      nicknameDuplicate: false,
      nickname: "",
      errorMsg: "",
    };
  },
  watch: {
    nickname: {
      handler: function () {
        if (this.nickname.length < 2 || this.nickname.length > 10) {
          this.errorMsg = "닉네임은 2자 이상 10자 이하입니다.";
          this.nicknameValidate = false;
        } else {
          this.errorMsg = "";
          this.nicknameValidate = true;
        }
      },
    },
  },
  computed: {
    ...mapState("accountStore", ["interestCategory", "interestLocation"]),
    ...mapState("classStore", ["smallcategory", "sigungu"]),
  },
  methods: {
    ...mapActions("accountStore", [
      "setNickname",
      "registerUserInfo",
      "validateNickname",
      "logout",
    ]),

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
    submitRegister() {
      this.registerUserInfo(this.nickname);
      this.$router.push({ name: "MyPage" });
    },
    //관심카테고리 모달 열기
    clickInterestCategory() {
      this.openInterestCategory = true;
    },
    //관심카테고리 모달 닫기
    closeInterestCategory() {
      this.openInterestCategory = false;
    },
    //관심카테고리 모달 열기
    clickInterestLocation() {
      this.openInterestLocation = true;
    },
    //관심지역 모달 닫기
    closeInterestLocation() {
      this.openInterestLocation = false;
    },
    // 닉네임 validation
    validationHandler() {
      if (this.nickname == null || !this.nicknameValidate) {
        this.$refs.nickname.focus();
        this.errorMsg = "닉네임은 2자 이상 10자 이하입니다.";
      } else {
        //axios
        let token = localStorage.getItem("token");
        console.log(token);
        axios({
          method: "get",
          url: API_SERVER_URL + "/api/user/nickname/" + this.nickname,
          headers: { Authorization: `Bearer ${token}` },
        })
          .then(({ data }) => {
            console.log("success");
            this.nicknameDuplicate = true;
            this.setNickname(this.nickname);
            this.errorMsg = "가능한 닉네임입니다.";
          })
          .catch((err) => {
            console.log(err);
            this.errorMsg = "이미 사용중인 닉네임입니다.";
          });
      }
    },
    getSmallcategoryName(num) {
      if (this.smallcategory != null) return this.smallcategory[num];
      return "";
    },
    getSigunguName(num) {
      return this.sigungu[num];
    },
    submitLogout() {
      this.logout();
    },
  },
};
</script>
<style scoped src="./Register.css"></style>
