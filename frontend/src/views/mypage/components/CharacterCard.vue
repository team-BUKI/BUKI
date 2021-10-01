<template>
  <div
    class="card-container"
    v-if="index > 0"
    :style="getColor"
    :class="{ 'card-container-filter': !info.obtain, 'card-container-represent': info.represent }"
  >
    <img v-if="isRepresent" src="@/assets/images/medal.png" class="represnt-medal" />
    <div class="card-wrapper">
      <img class="card-image" :src="info.image" />
      <div class="card-info">
        <div>
          <span class="title-6 black-title">{{ info.bigcategoryName }} </span>
          <span class="title-6 black-title">{{ info.characterName }}</span>
        </div>
        <div class="card-info-wrapper character-info">
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
        <div class="progress">
          <div class="progress-bar" ref="progressbBar"></div>
        </div>
        <!-- 대표 부캐 -->
        <div v-if="info.obtain" class="set-represent-button">
          <span class="title-6 black-title" @click="clickSetRepresentButton">대표 부캐</span>
        </div>
        <div v-else class="set-represent-inactive-button">
          <span class="title-6 black-title">대표 부캐</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "CharacterCard",
  props: {
    info: Object,
    index: Number,
  },
  data() {
    return {
      exp: [1000, 2000, 4500, 7000, "∞"],
      isRepresent: false,
    };
  },
  watch: {
    isRepresent: {
      handler() {
        if (this.getCharacterListInfo[this.index].represent) {
          this.isRepresent = true;
        } else if (this.getCharacterListInfo[this.index].represent) {
          this.isRepresent = false;
        }
      },
    },
  },
  created() {
    if (this.getCharacterListInfo[this.index].represent) {
      this.isRepresent = true;
    } else if (this.getCharacterListInfo[this.index].represent) {
      this.isRepresent = false;
    }
    this.calculateProgress();
  },
  computed: {
    ...mapGetters("characterStore", ["getRepresentCharacter", "getCharacterListInfo"]),
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
    ...mapActions("characterStore", ["setRepresentCharacter"]),
    calculateProgress() {
      let denom = 1;
      if (this.info.level != null) {
        if (this.info.level < 2) {
          denom = this.exp[0];
        } else if (this.info.level < 3) {
          denom = this.exp[1];
        } else if (this.info.level < 4) {
          denom = this.exp[2];
        } else if (this.info.level[3]) {
          denom = this.exp[3];
        } else {
          denom = 7000;
        }
        let width = (this.info.exp / denom) * 100;

        var _width = 1;
        setInterval(() => {
          if (_width >= width) {
            clearInterval();
          } else {
            _width++;
            this.$refs.progressbBar.style.width = `${_width}%`;
          }
        }, 10);
      }
    },
    clickSetRepresentButton() {
      let beforeRepresentId = this.getRepresentCharacter.id;
      console.log("before:", beforeRepresentId, ", after:", this.info.id);
      let data = {
        prevId: beforeRepresentId,
        afterId: this.info.id,
      };
      this.setRepresentCharacter(data);
    },
  },
};
</script>
<style scoped src="./CharacterCard.css"></style>
