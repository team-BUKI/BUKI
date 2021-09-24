<template>
  <div class="container">
    <my-header />
    <div class="contents">
      <div class="title-div">
        <span class="title title-3 text-ellipsis title-nickname">{{
          nickname
        }}</span
        ><span class="title title-3">님의 관심 클래스</span>
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
    <infinite-loading
      @infinite="getInterestList"
      spinner="waveDots"
      class="infinite-div"
    >
      <div slot="no-more" class="infinite-message title-5">
        더 이상 클래스가 없습니다
      </div>
      <div slot="no-results" class="infinite-message title-5">
        관심 클래스가 없습니다
      </div>
    </infinite-loading>
    <my-footer :selected="'home'" />
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "@/views/common/components/ClassList.vue";
import { mapState, mapActions, mapMutations } from "vuex";

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
      filteredList: [],
      pageId: 0,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["interestClassList", "nickname"]),
  },
  // lifecycle hook
  mounted() {
    // 관심 클래스 목록 초기화
    this.SET_INTEREST_CLASS_LIST([]);
  },
  // methods
  methods: {
    ...mapActions("classStore", ["getInterestClass"]),
    ...mapMutations("classStore", ["SET_INTEREST_CLASS_LIST"]),
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
      this.filteredList = this.interestClassList;
    },
    // 온라인 필터 적용
    clickOnline() {
      this.filter = "online";
      this.filteredList = this.interestClassList.filter(
        (item) => item.sidoId == 9
      );
    },
    // 오프라인 필터 적용
    clickOffline() {
      this.filter = "offline";
      this.filteredList = this.interestClassList.filter(
        (item) => item.sidoId != 9
      );
    },
  },
};
</script>

<style scoped src="./InterestClass.css"></style>
