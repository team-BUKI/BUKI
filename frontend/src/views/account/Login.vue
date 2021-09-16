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
    <my-footer :selected="'mypage'" />
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapActions } from "vuex";
import axios from "axios";

export default {
  name: "Login",
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
    ...mapActions(["accountStore"], ["setId", "setToken", "setSocialType"]),

    async googleLogin() {
      try {
        const googleUser = await this.$gAuth.signIn();
        const profile = googleUser.getBasicProfile();
        const email = profile.getEmail();

        axios({
          method: "post",
          url: "https://buki.com/api/user/login",
          data: {
            socialType: "google",
            email: email,
          },
        })
          .then(({ data }) => {
            if (data.first) {
              // 회원가입 페이지로 보내기
              localStorage.setItem("token", data.token);
              this.setToken(data.token);
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
    kakaoLogin() {},
  },
};
</script>
<style scoped src="./Login.css"></style>
