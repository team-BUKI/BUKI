<template>
  <div class="header-wrapper">
    <img
      class="logo"
      src="@/assets/images/logo.png"
      @click="$router.push({ name: 'Home' })"
    />
    <div class="icon-wrapper" @click="$router.push({ name: 'Search' })">
      <i class="fas fa-search"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyHeader",
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

<style scoped src="./MyHeader.css"></style>
