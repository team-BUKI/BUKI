<template>
  <div class="setting-container">
    <span class="my-title title-3">회원 정보 수정</span>
    <i class="fas fa-times close-icon" @click="clickCloseButton"></i>
    <div class="wrap">
      <!-- 닉네임 부분 -->
      <div class="nickname-section">
        <div class="title">
          <span class="title-4 title middle-title">닉네임 설정</span>
        </div>
        <div class="form-wrapper">
          <div class="title-wrapper">
            <span class="title-4 black-title">아이디:</span>
          </div>
          <div class="content-wrapper">
            <span class="title-4 black-title"> {{ this.getEmail }}</span>
          </div>
        </div>
        <div class="form-wrapper">
          <div class="title-wrapper">
            <span class="title-4 black-title">닉네임:</span>
          </div>
          <form @submit.prevent="validationHandler" class="nickname-form">
            <input
              class="content-wrapper title-4 black-title nickname-input"
              :placeholder="getPlaceHolder"
              v-model="nickname"
              ref="nickname"
              required
              minlength="2"
              maxlength="10"
              :readonly="!modifyNickname"
              :class="{ 'nickname-input-inactive': !modifyNickname }"
            />
          </form>
        </div>
        <div v-if="errorMsg != null" class="title-7 error-msg">
          <span>{{ errorMsg }}</span>
        </div>
        <button
          v-if="!modifyNickname"
          class="nickname-button"
          type="submit"
          @click.prevent="toggleNicknameButton"
        >
          <span class="title-4 black-title">닉네임 수정</span>
        </button>
        <div v-else class="button-wrapper">
          <button class="nickname-valid-button" @click.prevent="validationHandler" ref="validation">
            <span class="title-4">중복 검사</span>
          </button>
          <button class="nickname-valid-button" @click.prevent="quitModifyNickname">
            <span class="title-4">취소</span>
          </button>
          <button class="nickname-button" @click.prevent="saveNickname">
            <span class="title-4 black-title">닉네임 저장</span>
          </button>
        </div>
      </div>
      <!-- 로그아웃, 회원 탈퇴 부분 -->
      <div class="logout-section">
        <div class="title">
          <span class="title-4 title middle-title">계정 관리</span>
        </div>
        <div class="logout-button" @click="clickLogout">
          <span class="title-4 black-title">로그아웃</span>
        </div>
        <div class="logout-button" @click="clickSignout">
          <span class="title-4 black-title">회원탈퇴</span>
        </div>
      </div>
    </div>
    <!-- verify modal -->
    <verify-modal
      v-if="openModal"
      :content="content"
      :leftTitle="leftTitle"
      :rightTitle="rightTitle"
      :type="type"
      @closeModal="closeModal"
      @clickLeft="clickLeft"
    ></verify-modal>
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
import SERVER from "@/api/api.js";
import axios from "axios";
import Swal from "sweetalert2";
import VerifyModal from "./VerifyModal.vue";

export default {
  name: "Setting",
  components: {
    VerifyModal,
  },
  data() {
    return {
      nicknameDuplicate: false,
      nicknameValidate: false,
      nickname: "",
      errorMsg: "",
      modifyNickname: false,
      openModal: false,
      content: "",
      leftTitle: "",
      rightTitle: "",
      type: "",
      isQuitModifyNickname: false,
      placeholderMsg: "",
    };
  },
  mounted() {
    this.placeholderMsg = this.getNickname;
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
    ...mapGetters("accountStore", ["getNickname", "getEmail"]),
    ...mapGetters(["authorization"]),
    getPlaceHolder() {
      if (!this.modifyNickname) {
        return this.getNickname;
      } else {
        return "닉네임을 입력하세요";
      }
    },
  },
  methods: {
    ...mapActions("accountStore", ["removeUserInfo", "updateNickname", "signout"]),
    //세팅 모달 끝내기
    clickCloseButton() {
      this.$emit("closeSetting");
    },
    toggleNicknameButton() {
      this.modifyNickname = !this.modifyNickname;
      if (this.modifyNickname) {
        this.$refs.nickname.focus();
      }
    },
    quitModifyNickname() {
      this.nickname = this.getNickname;
      this.modifyNickname = false;
    },
    // 닉네임 validation
    validationHandler() {
      if (this.nickname == null || !this.nicknameValidate) {
        this.$refs.nickname.focus();
        this.errorMsg = "닉네임은 2자 이상 10자 이하입니다.";
      } else {
        axios({
          method: "get",
          url: SERVER.URL + SERVER.ROUTES.checkNickname + this.nickname,
          headers: this.authorization,
        })
          .then(({ data }) => {
            console.log("success");
            this.nicknameDuplicate = true;
            this.errorMsg = "가능한 닉네임입니다.";
          })
          .catch((err) => {
            console.log(err);
            this.errorMsg = "이미 사용중인 닉네임입니다.";
          });
      }
    },
    // 닉네임 저장
    saveNickname() {
      if (this.nicknameDuplicate) {
        this.updateNickname(this.nickname);
        Swal.fire({
          text: "닉네임을 성공적으로 변경하였습니다",
          showConfirmButton: false,
          timer: 1000,
        });
        this.errorMsg = "";
        this.modifyNickname = false;
      } else {
        this.$refs.validation.focus();
        this.errorMsg = "닉네임 중복 검사를 안하셨습니다.";
      }
    },
    //로그아웃
    clickLogout() {
      this.content = "정말 로그아웃 하시겠습니까?";
      this.leftTitle = "로그아웃";
      this.rightTitle = "취소";
      this.type = "logout";
      this.openModal = true;
    },
    //계정삭제
    clickSignout() {
      this.content = "정말 회원 탈퇴 하시겠습니까? <br>여태까지 쌓은 추억이 모두 사라져요🥲";
      this.leftTitle = "회원탈퇴";
      this.rightTitle = "취소";
      this.type = "signout";
      this.openModal = true;
    },
    // verify modal 닫기
    closeModal() {
      this.openModal = false;
    },
    clickLeft(_type) {
      this.openModal = false;
      if (_type == "logout") {
        this.removeUserInfo();
        this.$router.push({ name: "Home" });
      } else if (_type == "signout") {
        this.signout();
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>
<style scoped src="./Setting.css"></style>
