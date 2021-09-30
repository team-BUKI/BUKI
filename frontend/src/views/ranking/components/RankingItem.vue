<template>
  <div>
    <div class="left-item" @click="clickUser">
      <span class="title title-4 ranking-idx">{{ idx + 4 }}</span>
      <div class="left-img" :class="'category-' + item.bigcategoryId">
        <img
          v-if="item.imageUrl"
          :src="item.imageUrl"
          alt="Main Character Image"
        />
      </div>
      <div class="ranking-info">
        <div class="rankig-name">
          <span class="title title-7">
            {{ item.secondcharacterNicknameAdj }}
            {{ item.secondcharacterNicknameNoun }}
          </span>
          <span class="title title-5" style="color: var(--main-lime)">
            {{ item.username }}
          </span>
        </div>
        <div class="ranking-score">
          <span class="title title-6">{{ item.totalExp }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RankingItem",
  components: {},
  // props
  props: {
    item: Object,
    idx: Number,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
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
  mounted() {},
  // methods
  methods: {
    clickUser() {
      localStorage.setItem("username", this.item.username);
      this.$router.push({
        name: "UserDiary",
        query: { date: this.today, id: this.item.id },
      });
    },
  },
};
</script>

<style scoped src="./RankingItem.css"></style>
