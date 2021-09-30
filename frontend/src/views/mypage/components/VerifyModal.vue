<template>
  <div class="modal-wrapper">
    <div class="modal-container">
      <div class="title-container">
        <span class="title-5" v-html="this.content"></span>
      </div>
      <div class="button-wrapper">
        <div class="button closeOk" @click="clickLeft">
          <span class="title-5">{{ this.leftTitle }}</span>
        </div>
        <div class="button closeNo" @click="closeModal">
          <span class="title-5">{{ this.rightTitle }}</span>
        </div>
      </div>
    </div>
    <div class="modal-background" @click="closeModal" @change="closeModal"></div>
  </div>
</template>
<script>
export default {
  name: "Verify",
  props: {
    content: String,
    leftTitle: String,
    rightTitle: String,
    type: String,
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    clickLeft() {
      this.$emit("clickLeft", this.type);
    },
  },
};
</script>
<style scoped src="./VerifyModal.css"></style>
