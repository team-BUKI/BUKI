<template>
  <div class="container">
    <div class="arrow-wrapper">
      <i class="fas fa-chevron-left" @click="goBack"></i>
    </div>
    <div class="title title-3">전체부캐</div>
    <div class="wrap">
      <!-- 캐릭터 swap section-->
      <character-section :type="'totalcharacter'"></character-section>
      <!-- 전체 캐릭터 section-->
      <div class="character-list-section">
        <div>
          <span class="title-4 title middle-title">부캐 목록</span>
        </div>
        <character-card
          v-for="(character, index) in getCharacterListInfo"
          :key="character.bigCategoryId"
          :info="character"
          :index="index"
        ></character-card>
        <!-- 통계 차트 -->
        <div style="margin: 20px 0">
          <canvas id="chart"></canvas>
        </div>
      </div>
    </div>
    <my-footer :selected="'mypage'" />
  </div>
</template>
<script>
import MyFooter from "@/views/common/MyFooter.vue";
import CharacterSection from "./components/CharacterSection.vue";
import CharacterCard from "./components/CharacterCard.vue";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);
import { mapState, mapGetters, mapActions } from "vuex";
export default {
  name: "TotalCharacter",
  components: {
    MyFooter,
    CharacterSection,
    CharacterCard,
  },
  computed: {
    ...mapState("characterStore", ["characterListInfo"]),
    ...mapGetters("characterStore", ["mySecondCharacter", "getCharacterListInfo"]),
  },
  created() {
    // 보유 부캐 가져오기
    this.getMySecondCharacters();
    //전체 캐릭터 리스트 가져오기
    this.getTotalCharacterList();
  },
  mounted() {
    let expData = [];
    for (let i = 0; i < this.characterListInfo.length - 1; i++) {
      expData[i] = this.characterListInfo[i + 1].exp;
    }
    const chart = document.getElementById("chart").getContext("2d");
    Chart.defaults.font.family = "DungGeunMo";
    Chart.defaults.color = "white";
    const radarChart = new Chart(chart, {
      type: "line",
      data: {
        labels: [
          "미술",
          "공예",
          "요리",
          "음악",
          "액티비티",
          "운동",
          "라이프",
          "사진,영상",
          "자기계발",
        ],
        datasets: [
          {
            label: "취미 분석",
            data: expData,
            backgroundColor: "rgba(210, 243, 199, 0.3)",
            borderColor: "rgb(165, 255, 133)",
            tension: 0.4,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        animation: {
          easing: "easeInOutQuad",
          duration: 2000,
        },

        tooltips: {
          titleFontFamily: "Open Sans",
          backgroundColor: "rgba(0,0,0,0.3)",
          titleFontColor: "red",
          caretSize: 5,
          cornerRadius: 2,
          xPadding: 10,
          yPadding: 10,
        },
      },
    });
  },
  methods: {
    ...mapActions("characterStore", ["getMySecondCharacters", "getTotalCharacterList"]),
    //뒤로가기
    goBack() {
      this.$router.push({ name: "MyPage" });
    },
  },
};
</script>
<style scoped src="./TotalCharacter.css"></style>
