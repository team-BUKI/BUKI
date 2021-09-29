<template>
  <div class="container">
    <div class="title title-3">마이페이지</div>
    <i @click="clickSetting" class="fas fa-cog setting-icon"></i>
    <!-- 캐릭터 section -->
    <div v-if="this.isLogin">
      <div class="mypage-wrapper">
        <!-- 캐릭터 section-->
        <character-section></character-section>
        <!-- 관심 카테고리 section -->
        <div class="category-section">
          <div class="title-wrap">
            <span class="title-4 title middle-title">관심 카테고리</span>
            <span class="title-6 register-interest" @click="clickInterestCategory"
              >관심 카테고리 등록</span
            >
          </div>
          <div class="category-list">
            <div class="category-row" v-if="this.interestCategory.length > 0">
              <category-tag
                v-for="item in interestCategory"
                :key="item"
                :idx="item"
                :name="getSmallcategoryName(item)"
              >
              </category-tag>
            </div>
            <div v-else>
              <span class="title-5 no-category">관심 카테고리가 없습니다</span>
            </div>
          </div>
        </div>
        <!-- 관심 지역 section -->
        <div class="location-section">
          <div class="title-wrap">
            <span class="title-4 title middle-title">관심 지역</span>
            <span class="title-6 register-interest" @click="clickInterestLocation"
              >관심 지역 등록</span
            >
          </div>
          <div v-if="this.interestLocation.length > 0" class="location-list">
            <location-tag
              v-for="item in interestLocation"
              :key="item"
              :name="getSigunguName(item)"
            ></location-tag>
          </div>
          <div v-else>
            <span class="title-5 no-category">관심 지역이 없습니다</span>
          </div>
        </div>
        <!-- 좋아요 클래스 -->
        <div class="class-section">
          <div class="title-wrap">
            <span class="title-4 title middle-title">관심 클래스</span>
            <span class="title-6 register-interest" @click="clickInterestClass">전체 보기</span>
          </div>
          <div v-if="interestClassList.length > 0" class="class-list-wrapper">
            <class-list :classList="interestClassList" @openModal="openModal" />
          </div>
          <div v-else>
            <span class="title-5 no-category">관심 지역이 없습니다.</span>
          </div>
        </div>
      </div>
    </div>
    <my-footer :selected="'mypage'" />
    <!-- 관심 카테고리 등록 modal-->
    <interest-category
      v-if="this.openInterestCategory"
      @closeInterestCategory="closeInterestCategory"
    ></interest-category>
    <!-- 관심 지역 등록 modal -->
    <interest-location
      v-if="this.openInterestLocation"
      @closeInterestLocation="closeInterestLocation"
    ></interest-location>
    <!-- 관심 클래스 modal-->
    <class-modal v-if="isOpen" :item="classItem" @closeModal="closeModal" />
    <!-- 세팅 modal -->
    <setting v-if="openSetting" @closeSetting="closeSetting"></setting>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import CategoryTag from "../mypage/components/Category/CategoryTag.vue";
import LocationTag from "../mypage/components/Location/LocationTag.vue";
import InterestCategory from "../mypage/components/Category/InterestCategory.vue";
import InterestLocation from "../mypage/components/Location/InterestLocation.vue";
import Setting from "../mypage/components/Setting.vue";
import ClassList from "@/views/home/components/ClassList.vue";
import ClassModal from "@/views/home/components/ClassModal.vue";
import CharacterSection from "@/views/mypage/components/CharacterSection.vue";
import { mapGetters, mapState, mapActions } from "vuex";

export default {
  name: "MyPage",
  components: {
    MyFooter,
    CategoryTag,
    LocationTag,
    InterestCategory,
    InterestLocation,
    ClassList,
    ClassModal,
    CharacterSection,
    Setting,
  },
  // props
  props: {},
  // data
  data() {
    return {
      isLogin: false,
      openInterestCategory: false,
      openInterestLocation: false,
      isOpen: false,
      classItem: {},
      openSetting: false,
    };
  },
  // computed
  computed: {
    ...mapState("accountStore", ["interestLocation", "interestCategory"]),
    ...mapState("classStore", ["smallcategory", "sigungu", "interestClassList"]),
  },
  // lifecycle hook
  mounted() {
    let token = localStorage.getItem("token");
    if (token == null || token == "") {
      //로그인 페이지로 보내기
      this.isLogin = false;
      this.$router.push({ path: "/login" });
    } else {
      //로그인 페이지로 보내기
      this.isLogin = true;
    }

    // 클래스 목록 불러오기
    this.getInterestClassFirst(0);
    // 관심 카테고리 불러오기
    this.getInterestCategory();
    // 관심 지역 불러오기
    this.getInterestLocation();
  },
  // methods
  methods: {
    ...mapActions("classStore", ["getInterestClassFirst"]),
    ...mapActions("accountStore", ["getInterestCategory", "getInterestLocation"]),
    // 회원정보 세팅 모달 열기
    clickSetting() {
      this.openSetting = true;
    },
    // 세팅 모달 닫기
    closeSetting() {
      this.openSetting = false;
    },
    //관심카테고리 모달 열기
    clickInterestCategory() {
      this.openInterestCategory = true;
    },
    //관심카테고리 모달 닫기
    closeInterestCategory() {
      this.openInterestCategory = false;
    },
    //관심카테고리 모달 열기
    clickInterestLocation() {
      this.openInterestLocation = true;
    },
    //관심지역 모달 닫기
    closeInterestLocation() {
      this.openInterestLocation = false;
    },
    getSmallcategoryName(num) {
      if (this.smallcategory != null) return this.smallcategory[num];
      return "";
    },
    getSigunguName(num) {
      return this.sigungu[num];
    },
    // 클래스 정보 모달 띄우기
    openModal(classItem) {
      this.classItem = classItem;
      this.isOpen = true;
    },
    // 클래스 정보 모달 닫기
    closeModal() {
      this.isOpen = false;
    },
    //관심 클래스 전체보기
    clickInterestClass() {
      this.$router.push({ name: "InterestClass" });
    },
  },
};
</script>
<style scoped src="./MyPage.css"></style>
