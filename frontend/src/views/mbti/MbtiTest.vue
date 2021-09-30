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
      <div class="contents">
        <div class="question">
          <p class="title title-2">Q{{ this.index + 1 }}</p>
          <span class="message title-3"> {{ this.data.question }} </span>
        </div>
        <div class="answer">
          <div
            class="button-answer message title-4"
            v-for="(item, index) in this.data.answer"
            :key="index"
            :item="item.content"
            @click="clickAnswer(index)"
          >
            {{ item.content }}
          </div>
        </div>
        <div class="progressBar"></div>
      </div>
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapState } from "vuex";

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
    };
  },
  // computed
  computed: {
    ...mapState("mbtiStore", ["mbtiTest"]),
  },
  // lifecycle hook
  mounted() {
    this.data = this.mbtiTest[this.index];
  },
  // methods
  methods: {
    clickAnswer(idx) {
      for (let i = 0; i < this.score.length; i++) {
        this.score[i] += this.data.answer[idx].value[i];
      }

      if (this.index < 4) {
        this.mbtiResult += this.data.answer[idx].mbti;
      }

      this.index += 1;

      if (this.index == 12) {
        console.log("오니");
        this.$router.push({ name: "MbtiResult" });
      }

      this.data = this.mbtiTest[this.index];
    },
  },
};
</script>

<style scoped src="./MbtiTest.css"></style>
