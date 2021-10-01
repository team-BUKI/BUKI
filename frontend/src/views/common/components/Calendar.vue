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
      <span class="title title-3">{{ year }}년 {{ month }}월</span>
      <div
        class="icon-wrapper"
        :class="{ invalid: isNoNext }"
        @click="nextMonth"
      >
        <i class="fas fa-chevron-right"></i>
      </div>
    </div>
    <div class="calendar-content">
      <div
        v-for="(day, index) in dayList"
        :key="day"
        class="title-5 day-list"
        :class="{ sunday: index == 0, saturday: index == 6 }"
      >
        {{ day }}
      </div>
      <div
        v-for="(item, index) in dateList"
        :key="index"
        class="title-5"
        :class="{
          invalid: item.invalid,
          selected: item.selected,
          empty: item.empty,
          'level-1': item.cnt === 1,
          'level-2': item.cnt === 2,
          'level-3': item.cnt === 3,
          'level-4': item.cnt >= 4,
        }"
        @click="changeDate(item)"
      >
        {{ item.date }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Swal from "sweetalert2";

export default {
  name: "Calendar",
  components: {},
  // props
  props: {
    diaryWriter: Number,
    year: String,
    month: String,
    day: String,
  },
  // data
  data() {
    return {
      dayList: ["일", "월", "화", "수", "목", "금", "토"],
      today: 0,
    };
  },
  // computed
  computed: {
    ...mapState("diaryStore", ["monthlyDiaryList"]),
    ...mapState(["userId"]),
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
    dateList: {
      get() {
        // 달력에 들어갈 날짜 배열 설정
        let dateList = [];
        // 선택한 날짜 셋팅
        const year = this.year * 1;
        const month = this.month * 1;
        const day = this.day * 1;
        // 이번 달 시작 요일
        const firstDay = new Date(year, month - 1, 1).getDay();
        // 이번 달 마지막 날짜
        const lastDate = new Date(year, month, 0).getDate();
        // 마지막 날짜 이후로 접근하면 마지막 날짜로 보내기
        if (lastDate < day) {
          this.$router.replace({
            path: this.$route.path,
            query: {
              date:
                `${year}-` +
                (month < 10 ? `0${month}` : month) +
                `-${lastDate}`,
            },
          });
          this.$router.go();
        }
        // 지난 달 마지막 날짜 구하기
        let lastYear = year;
        let lastMonth = month - 1;
        if (month == 1) {
          lastMonth = 12;
          lastYear--;
        }
        const prevLastDate = new Date(lastYear, lastMonth, 0).getDate();
        // 지난 달의 날짜를 먼저 넣어 달력 꽉 채우기
        let prevDay = prevLastDate - firstDay + 1;
        for (let i = 0; i < firstDay; i++) {
          dateList.push({ date: prevDay, invalid: true, empty: true });
          prevDay++;
        }
        // 이번 달 날짜 모두 넣기
        let num = 1;
        let today = new Date().getDate();
        while (num <= lastDate) {
          dateList.push({
            date: num,
            invalid: this.isNoNext && num > today,
            selected: num == day,
            cnt: this.monthlyDiaryList[num],
          });
          num++;
        }
        return dateList;
      },
      set() {},
    },
  },
  // lifecycle hook
  created() {
    this.validationMonth();
    this.getMonthlyDiary({
      userId: this.diaryWriter ? this.diaryWriter : this.userId,
      year: this.year,
      month: this.month,
    });
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["getMonthlyDiary"]),
    // 입력된 날짜가 유효한지 확인
    validationMonth() {
      // 오늘 날짜 구하기
      let today = new Date();
      let year = today.getFullYear();
      let month = today.getMonth() + 1;
      let day = today.getDate();
      // 2021년 이후부터 오늘까지만 가능
      if (
        this.year < 2021 ||
        this.year > year ||
        (this.year == year && this.month > month) ||
        (this.year == year && this.month == month && this.day > day)
      ) {
        Swal.fire({
          text: "접근할 수 없는 날짜입니다",
          showConfirmButton: false,
          timer: 1000,
        }).then(() => {
          // 오늘 날짜로 보내기
          if (month < 10) month = "0" + month;
          if (day < 10) day = "0" + day;
          this.$router.replace({
            path: this.$route.path,
            query: { date: `${year}-${month}-${day}` },
          });
          this.$router.go();
        });
      }
    },
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
        query: { date: `${year}-${month}-01`, id: this.diaryWriter },
      });
      this.$router.go();
    },
    // 다음 달로 이동
    nextMonth() {
      // 다음 달이 있을 때만 이동
      if (this.isNoNext) return;
      // 이동할 년, 월 구하기
      let year = this.year * 1;
      let month = this.month * 1;
      if (month == 12) {
        month = "01";
        year = year + 1;
      } else {
        month = month + 1;
        if (month < 10) month = "0" + month;
      }
      this.$router.replace({
        path: this.$route.path,
        query: { date: `${year}-${month}-01`, id: this.diaryWriter },
      });
      this.$router.go();
    },
    // 클릭한 날짜로 이동
    changeDate(item) {
      // 이번 달 날짜가 아니면 이동 안 함
      if (item.invalid) return;
      this.$router.replace({
        path: this.$route.path,
        query: {
          date:
            `${this.year}-${this.month}-` +
            (item.date < 10 ? `0${item.date}` : item.date),
          id: this.diaryWriter,
        },
      });
      this.$router.go();
    },
  },
};
</script>

<style scoped src="./Calendar.css"></style>
