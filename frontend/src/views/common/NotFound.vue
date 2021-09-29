<template>
  <div>
    <div class="container">
      <div class="contents">
        <span class="main title title-1">404</span>
        <span class="title title-2">Page Not Found</span>
        <span class="sub title title-4">페이지를 찾을 수 없어요!</span>
        <img src="@/assets/images/404.gif" alt="Page Not Found" />
        <div
          type="button"
          class="button-4 title-5"
          @click="$router.push({ name: 'Home' })"
        >
          홈으로 가기
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  components: {},
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
  methods: {},
};
</script>

<style scoped src="./NotFound.css"></style>
