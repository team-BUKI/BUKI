<template>
  <div>
    <div class="container">
      <my-header />
      <img
        src="@/assets/images/banner.png"
        alt="MBTI Test Banner"
        class="banner"
        @click="$router.push({ name: 'Mbti' })"
      />
      <div class="contents">
        <div v-if="token && token != ''">
          <div
            v-if="firstRecommendClassList && firstRecommendClassList.length > 0"
            class="recommend"
          >
            <div class="recommend-title">
              <div>
                <span class="title title-6">{{ nickname }}님을 위한</span
                ><span class="title title-4">추천 클래스</span>
              </div>
              <span
                class="text-button title-6"
                @click="$router.push({ name: 'FirstRecommendClass' })"
                >전체보기</span
              >
            </div>
            <class-list
              :classList="firstRecommendClassList"
              @openModal="openModal"
            />
          </div>
          <div v-else class="no-recommend">
            <div class="recommend-title">
              <span class="title title-4"
                >{{ nickname }}님을 위한 추천 클래스</span
              >
            </div>
            <div class="no-recommend-wrapper title-5">
              <span class="title-5"
                >맞춤 추천을 위해서<br />관심 카테고리 등록이 필요해요!</span
              >
              <div
                class="button-1 title-6"
                @click="$router.push({ name: 'Mbti' })"
              >
                취미 추천 받으러 가기
              </div>
              <div class="button-1 title-6 dark" @click="clickInterestCategory">
                관심 카테고리 설정
              </div>
            </div>
          </div>
          <div class="recommend">
            <div class="recommend-title">
              <div>
                <span class="title title-6">{{ nickname }}님의</span
                ><span class="title title-4">취향 저격 클래스</span>
              </div>
              <span
                class="text-button title-6"
                @click="$router.push({ name: 'SecondRecommendClass' })"
                >전체보기</span
              >
            </div>
            <class-list
              :classList="secondRecommendClassList"
              @openModal="openModal"
            />
          </div>
        </div>
        <div class="popular">
          <span class="title title-4">지금 인기 있는 클래스</span>
          <popular-list
            :popularList="popularClassList"
            @openModal="openModal"
          />
        </div>
      </div>
      <class-modal v-if="isOpen" :item="classItem" @closeModal="closeModal" />
      <my-footer :selected="'home'" />
      <!-- 관심 카테고리  -->
      <interest-category
        v-if="openInterestCategory"
        @closeInterestCategory="closeInterestCategory"
      ></interest-category>
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "./components/ClassList.vue";
import PopularList from "./components/PopularList.vue";
import ClassModal from "./components/ClassModal.vue";
import InterestCategory from "@/views/mypage/components/Category/InterestCategory.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "Template",
  components: {
    MyHeader,
    MyFooter,
    ClassList,
    PopularList,
    ClassModal,
    InterestCategory,
  },
  // props
  props: {},
  data() {
    return {
      classItem: {},
      isOpen: false,
      openInterestCategory: false,
    };
  },
  // computed
  computed: {
    ...mapState(["token"]),
    ...mapState("accountStore", ["nickname"]),
    ...mapState("classStore", [
      "firstRecommendClassList",
      "secondRecommendClassList",
      "popularClassList",
    ]),
  },
  // lifecycle hook
  mounted() {
    // 클래스 목록 불러오기
    this.fetchClassList();
  },
  // methods
  methods: {
    ...mapActions("classStore", ["fetchClassList"]),
    // 클래스 정보 모달 띄우기
    openModal(classItem) {
      this.classItem = classItem;
      this.isOpen = true;
    },
    // 클래스 정보 모달 닫기
    closeModal() {
      this.isOpen = false;
    },
    // 관심 카테고리 모달
    clickInterestCategory() {
      this.openInterestCategory = true;
    },
    // 관심 카테고리 모달 닫기
    closeInterestCategory() {
      this.openInterestCategory = false;
    },
  },
};
</script>

<style scoped src="./ClassHome.css"></style>
