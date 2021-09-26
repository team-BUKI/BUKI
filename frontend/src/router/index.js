import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import RecommendClass from "@/views/home/RecommendClass.vue";
import InterestClass from "@/views/home/InterestClass.vue";
import Category from "@/views/category/Category.vue";
import CategorySearch from "@/views/category/CategorySearch.vue";
import Search from "@/views/search/Search.vue";
import Diary from "@/views/diary/Diary.vue";
import Mbti from "@/views/mbti/Mbti.vue";
import MyPage from "@/views/mypage/MyPage.vue";
import Register from "@/views/account/Register.vue";
import Login from "@/views/account/Login.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/class/recommend",
    name: "RecommendClass",
    component: RecommendClass,
  },
  {
    path: "/class/interest",
    name: "InterestClass",
    component: InterestClass,
  },
  {
    path: "/category",
    name: "Category",
    component: Category,
  },
  {
    path: "/category/search",
    name: "CategorySearch",
    component: CategorySearch,
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
  },
  {
    path: "/diary",
    name: "Diary",
    component: Diary,
  },
  {
    path: "/mbti",
    name: "Mbti",
    component: Mbti,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
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
  // 스크롤 위치 최상단으로 고정
  scrollBehavior(to, from, savedPosition) {
    return { x: 0, y: 0 };
  },
});

export default router;
