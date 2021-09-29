<template>
  <div class="calendar">
    <div class="calendar-title">
      <div
        class="icon-wrapper"
        :class="{ invalid: isNoPrev }"
        @click="prevMonth"
      >
        <i class="fas fa-chevron-left"></i>
      </div>
      <span class="title-3">{{ year }}년 {{ month }}월</span>
      <div
        class="icon-wrapper"
        :class="{ invalid: isNoNext }"
        @click="nextMonth"
      >
        <i class="fas fa-chevron-right"></i>
      </div>
    </div>
    <div class="calendar-content"></div>
  </div>
</template>

<script>
export default {
  name: "Calendar",
  components: {},
  // props
  props: {
    year: String,
    month: String,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    isNoPrev: {
      get() {
        if (
          this.year * 1 > 2021 ||
          (this.year * 1 == 2021 && this.month * 1 == 1)
        ) {
          return true;
        } else {
          return false;
        }
      },
      set() {},
    },
    isNoNext: {
      get() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() + 1;
        if (
          this.year * 1 > year ||
          (this.year * 1 == year && this.month * 1 >= month)
        ) {
          return true;
        } else {
          return false;
        }
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    // 이전 달로 이동
    prevMonth() {
      // 이전 달이 있을 때만 이동
      if (this.isNoPrev) return;
      // 이동할 년, 월 구하기
      let year = this.year * 1;
      let month = this.month * 1;
      if (month == 1) {
        month = "12";
        year = year - 1;
      } else {
        month = month - 1;
        if (month < 10) month = "0" + month;
      }
      this.$router.replace({
        path: this.$route.path,
        query: { date: year + "-" + month + "-01" },
      });
      this.$router.go();
    },
    // 다음 달로 이동
    nextMonth() {
      // 다음 달이 있을 때만 이동
      if (this.isNoNext) return;
      // 이동할 년, 월 구하기
      let year = this.year;
      let month = this.month;
      if (month == 12) {
        month = "01";
        year = year + 1;
      } else {
        month = month + 1;
        if (month < 10) month = "0" + month;
      }
      this.$router.replace({
        path: this.$route.path,
        query: { date: year + "-" + month + "-01" },
      });
      this.$router.go();
    },
  },
};
</script>

<style scoped src="./Calendar.css"></style>
