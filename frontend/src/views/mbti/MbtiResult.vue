<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <img
          class="logo"
          src="@/assets/images/logo.png"
          @click="$router.push({ name: 'Home' })"
        />
        <span class="title title-3">MBTI 테스트</span>
      </div>
      <div class="contents" v-if="recommendCategory">
        <div class="mbti">
          <span class="title title-1">{{ this.mbti }}</span>
        </div>
        <span class="message title-5">{{ this.mbtiDesc }}</span>
        <img
          class="img"
          :src="`https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/category/category-${this.recommendCategory[0].bigcategoryId}.png`"
        />
        <div class="result">
          <p class="title-5">이런 취미를 추천해요!</p>
          <p class="title-7" @click="clickOpenModal">나의 관심 카테고리 수정</p>
          <category-tag
            class="category-tag"
            v-for="(item, index) in this.recommendCategory"
            :key="index"
            :idx="item.smallcategoryId"
            :name="getSmallcategoryName(item.smallcategoryId)"
          >
          </category-tag>
        </div>
        <div class="mbti button-4">
          <span class="title-4" @click="$router.push({ name: 'Mbti' })"
            >다시하기</span
          >
        </div>
      </div>
      <my-footer :selected="'home'" />
    </div>
    <mbti-interest-category
      v-if="openModal"
      :recommendCategory="recommendCategory"
      @clickCloseButton="clickCloseButton"
    ></mbti-interest-category>
  </div>
</template>

<script>
import CategoryTag from "../mypage/components/Category/CategoryTag.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import MbtiInterestCategory from "./components/MbtiInterestCategory.vue";
import { mapState } from "vuex";

export default {
  name: "MbtiResult",
  components: {
    MyFooter,
    CategoryTag,
    MbtiInterestCategory,
  },
  // props
  props: {},
  // data
  data() {
    return {
      desc: "",
      openModal: false,
    };
  },
  //mounted
  mounted() {},
  // computed
  computed: {
    ...mapState("mbtiStore", ["recommendCategory", "mbti", "mbtiDesc"]),
    ...mapState("classStore", ["smallcategory"]),
  },
  // methods
  methods: {
    getSmallcategoryName(idx) {
      return this.smallcategory[idx];
    },
    clickOpenModal() {
      this.openModal = true;
    },
    clickCloseButton() {
      this.openModal = false;
    },
  },
};
</script>
<style scoped src="./MbtiResult.css"></style>
