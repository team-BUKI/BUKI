import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Search from "@/views/search/Search.vue";
import Mbti from "@/views/mbti/Mbti.vue";
import Login from "@/views/account/Login.vue";
import KakaoOauth from "@/views/account/KakaoOauth.vue";
import MyPage from "@/views/mypage/MyPage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
  },
  {
    path: "/mbti",
    name: "Mbti",
    component: Mbti,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage
  },
  {
    path: "/kakao/oauth",
    name: "KakaoOauth",
    component: KakaoOauth
  },

];

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
