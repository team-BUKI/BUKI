<template>
  <div class="container">
    <div class="my-title title-3">회원가입</div>
    <img src="../../assets/images/close.png" class="close-icon" />
    <div class="wrap">
      <div class="nickname-section">
        <div class="nickname-title">
          <span class="title-4 title">닉네임 설정</span>
        </div>
        <input class="nickname-input" placeholder="닉네임을 입력하세요" />
      </div>
      <div class="category-section">
        <div class="category-title-wrap">
          <span class="title-4 title">관심 카테고리</span>
          <span class="title-6">관심 카테고리 등록</span>
        </div>
        <div class="category-list">
          <div class="category-row">
            <category-tag v-for="item in categoryList" :key="item.id" :category="item">
            </category-tag>
          </div>
          <div class="category-row"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CategoryTag from "../common/CategoryTag.vue";

export default {
  name: "Register",
  components: {
    CategoryTag,
  },
  data() {
    return {
      categoryList: [
        {
          id: 1,
          name: "드로잉",
        },
        {
          id: 2,
          name: "금속,가죽,도자기",
        },
        {
          id: 3,
          name: "베이킹",
        },
      ],
    };
  },
};
</script>
<style scoped src="./Register.css"></style>
