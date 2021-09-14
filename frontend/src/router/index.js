import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Mobile from "@/views/template/Mobile.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/test",
    name: "Mobile",
    component: Mobile,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
