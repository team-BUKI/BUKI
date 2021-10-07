<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <img
          class="logo"
          src="@/assets/images/logo.png"
          @click="$router.push({ name: 'Home' })"
        />
        <span class="title title-3">MBTI 테스트</span>
      </div>
      <div class="contents">
        <img src="@/assets/images/mbti.png" alt="MBTI Test" class="banner" />
        <div
          class="button-4 title-5"
          @click="$router.push({ name: 'MbtiTest' })"
        >
          시작하기
        </div>
        <div v-if="token" class="user-div">
          <span class="message title-6"
            >나에게 맞는 취미를 알고 싶다면<br />지금 테스트를 시작하세요!</span
          >
        </div>
        <div v-else class="guest-div">
          <span class="message title-6"
            >테스트 결과를 저장 하시려면<br />로그인 후 테스트를
            시작해주세요!</span
          >
          <div class="button-div">
            <div class="kakao" @click="clickKakao">
              <img src="@/assets/images/kakao.svg" class="icon" />
            </div>
            <div class="google" @click="clickGoogle">
              <img src="@/assets/images/google.svg" class="icon" />
            </div>
          </div>
        </div>
      </div>
      <my-footer :selected="'home'" />
      <register v-if="isFirstLogin" @quitRegister="quitRegister"></register>
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import Register from "@/views/account/Register.vue";
import { mapActions, mapState } from "vuex";
import SERVER from "@/api/api";
import axios from "axios";

export default {
  name: "Mbti",
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
  computed: {
    ...mapState(["token"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions("accountStore", ["dispatchLoginInfo", "removeUserInfo"]),
    // 카카오 로그인
    async clickKakao() {
      await window.Kakao.Auth.login({
        scope: "account_email, gender",
        success: () => {
          window.Kakao.API.request({
            url: "/v2/user/me",
            success: (res) => {
              const kakao_acocunt = res.kakao_account;
              const email = kakao_acocunt.email;
              axios
                .post(SERVER.URL + SERVER.ROUTES.login, {
                  socialType: "KAKAO",
                  email: email,
                })
                .then(({ data }) => {
                  let userInfo = {
                    id: data.id,
                    email: email,
                    socialType: "KAKAO",
                    token: data.token,
                    nickname: data.nickname,
                  };
                  // 로그인 정보 저장
                  this.dispatchLoginInfo(userInfo);
                  if (data.first) {
                    // 회원가입 화면 띄우기
                    this.isFirstLogin = true;
                  } else {
                    // MBTI 메인 다시 띄우기
                    this.$router.go();
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
    // 구글 로그인
    async clickGoogle() {
      try {
        const googleUser = await this.$gAuth.signIn();
        const profile = googleUser.getBasicProfile();
        const email = profile.getEmail();
        this.email = email;

        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";
        axios
          .post(SERVER.URL + SERVER.ROUTES.login, {
            socialType: "GOOGLE",
            email: email,
          })
          .then(({ data }) => {
            let userInfo = {
              id: data.id,
              email: email,
              socialType: "GOOGLE",
              token: data.token,
              nickname: data.nickname,
            };
            // 로그인 정보 저장
            this.dispatchLoginInfo(userInfo);
            if (data.first) {
              // 회원가입 화면 띄우기
              this.isFirstLogin = true;
            } else {
              // MBTI 메인 다시 띄우기
              this.$router.go();
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } catch (e) {
        console.error(e);
      }
    },
    // 회원가입 중단
    quitRegister() {
      this.isFirstLogin = false;
      this.removeUserInfo();
    },
  },
};
</script>

<style scoped src="./Mbti.css"></style>
