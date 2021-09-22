<template>
  <div class="modal-container">
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
          @keypress.enter="searchKeyword"
        />
      </div>
      <span class="text-button title-5" @click="$router.go(-1)">취소</span>
    </div>
    <div class="title-wrapper">
      <span class="title title-4">최근 검색어</span>
      <span class="text-button title-5" @click="clickDelete">전체 삭제</span>
    </div>
    <div class="search-list">
      <span
        class="title-5"
        v-for="(keyword, index) in keywordList"
        :key="index"
        @click="clickKeyword"
        >{{ keyword }}</span
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "Search",
  components: {},
  // props
  props: {},
  // data
  data() {
    return {
      keyword: "",
      keywordList: [],
    };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {
    // localStorage에서 최근 검색어 불러오기
    let keywordList = localStorage.getItem("keywordList");
    if (keywordList) {
      this.keywordList = JSON.parse(keywordList);
    }
  },
  // methods
  methods: {
    // 입력된 검색어로 클래스 검색
    searchKeyword() {
      // 목록에 이미 있으면 기존 위치 삭제
      this.keywordList.splice(this.keywordList.indexOf(this.keyword), 1);
      // 최근 검색어는 최대 5개까지 저장
      if (this.keywordList.length == 5) {
        this.keywordList.pop();
      }
      // 최근 검색어 목록에 입력된 검색어 추가
      this.keywordList.unshift(this.keyword);
      localStorage.setItem("keywordList", JSON.stringify(this.keywordList));
      // 키워드 검색 후 결과 페이지로 이동
      console.log("검색어 : " + this.keyword);
    },
    // 최근 검색어 전체 삭제
    clickDelete() {
      this.keywordList = [];
      localStorage.removeItem("keywordList");
    },
    // 최근 검색어로 클래스 검색
    clickKeyword(event) {
      // 클릭한 검색어를 키워드로 입력
      this.keyword = event.target.innerText;
      // 최근 검색어 목록에서 기존 위치 삭제
      this.keywordList.splice(this.keywordList.indexOf(this.keyword), 1);
      // 해당 검색어로 클래스 검색
      this.searchKeyword();
    },
  },
};
</script>

<style scoped src="./Search.css"></style>
