<template>
  <div class="character-section">
    <div class="character-title-section">
      <span v-if="!clickSecondNicknameAdj" class="title-4">{{
        this.secondNicknameAdj
      }}</span>
      <div v-else class="slots-wrap">
        <div class="slots">
          <div
            v-for="item in this.secondNicknameAdjList"
            :key="item"
            class="slot title-4 black-title"
          >
            {{ item }}
          </div>
        </div>
      </div>
      <span>
        <span class="title-4 represent-character">{{
          this.getRepresentCharacterName
        }}</span>
        <span class="title-4">,</span>
      </span>
      <span class="title-4">{{ this.getNickname }}님</span>
    </div>
    <div
      v-if="
        this.mySecondCharacter != null && this.mySecondCharacter.length == 0
      "
      class="no-character"
    >
      <span class="title-4"
        >아직 획득한 부캐가 없습니다.<br />
        일기를 작성해서 부캐를 얻어보세요!</span
      >
      <div class="diary-button" @click="clickGoDiary()">
        <span class="title-5 title">일기 작성하러 가기</span>
      </div>
    </div>
    <div v-else>
      <div class="character-wrapper">
        <!-- 레벨-->
        <div>
          <img src="@/assets/images/heart.png" class="heart-icon" />
          <div class="character-level">
            <span class="title-6">Lv.{{ this.currentCharacter.level }}</span>
          </div>
        </div>
        <!-- 이미지 -->
        <img class="character-img" :src="this.currentCharacter.image" />
        <!-- 이름 -->
        <div class="character-name-wrapper">
          <img
            v-if="this.currentCharacter.represent"
            class="medal"
            src="@/assets/images/medal.png"
          />
          <div class="character-name">
            <span class="title-6"
              >{{ this.currentCharacter.bigcategoryName }}
              {{ this.currentCharacter.name }}</span
            >
          </div>
        </div>
        <div class="elipse" :style="getElipseColor"></div>
        <span class="background-light" :style="getColor"></span>
      </div>
    </div>
    <div class="arrow-section" v-if="this.mySecondCharacter.length != 0">
      <i
        class="fas fa-chevron-left fa-2x arrow"
        @click="clickLeft"
        :class="{ 'arrow-inactive': this.mySecondCharacter.length == 1 }"
      ></i>
      <i
        class="fas fa-chevron-right fa-2x arrow"
        @click="clickRight"
        :class="{ 'arrow-inactive': this.mySecondCharacter.length == 1 }"
      ></i>
    </div>
    <div class="button-wrapper">
      <div
        v-if="type == 'mypage'"
        class="total-character-button"
        @click="clickTotalCharacter"
      >
        <span class="title-5" style="color: black">전제 부캐 보러 가기</span>
      </div>
      <div v-else-if="type == 'totalcharacter'" class="total-character-button">
        <span
          class="title-5"
          style="color: black"
          @click="updateSecondNicknameAdj"
          >타이틀 선택</span
        >
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions, mapState } from "vuex";
export default {
  name: "CharacterSection",
  data() {
    return {
      currentIdx: 0,
      level: 0,
      name: "",
      represent: false,
      clickSecondNicknameAdj: false,
    };
  },
  props: {
    type: String,
  },
  mounted() {
    // 보유한 부캐 가져오기
    this.getMySecondCharacters();
    // 별칭 가져오기
    this.getSecondNicknameAdj();
  },
  computed: {
    ...mapState("accountStore", ["secondNicknameAdjList"]),
    ...mapGetters("accountStore", ["getNickname", "secondNicknameAdj"]),
    ...mapGetters("characterStore", [
      "getRepresentCharacterName",
      "getRepresentCharacter",
      "mySecondCharacter",
      "currentCharacter",
    ]),
    ...mapState("characterStore", [
      "mySecondCharacter",
      "getRepresentCharacterIdx",
    ]),
    ...mapState("accountStore", ["secondNicknameAdjList"]),
    getElipseColor() {
      let color =
        "var(--category-" + this.getRepresentCharacter.bigcategoryId + ")";
      let ret = `background-color: ${color};  border: 1px solid ${color};
        box-shadow: 0px 0px 4px 2px ${color}`;

      return ret;
    },
    getColor() {
      let color =
        "var(--category-" + this.getRepresentCharacter.bigcategoryId + ")";
      return `background: radial-gradient(50% 50% at 50% 50%, ${color} 0%, rgba(255, 241, 215, 0.5) 100%);
;`;
    },
  },
  methods: {
    ...mapActions("characterStore", [
      "getMySecondCharacters",
      "updateRepresentCharacter",
    ]),
    ...mapActions("accountStore", [
      "getSecondNicknameAdj",
      "setSecondNicknameAdj",
    ]),
    // 왼쪽 idx 캐릭터 보기
    clickLeft() {
      if (this.currentIdx == 0) {
        this.currentIdx = this.mySecondCharacter.length - 1;
      } else {
        this.currentIdx -= 1;
      }
      this.updateRepresentCharacter(this.currentIdx);
    },
    // 오른쪽 idx 캐릭터 보기
    clickRight() {
      if (this.currentIdx == this.mySecondCharacter.length - 1) {
        this.currentIdx = 0;
      } else {
        this.currentIdx += 1;
      }
      this.updateRepresentCharacter(this.currentIdx);
    },
    // 일기 쓰러 가기
    clickGoDiary() {
      this.$router.push({ name: "Diary" });
    },
    clickTotalCharacter() {
      this.$router.push({ name: "TotalCharacter" });
    },
    // 부캐 닉네임 형용사 변경
    updateSecondNicknameAdj() {
      // 타이틀 randomize
      this.shuffle();
      this.clickSecondNicknameAdj = true;
      let adj = this.secondNicknameAdjList[30];
      this.setSecondNicknameAdj(adj);
      setTimeout(() => {
        this.clickSecondNicknameAdj = false;
      }, 3000);
    },
    // secondNicknameAdjList 섞기
    shuffle() {
      var j, x, i;
      for (i = this.secondNicknameAdjList.length; i; i -= 1) {
        j = Math.floor(Math.random() * this.secondNicknameAdjList.length);
        x = this.secondNicknameAdjList[i - 1];
        this.secondNicknameAdjList[i - 1] = this.secondNicknameAdjList[j];
        this.secondNicknameAdjList[j] = x;
      }
    },
  },
};
</script>
<style scoped src="./CharacterSection.css"></style>
