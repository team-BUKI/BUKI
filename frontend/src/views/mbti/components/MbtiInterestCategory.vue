<template>
  <div class="interest-container">
    <div class="my-title title-3">나의 관심 카테고리 수정</div>
    <i @click="clickCloseButton" class="fas fa-times close-icon"></i>
  </div>
</template>
<script>
import { mapActions, mapetters } from "vuex";
export default {
  name: "MbtiInterestCategory",
};
</script>
