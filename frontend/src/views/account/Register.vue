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

      <div
        v-if="nicknameValidate && nicknameDuplicate"
        class="register-btn-active"
        @click="success"
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
  </div>
</template>
<script>
import CategoryTag from "../common/CategoryTag.vue";
import LocationTag from "../common/LocationTag.vue";
import InterestCategory from "../common/InterestCategory.vue";
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
  },
  data() {
    return {
      openInfoModal: false,
      openInterestCategory: false,
      nicknameValidate: false,
      nicknameDuplicate: false,
      nickname: "",
      errorMsg: "",
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
    ...mapState,
  },
  methods: {
    ...mapActions("accountStore", ["setNickname"]),

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
      // this.$emit("registerSuccess");
    },
    //관심카테고리 모달 열기
    clickInterestCategory() {
      this.openInterestCategory = true;
    },
    //관심카테고리 모달 닫기
    closeInterestCategory() {
      this.openInterestCategory = false;
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
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>
<style scoped src="./Register.css"></style>
