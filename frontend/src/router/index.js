import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // 존재하지 않는 경로
  {
    path: "*",
    redirect: "/404",
  },
  // 404 Error 페이지
  {
    path: "/404",
    name: "NotFound",
    component: () => import("@/views/common/NotFound.vue"),
  },
  // 메인 화면
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home.vue"),
  },
  // 추천 클래스 전체보기 (관심 카테고리 기반)
  {
    path: "/class/recommend/first",
    name: "FirstRecommendClass",
    component: () => import("@/views/home/FirstRecommendClass.vue"),
  },
  // 추천 클래스 전체보기 (클릭 로그 기반)
  {
    path: "/class/recommend/second",
    name: "SecondRecommendClass",
    component: () => import("@/views/home/SecondRecommendClass.vue"),
  },
  // 카테고리 목록
  {
    path: "/category",
    name: "Category",
    component: () => import("@/views/category/Category.vue"),
  },
  // 카테고리로 클래스 검색
  {
    path: "/category/search",
    name: "CategorySearch",
    component: () => import("@/views/category/CategorySearch.vue"),
  },
  // 키워드로 클래스 검색
  {
    path: "/search",
    name: "Search",
    component: () => import("@/views/search/Search.vue"),
  },
  // 일기 목록 (피드)
  {
    path: "/diary",
    name: "Diary",
    component: () => import("@/views/diary/Diary.vue"),
  },
  // 일기 목록 (캘린더)
  {
    path: "/diary/calendar",
    name: "DiaryCalendar",
    component: () => import("@/views/diary/DiaryCalendar.vue"),
  },
  // 일기 작성
  {
    path: "/diary/write",
    name: "DiaryWrite",
    component: () => import("@/views/diary/DiaryWrite.vue"),
  },
  // 일기 수정
  {
    path: "/diary/update",
    name: "DiaryUpdate",
    component: () => import("@/views/diary/DiaryUpdate.vue"),
  },
  // MBTI 테스트
  {
    path: "/mbti",
    name: "Mbti",
    component: () => import("@/views/mbti/Mbti.vue"),
  },
  // MBTI 테스트 질문
  {
    path: "/mbti/test",
    name: "MbtiTest",
    component: () => import("@/views/mbti/MbtiTest.vue"),
  },
  // MBTI 테스트 결과
  {
    path: "/mbti/result",
    name: "MbtiResult",
    component: () => import("@/views/mbti/MbtiResult.vue"),
  },
  // 마이페이지
  {
    path: "/mypage",
    name: "MyPage",
    component: () => import("@/views/mypage/MyPage.vue"),
  },
  // 회원가입
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/account/Register.vue"),
  },
  // 로그인
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/account/Login.vue"),
  },
  // 관심 카테고리 등록
  {
    path: "/interestcategory",
    name: "InterestCategory",
    component: () =>
      import("@/views/mypage/components/Category/InterestCategory.vue"),
  },
  // 관심 지역 등록
  {
    path: "/interestLocation",
    name: "InterestLocation",
    component: () =>
      import("@/views/mypage/components/Location/InterestLocation.vue"),
  },
  // 회원정보 수정
  {
    path: "/setting",
    name: "Setting",
    component: () => import("@/views/mypage/components/Setting.vue"),
  },
  // 전체 부캐 페이지
  {
    path: "/character",
    name: "TotalCharacter",
    component: () => import("@/views/mypage/components/TotalCharacter.vue"),
  },
  // 관심 클래스
  {
    path: "/interestclass",
    name: "InterestClass",
    component: () => import("@/views/mypage/components/InterestClass.vue"),
  },
  // 랭킹
  {
    path: "/ranking",
    name: "Ranking",
    component: () => import("@/views/ranking/Ranking.vue"),
  },
  // 다른 사용자의 일기
  {
    path: "/ranking/diary",
    name: "UserDiary",
    component: () => import("@/views/ranking/UserDiary.vue"),
  },
];

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => {
    if (err.name !== "NavigationDuplicated") throw err;
  });
};

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  // 스크롤 위치 고정
  scrollBehavior(to, from, savedPosition) {
    return { x: 0, y: 0 };
  },
});

export default router;
