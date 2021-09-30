<template>
  <div>
    <div class="container">
      <div class="title title-3">랭킹</div>
      <div class="contents">
        <div>
          <ranking-top3-list :list="rankingTop3List" @open="openDiary" />
          <ranking-list :list="rankingLeftList" @open="openDiary" />
        </div>
      </div>
      <my-footer :selected="'ranking'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import RankingTop3List from "./components/RankingTop3List.vue";
import RankingList from "./components/RankingList.vue";
import { mapState, mapActions, mapGetters } from "vuex";

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
    return {
      rankingTop3List: [],
      rankingLeftList: [],
      rankingList: [],
    };
  },
  //created
  created() {
    // this.getRankingAllList();
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {
    this.getRankingAllList();
  },
  // methods
  methods: {
    ...mapGetters("rankingStore", ["getRankingList"]),
    ...mapActions("rankingStore", ["getRankingData"]),

    getRankingAllList() {
      this.getRankingData();
      this.rankingList = this.getRankingList();

      if (this.rankingList.length > 3) {
        this.rankingTop3List = this.rankingList.slice(0, 3);
        this.rankingLeftList = this.rankingList.slice(3);
        console.log(this.rankingTop3List);
        console.log(this.rankingLeftList);
      } else {
        this.rankingTop3List = this.rankingList;
      }
    },

    openDiary(rankingItem) {},
  },
};
</script>

<style scoped src="./Ranking.css"></style>
