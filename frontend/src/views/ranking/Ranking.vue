<template>
  <div>
    <div class="container">
      <div class="title title-3">랭킹</div>
      <div v-if="rankingList && rankingList.length > 0" class="contents">
        <ranking-top3-list :list="rankingTop3List" @open="openDiary" />
        <ranking-list :list="rankingLeftList" @open="openDiary" />
      </div>
      <div v-else class="no-ranking title-5">랭킹을 찾을 수 없습니다</div>
      <my-footer :selected="'ranking'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import RankingTop3List from "./components/RankingTop3List.vue";
import RankingList from "./components/RankingList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "Ranking",
  components: {
    MyFooter,
    RankingTop3List,
    RankingList,
  },
  // props
  props: {},
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("rankingStore", ["rankingList"]),
    rankingTop3List: {
      get() {
        if (this.rankingList.length > 3) {
          return this.rankingList.slice(0, 3);
        } else {
          return this.rankingList;
        }
      },
      set() {},
    },
    rankingLeftList: {
      get() {
        if (this.rankingList.length > 3) {
          return this.rankingList.slice(3);
        } else {
          return null;
        }
      },
      set() {},
    },
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
  created() {
    this.getRankingData();
  },
  // methods
  methods: {
    ...mapActions("rankingStore", ["getRankingData"]),
    // 랭킹 목록 가져오기
    getRankingAllList() {
      // 유저가 3명 이상이면 배열 나누기
      if (this.rankingList.length > 3) {
        this.rankingTop3List = this.rankingList.slice(0, 3);
        this.rankingLeftList = this.rankingList.slice(3);
      } else {
        this.rankingTop3List = this.rankingList;
      }
    },
    // 클릭한 유저의 일기 보여주기
    openDiary(rankingItem) {
      this.$router.push({
        name: "UserDiary",
        query: { date: this.today, id: rankingItem.id },
      });
    },
  },
};
</script>

<style scoped src="./Ranking.css"></style>
