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
      <div class="content"></div>
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "MbtiResult",
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
  // methods
  methods: {},
};
</script>
<style scoped src="./MbtiResult.css"></style>
