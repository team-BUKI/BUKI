<template>
  <div class="setting-container">
    <span class="my-title title-3">회원 정보 수정</span>
    <i class="fas fa-times close-icon" @click="clickCloseButton"></i>
    <div class="wrap">
      <div class="nickname-section">
        <div class="title">
          <span class="title-4 title middle-title">닉네임 설정</span>
        </div>
        <div>
          <div class="title-wrapper">
            <span class="title title-4">아이디:</span>
          </div>
          <div class="content-wrapper">
            <span class="title title-4"> {{ this.getEmail }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  name: "Setting",
  computed: {
    ...mapGetters("accountStore", ["getNickname", "getEmail"]),
  },
  methods: {
    clickCloseButton() {
      this.$emit("closeSetting");
    },
  },
};
</script>
<style scoped src="./Setting.css"></style>
