<template>
  <div v-if="isShow" class="modal-container">
    <div class="header-wrapper">
      <div class="title title-3">타이틀</div>
      <div class="icon-wrapper" @click="clickClose">
        <i class="fas fa-times"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Template",
  components: {},
  // props
  props: {
    isShow: Boolean,
  },
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
    clickClose() {
      this.isShow = false;
    },
  },
};
</script>

<style scoped src="./MyModal.css"></style>
