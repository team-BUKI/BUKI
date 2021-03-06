<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <div
          class="type-button title title-4"
          @click="$router.push({ name: 'Diary' })"
        >
          피드
        </div>
        <div class="type-button active title title-4">캘린더</div>
      </div>
      <calendar :year="year" :month="month" :day="day" />
      <div class="contents">
        <div class="date-title">
          <span class="title title-3">{{ dateStr }}</span>
          <span class="title-6">{{ diaryList.length }}개의 글이 있습니다</span>
        </div>
        <diary-list
          v-if="diaryList && diaryList.length > 0"
          :diaryList="diaryList"
          @openModal="openModal"
        />
        <div v-else class="no-content title-5">작성한 글이 없습니다</div>
      </div>
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
import Calendar from "@/views/common/components/Calendar.vue";
import DiaryList from "./components/DiaryList.vue";
import ConfirmCloseModal from "@/views/common/components/ConfirmCloseModal.vue";
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

export default {
  name: "DiaryCalendar",
  components: {
    MyFooter,
    Calendar,
    DiaryList,
    ConfirmCloseModal,
  },
  // props
  props: {},
  // data
  data() {
    return {
      isOpenModal: false,
      diaryId: 0,
      date: this.$route.query.date,
    };
  },
  // computed
  computed: {
    ...mapState("diaryStore", ["diaryList"]),
    ...mapState(["userId"]),
    year: {
      get() {
        return this.date.substring(0, 4);
      },
      set() {},
    },
    month: {
      get() {
        return this.date.substring(5, 7);
      },
      set() {},
    },
    day: {
      get() {
        return this.date.substring(8, 10);
      },
      set() {},
    },
    dateStr: {
      get() {
        return this.month + "월 " + this.day + "일";
      },
      set() {},
    },
  },
  // lifecycle hook
  created() {
    // 날짜 형식이 유효한지 검사
    var reg = RegExp(/^(\d{4})-(0[1-9]|1[012])-([012][0-9]|3[01])$/);
    if (!reg.test(this.date)) {
      Swal.fire({
        text: "유효한 날짜 형식이 아닙니다",
        showConfirmButton: false,
        timer: 1000,
      }).then(() => {
        this.$router.go(-1);
      });
    } else {
      this.getDiaryList();
    }
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["getDailyDiary", "deleteDiary"]),
    // 일기 목록 가져오기
    getDiaryList() {
      let data = { userId: this.userId, date: this.date };
      this.getDailyDiary(data);
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

<style scoped src="./DiaryCalendar.css"></style>
