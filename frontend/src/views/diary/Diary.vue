<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <div
          class="type-button title title-4"
          :class="{ active: !this.isCalendar }"
          @click="changeType"
        >
          피드
        </div>
        <div
          class="type-button title title-4"
          :class="{ active: this.isCalendar }"
          @click="changeType"
        >
          캘린더
        </div>
      </div>
      <div class="contents">
        <diary-list :diaryList="diaryList" />
      </div>
      <infinite-loading
        @infinite="getDiaryList"
        spinner="waveDots"
        class="infinite-div"
      >
        <div slot="no-more" class="infinite-message title-5">
          더 이상 일기가 없습니다
        </div>
        <div slot="no-results" class="infinite-message title-5">
          등록된 일기가 없습니다
        </div>
      </infinite-loading>
      <div class="floating-button-div">
        <div
          class="floating-button"
          @click="$router.push({ name: 'DiaryWrite' })"
        >
          <div class="icon-wrapper">
            <i class="fas fa-pen-alt"></i>
          </div>
        </div>
      </div>
      <my-footer :selected="'diary'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import DiaryList from "./components/DiaryList.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "Diary",
  components: {
    MyFooter,
    DiaryList,
  },
  // props
  props: {},
  // data
  data() {
    return {
      pageId: 0,
      isCalendar: false,
    };
  },
  // computed
  computed: {
    ...mapState("diaryStore", ["diaryList"]),
    ...mapState("accountStore", ["id"]),
  },
  // lifecycle hook
  mounted() {
    // 일기 목록 초기화
    this.setDiaryList([]);
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["getAllDiary", "setDiaryList"]),
    // 일기 목록 가져오기
    getDiaryList($state) {
      let data = { id: this.pageId, userId: this.id, state: $state };
      this.getAllDiary(data);
      this.pageId++;
    },
    // 일기 보여주는 유형 바꾸기
    changeType() {},
  },
};
</script>

<style scoped src="./Diary.css"></style>
