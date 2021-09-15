<template>
  <div class="container">
    <div class="my-title title-3">마이페이지</div>
    <img src="@/assets/images/myPage.svg" class="image" />

    <div class="login-container">
      <div class="kakao-button btn" @click="kakaoLogin">
        <img src="@/assets/images/kakao.svg" class="kakao-icon" />
        <div class="kakao-title title-4">
          <span>Kakao로 로그인</span>
        </div>
      </div>
      <div class="google-button btn" @click="googleLogin">
        <img src="@/assets/images/google.svg" class="kakao-icon" />
        <div class="kakao-title title-4">
          <span>Google로 로그인</span>
        </div>
      </div>
    </div>
    <my-footer :selected="'mypage'" />
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "MyPage",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return {};
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    googleLogin() {
      let token = localStorage.getItem("token");
      if (token == "") {
        //회원가입 페이지로 보내기
      } else {
        //로그인 페이지로 보내기
      }
    },
    kakaoLogin() {
      let token = localStorage.getItem("token");
      if (token == "") {
        //회원가입 페이지로 보내기
      } else {
        //로그인 페이지로 보내기
      }
    },
  },
};
</script>
<style scoped src="./MyPage.css"></style>
