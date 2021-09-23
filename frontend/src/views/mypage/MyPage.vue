<template>
  <div class="container">
    <div class="title title-3">마이페이지</div>
    <div v-if="this.isLogin"></div>
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
    return {
      isLogin: false,
    };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {
    let token = localStorage.getItem("token");
    if (token == null) {
      //로그인 페이지로 보내기
      this.isLogin = false;
      this.$router.push({ path: "/login" });
    } else {
      //로그인 페이지로 보내기
      this.isLogin = true;
    }
  },
  // methods
  methods: {},
};
</script>
<style scoped src="./MyPage.css"></style>
