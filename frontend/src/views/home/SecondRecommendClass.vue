<template>
  <div>
    <div class="container">
      <my-header />
      <div class="contents">
        <div class="title-div">
          <span class="title title-3 text-ellipsis title-nickname">{{
            nickname
          }}</span
          ><span class="title title-3">님의 취향 저격 클래스</span>
        </div>
        <div class="button-div">
          <div
            class="button-3 title-7"
            :class="{ active: this.filter == 'all' }"
            @click="clickAll"
          >
            전체
          </div>
          <div
            class="button-3 title-7"
            :class="{ active: this.filter == 'online' }"
            @click="clickOnline"
          >
            온라인
          </div>
          <div
            class="button-3 title-7"
            :class="{ active: this.filter == 'offline' }"
            @click="clickOffline"
          >
            오프라인
          </div>
        </div>
        <class-list :classList="filteredList" />
      </div>
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "@/views/common/components/ClassList.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "SecondRecommendClass",
  components: {
    MyHeader,
    MyFooter,
    ClassList,
  },
  // props
  props: {},
  // data
  data() {
    return {
      filter: "all",
    };
  },
  // computed
  computed: {
    ...mapState("accountStore", ["nickname"]),
    ...mapState("classStore", ["secondRecommendClassList"]),
    filteredList: {
      get() {
        if (this.filter == "online") {
          return this.secondRecommendClassList.filter(
            (item) => item.sidoId == 9
          );
        } else if (this.filter == "offline") {
          return this.secondRecommendClassList.filter(
            (item) => item.sidoId != 9
          );
        } else {
          return this.secondRecommendClassList;
        }
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    // 추천 클래스 목록 불러오기
    this.getSecondRecommendClass();
  },
  // methods
  methods: {
    ...mapActions("classStore", ["getSecondRecommendClass"]),
    // 전체 클래스 보여주기
    clickAll() {
      this.filter = "all";
    },
    // 온라인 필터 적용
    clickOnline() {
      this.filter = "online";
    },
    // 오프라인 필터 적용
    clickOffline() {
      this.filter = "offline";
    },
  },
};
</script>

<style scoped src="./SecondRecommendClass.css"></style>
