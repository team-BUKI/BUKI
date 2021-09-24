<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="title-container">
        <span class="title-5">회원가입을 종료하시겠습니까?</span>
        <div class="button-wrapper">
          <div class="button closeOk" @click="clickRegisterClose">
            <span class="title-5">종료</span>
          </div>
          <div class="button closeNo" @click="clickInfoClose">
            <span class="title-5">취소</span>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-background" @click="clickInfoClose" @change="clickInfoClose"></div>
  </div>
</template>
<script>
export default {
  name: "RegisterCloseModal",
  methods: {
    clickInfoClose() {
      this.$emit("closeInfoModal");
    },
    clickRegisterClose() {
      this.$emit("closeRegisterModal");
    },
  },
};
</script>
<style scoped src="./RegisterCloseModal.css"></style>
