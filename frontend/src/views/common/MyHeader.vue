<template>
  <div class="header-wrapper">
    <div @click="$router.push({ name: 'Home' })">
      <img src="@/assets/images/logo.svg" class="logo" />
    </div>
    <div class="icon-wrapper" @click="$router.push({ name: 'Search' })">
      <i class="fas fa-search"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "Template",
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
