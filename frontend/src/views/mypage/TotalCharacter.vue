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
      </div>
    </div>
    <my-footer :selected="'mypage'" />
  </div>
</template>
<script>
import MyFooter from "@/views/common/MyFooter.vue";
import CharacterSection from "./components/CharacterSection.vue";
import CharacterCard from "./components/CharacterCard.vue";

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
    ...mapGetters("characterStore", ["getCharacterListInfo"]),
  },
  created() {
    //전체 캐릭터 리스트 가져오기
    this.getTotalCharacterList();
    console.log(this.getCharacterListInfo);
  },
  methods: {
    ...mapActions("characterStore", ["getTotalCharacterList"]),
    //뒤로가기
    goBack() {
      this.$router.push({ name: "MyPage" });
    },
  },
};
</script>
<style scoped src="./TotalCharacter.css"></style>
