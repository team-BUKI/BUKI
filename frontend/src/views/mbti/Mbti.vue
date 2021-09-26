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
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "Mbti",
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
    clickKakao() {},
    clickGoogle() {},
  },
};
</script>

<style scoped src="./Mbti.css"></style>
