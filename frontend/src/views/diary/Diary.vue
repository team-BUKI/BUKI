<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <div class="type-button active title title-4">피드</div>
        <div
          class="type-button title title-4"
          @click="
            $router.push({
              name: 'DiaryCalendar',
              query: { date: today },
            })
          "
        >
          캘린더
        </div>
      </div>
      <div class="contents">
        <diary-list :diaryList="diaryList" @openModal="openModal" />
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
      <confirm-close-modal
        v-if="isOpenModal"
        :title="'정말 삭제하시겠습니까?'"
        :content="'기록이 삭제되면 다시 되돌릴 수 없어요'"
        :confirmText="'삭제'"
        :closeText="'취소'"
        @confirm="deleteThisDiary"
        @close="closeModal"
      />
      <my-footer :selected="'diary'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import DiaryList from "./components/DiaryList.vue";
import ConfirmCloseModal from "@/views/common/components/ConfirmCloseModal.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "Diary",
  components: {
    MyFooter,
    DiaryList,
    ConfirmCloseModal,
  },
  // props
  props: {},
  // data
  data() {
    return {
      pageId: 0,
      isOpenModal: false,
      diaryId: 0,
    };
  },
  // computed
  computed: {
    ...mapState(["userId"]),
    ...mapState("diaryStore", ["diaryList"]),
    today: {
      get() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() + 1;
        let day = today.getDate();
        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;
        return `${year}-${month}-${day}`;
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    // 일기 목록 초기화
    this.setDiaryList([]);
  },
  destroyed() {
    // 일기 목록 초기화
    this.setDiaryList([]);
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["getAllDiary", "setDiaryList", "deleteDiary"]),
    // 일기 목록 가져오기
    getDiaryList($state) {
      let data = { pageId: this.pageId, state: $state };
      this.getAllDiary(data);
      this.pageId++;
    },
    // 일기 삭제 모달창 열기
    openModal(id) {
      this.diaryId = id; // 해당 일기ID
      this.isOpenModal = true;
    },
    // 일기 삭제 모달창 닫기
    closeModal() {
      this.isOpenModal = false;
    },
    // 해당 일기 삭제하기
    deleteThisDiary() {
      this.deleteDiary(this.diaryId);
    },
  },
};
</script>

<style scoped src="./Diary.css"></style>
