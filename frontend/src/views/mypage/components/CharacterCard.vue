<template>
  <div class="card-container" v-if="index > 0" :style="getColor">
    <div class="card-wrapper">
      <img class="card-image" :src="info.image" />
      <div class="card-info">
        <div>
          <span class="title-6 black-title">{{ info.bigcategoryName }} </span>
          <span class="title-6 black-title">{{ info.characterName }}</span>
        </div>
        <div class="card-info-wrapper">
          <span class="title-6 black-title">{{ info.characterInfo }}</span>
        </div>
        <div class="card-image-wrapper">
          <div class="level-exp-wrapper">
            <img src="@/assets/images/heart.png" />
            <div class="level-exp-box">
              <span class="title-6">LEVEL</span>
            </div>
            <span class="title-4 black-title">{{ info.level }}</span>
          </div>
          <div class="level-exp-wrapper">
            <img src="@/assets/images/diamond.png" />
            <div class="level-exp-box">
              <span class="title-6">EXP</span>
            </div>
            <span class="exp-wrapper">
              <span class="title-4 black-title">{{ info.exp }}</span>
              <span class="title-4 black-title"> / </span>
              <span class="title-4 black-title"> {{ getExp }} </span>
            </span>
          </div>
        </div>
        <!-- progress bar -->
        <!-- <div class="progress-container">
          <div class="progress progress-moved">
            <div class="progress-bar" ref="progressbBar"></div>
          </div>
        </div> -->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "CharacterCard",
  props: {
    info: Object,
    index: Number,
  },
  data() {
    return {
      exp: [1000, 2000, 4500, 7000, "∞"],
    };
  },
  mounted() {
    // console.log("sdfsdfsdfsdf");
    // setTimeout(this.calculateProgress(), 500);
  },
  computed: {
    getColor() {
      return `background-color: var(--category-${this.index})`;
    },
    getExp() {
      if (this.info.level < 2) {
        return this.exp[0];
      } else if (this.info.level < 3) {
        return this.exp[1];
      } else if (this.info.level < 4) {
        return this.exp[2];
      } else if (this.info.level[3]) {
        return this.exp[3];
      } else {
        return this.exp[4];
      }
    },
  },
  methods: {
    calculateProgress() {
      let denom = 1;
      if (this.info.level != null) {
        if (this.info.level < 2) {
          this.denom = this.exp[0];
        } else if (this.info.level < 3) {
          this.denom = this.exp[1];
        } else if (this.info.level < 4) {
          this.denom = this.exp[2];
        } else if (this.info.level[3]) {
          this.denom = this.exp[3];
        } else {
          this.denom = this.exp[4];
        }
        let width = (this.info.exp / this.denom) * 100;
        // console.log(width * 100);
        this.$refs.progressbBar.style.width = `${width}%`;
        // this.$refs.nativeView.animate({
        //   translate: {
        //     // width:
        //   },
        // });
      }
    },
  },
};
</script>
<style scoped src="./CharacterCard.css"></style>
