<template>
  <div>
    <div class="container">
      <div class="header-wrapper">
        <img
          class="logo"
          src="@/assets/images/logo.png"
          @click="$router.push({ name: 'Home' })"
        />
        <span class="title title-3">MBTI 테스트</span>
      </div>
      <transition name="slide">
        <div v-if="isShow" class="contents">
          <div class="question">
            <div class="mbti-idx title title-2">Q{{ this.index + 1 }}.</div>
            <div class="message title-3" v-html="this.data.question"></div>
          </div>
          <div class="answer">
            <div
              class="answer-button title-5"
              v-for="(item, index) in this.data.answer"
              :key="index"
              v-text="item.content"
              @click="clickAnswer(index)"
            ></div>
          </div>
          <div class="progress-bar">
            <div class="progress-bg">
              <div class="progress" :class="'progress-' + index"></div>
            </div>
            <div class="progress-text title-4">{{ this.index + 1 }} / 12</div>
          </div>
        </div>
      </transition>
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "Mbti",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return {
      index: 0,
      data: {},
      score: [4, 4, 4, 4, 4, 4, 4, 4, 4],
      mbtiResult: "",
      isShow: false,
    };
  },
  // computed
  computed: {
    ...mapState("mbtiStore", ["mbtiTest"]),
  },
  // lifecycle hook
  mounted() {
    this.data = this.mbtiTest[this.index];
    this.isShow = true;
  },
  // methods
  methods: {
    ...mapActions("mbtiStore", ["getRecommendCategory", "setMbti"]),
    clickAnswer(idx) {
      // 문항별 점수 합산
      for (let i = 0; i < this.score.length; i++) {
        this.score[i] += this.data.answer[idx].value[i];
      }
      // MBTI 문항일 경우
      if (this.index < 4) {
        this.mbtiResult += this.data.answer[idx].mbti;
      }
      // 12문항을 완료하면 종료
      this.index += 1;
      if (this.index == 12) {
        this.getRecommendCategory({ survey_data: this.score });
        this.setMbti(this.mbtiResult);
        this.$router.push({ name: "MbtiResult" });
      }
      this.data = this.mbtiTest[this.index];
      // 애니메이션 효과 적용
      this.isShow = false;
      setTimeout(() => {
        this.isShow = true;
      }, 100);
    },
  },
};
</script>

<style scoped src="./MbtiTest.css"></style>
