<template>
  <div class="container">
    <div class="my-title title-3">마이페이지</div>
    <img src="@/assets/images/myPage.svg" class="image" />

    <div class="login-container">
      <div class="kakao-button btn" @click="kakaoLogin">
        <img src="@/assets/images/kakao.svg" class="icon" />
        <div class="kakao-title title-4">
          <span>Kakao로 로그인</span>
        </div>
      </div>
      <div class="google-button btn" @click="googleLogin">
        <img src="@/assets/images/google.svg" class="icon" />
        <div class="kakao-title title-4">
          <span>Google로 로그인</span>
        </div>
      </div>
    </div>
    <div>{{ this.email }}</div>
    <my-footer :selected="'mypage'" />
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapActions } from "vuex";
import axios from "axios";
import { API_SERVER_URL } from "@/constant/index.js";

export default {
  name: "Login",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return { email: "" };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions(["accountStore"], ["setId", "setToken", "setSocialType"]),

    async googleLogin() {
      try {
        const googleUser = await this.$gAuth.signIn();
        const profile = googleUser.getBasicProfile();
        const email = profile.getEmail();
        this.email = email;

        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";
        axios({
          method: "post",
          url: API_SERVER_URL + "/api/user/login",
          data: {
            socialType: "google",
            email: email,
          },
          headers: {
            Origin: "http://localhost:3000",
          },
        })
          .then(({ data }) => {
            if (data.first) {
              // 회원가입 페이지로 보내기
              localStorage.setItem("token", data.token);
              this.setToken(data.token);
              console.log(data.token);
            } else {
              // 로그인 처리, token localStorage에 저장
              localStorage.setItem("token", data.token);
              this.setId(email);
              this.setToken(data.token);
              this.setSocialType(data.socialType);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } catch (e) {
        console.error(e);
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
              // axios({
              //   method: "post",
              //   url: API_SERVER_URL + "/api/user/login",
              //   data: {
              //     socialType: "kakao",
              //     email: email,
              //   },
              // })
              //   .then(({ data }) => {
              //     console.log("result:" + data);
              //     // if (data.first) {
              //     //   // 회원가입 페이지로 보내기
              //     // } else {

              //     // }
              //   })
              //   .catch((error) => {
              //     console.log(error);
              //   });
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
