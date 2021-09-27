<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <div class="search-div">
          <div class="icon-wrapper">
            <i class="fas fa-search"></i>
          </div>
          <input
            v-model="keyword"
            class="title-5"
            type="text"
            placeholder="검색어를 입력하세요"
            @keypress.enter="enterKeyword"
          />
        </div>
        <span class="text-button title-5" @click="$router.go(-1)">취소</span>
      </div>
      <div class="search-result">
        <div class="title-div">
          <span class="title title-4">{{ prevKeyword }} 검색 결과</span>
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
      <infinite-loading @infinite="searchKeyword" spinner="waveDots">
        <div slot="no-more" class="infinite-message title-5">
          더 이상 클래스가 없습니다
        </div>
        <div slot="no-results" class="infinite-message title-5">
          검색된 클래스가 없습니다
        </div>
      </infinite-loading>
    </div>
  </div>
</template>

<script>
import ClassList from "@/views/common/components/ClassList.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "Search",
  components: {
    ClassList,
  },
  // props
  props: {},
  // data
  data() {
    return {
      prevKeyword: this.$route.query.keyword,
      keyword: this.$route.query.keyword,
      keywordList: [],
      filter: "all",
      filteredList: [],
      pageId: 0,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["searchClassList"]),
  },
  // lifecycle hook
  mounted() {
    // localStorage에서 최근 검색어 불러오기
    let keywordList = localStorage.getItem("keywordList");
    if (keywordList) {
      this.keywordList = JSON.parse(keywordList);
    }
    // 클래스 검색 결과 초기화
    this.setSearchClassList([]);
  },
  // methods
  methods: {
    ...mapActions("classStore", ["searchClassByKeyword", "setSearchClassList"]),
    // 입력된 단어를 검색어로 입력
    enterKeyword() {
      // 1글자 이상부터 검색 가능
      this.keyword = this.keyword.trim();
      if (this.keyword.length == 0) return;
      // 목록에 이미 있으면 기존 위치 삭제
      let idx = this.keywordList.indexOf(this.keyword);
      if (idx >= 0) {
        this.keywordList.splice(idx, 1);
      }
      // 최근 검색어는 최대 5개까지 저장
      if (this.keywordList.length == 5) {
        this.keywordList.pop();
      }
      // 최근 검색어 목록에 입력된 검색어 추가
      this.keywordList.unshift(this.keyword);
      localStorage.setItem("keywordList", JSON.stringify(this.keywordList));
      // 키워드 검색 결과 바꾸기
      this.$router.replace({
        path: this.$route.path,
        query: { keyword: this.keyword },
      });
      this.$router.go();
    },
    // 검색어로 클래스 검색하기
    searchKeyword($state) {
      let data = { id: this.pageId, keyword: this.keyword, state: $state };
      this.searchClassByKeyword(data);
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
      this.filteredList = this.searchClassList;
    },
    // 온라인 필터 적용
    clickOnline() {
      this.filter = "online";
      this.filteredList = this.searchClassList.filter(
        (item) => item.sidoId == 9
      );
    },
    // 오프라인 필터 적용
    clickOffline() {
      this.filter = "offline";
      this.filteredList = this.searchClassList.filter(
        (item) => item.sidoId != 9
      );
    },
  },
};
</script>

<style scoped src="./Search.css"></style>
