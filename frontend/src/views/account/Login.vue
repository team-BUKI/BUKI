<template>
  <div class="container">
    <div class="my-title title-3">마이페이지</div>
    <img src="@/assets/images/logincloud.png" class="login-cloud" />

    <img src="@/assets/images/characters/character9-2.gif" class="image" />

    <div class="login-container">
      <div class="kakao-button btn" @click="kakaoLogin">
        <img src="@/assets/images/kakao.svg" class="icon" />
        <div class="title-wrap">
          <span class="title-4 login-title">Kakao로 로그인</span>
        </div>
      </div>
      <div class="google-button btn" @click="submitGoogleLogin">
        <img src="@/assets/images/google.svg" class="icon" />
        <div class="title-wrap">
          <span class="title-4 login-title">Google로 로그인</span>
        </div>
      </div>
    </div>
    <my-footer :selected="'mypage'" />
    <register
      v-if="this.isFirstLogin"
      @quitRegister="quitRegister"
      @registerSuccess="registerSuccess"
    ></register>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapActions } from "vuex";
import axios from "axios";
import { API_SERVER_URL } from "@/constant/index.js";
import Register from "./Register.vue";

export default {
  name: "Login",
  components: {
    MyFooter,
    Register,
  },
  // props
  props: {},
  // data
  data() {
    return {
      isFirstLogin: false,
    };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions("accountStore", ["removeUserInfo", "googleLogin"]),

    quitRegister() {
      console.log("stop register");
      this.isFirstLogin = false;
      this.removeUserInfo();
      this.$router.push({ path: "/login" });
    },

    registerSuccess() {
      this.isFirstLogin = false;
      this.$router.push({ path: "/mypage" });
    },

    submitGoogleLogin() {
      let isFirst = this.googleLogin();
      if (isFirst) {
        this.isFirstLogin = true;
      }
    },

    kakaoLogin() {
      window.Kakao.Auth.login({
        scope: "account_email, gender",
        success: () => {
          console.log("kakao login");
          window.Kakao.API.request({
            url: "/v2/user/me",
            success: (res) => {
              document.cookie = "safeCookie1=foo; SameSite=Lax";
              document.cookie = "safeCookie2=foo";
              document.cookie = "crossCookie=bar; SameSite=None; Secure";

              const kakao_acocunt = res.kakao_account;
              const email = kakao_acocunt.email;
              console.log(kakao_acocunt);
              console.log(email);
              this.email = email;
              axios({
                method: "post",
                url: API_SERVER_URL + "/api/user/login",
                data: {
                  socialType: "KAKAO",
                  email: email,
                },
              })
                .then(({ data }) => {
                  console.log("result:" + data);
                  localStorage.setItem("token", data.token);

                  // id, email, token, socialType 저장
                  this.setId(data.id);
                  this.setEmail(email);
                  this.setToken(data.token);
                  this.setSocialType("KAKAO");
                  if (data.first) {
                    // 회원가입 페이지로 보내기
                    this.isFirstLogin = true;
                  } else {
                    // 로그인, 마이페이지로 보내기
                    this.$router.push({ path: "/mypage" });
                  }
                })
                .catch((error) => {
                  console.log(error);
                });
            },
            fail: (res) => {
              console.log(res);
            },
          });
        },
      });
    },
  },
};
</script>
<style scoped src="./Login.css"></style>
