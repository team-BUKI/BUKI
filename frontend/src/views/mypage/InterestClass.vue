<template>
  <div>
    <div class="container">
      <my-header />
      <div class="contents">
        <div class="title-div">
          <span class="title title-3 text-ellipsis title-nickname">{{ nickname }}</span
          ><span class="title title-3">님의 관심 클래스</span>
        </div>
        <div class="button-div">
          <div class="button-3 title-7" :class="{ active: this.filter == 'all' }" @click="clickAll">
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
      <infinite-loading @infinite="getInterestList" spinner="waveDots" class="infinite-div">
        <div slot="no-more" class="infinite-message title-5">더 이상 클래스가 없습니다</div>
        <div slot="no-results" class="infinite-message title-5">관심 클래스가 없습니다</div>
      </infinite-loading>
      <my-footer :selected="'mypage'" />
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "@/views/common/components/ClassList.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "InterestClass",
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
      pageId: 0,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["interestClassList"]),
    ...mapState("accountStore", ["nickname"]),
    filteredList: {
      get() {
        if (this.filter == "online") {
          return this.interestClassList.filter((item) => item.sidoId == 9);
        } else if (this.filter == "offline") {
          return this.interestClassList.filter((item) => item.sidoId != 9);
        } else {
          return this.interestClassList;
        }
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    // 관심 클래스 목록 초기화
    this.setInterestClassList([]);
  },
  // methods
  methods: {
    ...mapActions("classStore", ["getInterestClass", "setInterestClassList"]),
    // 관심 클래스 목록 가져오기
    getInterestList($state) {
      let data = { id: this.pageId, state: $state };
      this.getInterestClass(data);
      this.pageId++;
      if (this.filter == "all") {
        this.clickAll();
      } else if (this.filter == "online") {
        this.clickOnline();
      } else if (this.filter == "offline") {
        this.clickOffline();
      }
    },
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

<style scoped src="./InterestClass.css"></style>
