<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="modal-text-div">
        <span class="title-4">{{ title }}</span>
        <span class="text-content title-6">{{ content }}</span>
      </div>
      <div class="button-div">
        <div class="button close" @click="clickClose">
          <span class="title-4">{{ closeText }}</span>
        </div>
        <div class="button confirm" @click="clickConfirm">
          <span class="title-4">{{ confirmText }}</span>
        </div>
      </div>
    </div>
    <div
      class="modal-background"
      @click="clickClose"
      @change="clickClose"
    ></div>
  </div>
</template>
<script>
export default {
  name: "ConfirmCloseModal",
  // props
  props: {
    title: String,
    content: String,
    confirmText: String,
    closeText: String,
  },
  // methods
  methods: {
    // 확인 버튼 클릭
    clickConfirm() {
      this.$emit("confirm");
    },
    // 취소 버튼 클릭
    clickClose() {
      this.$emit("close");
    },
  },
};
</script>
<style scoped src="./ConfirmCloseModal.css"></style>
