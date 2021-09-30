<template>
  <div>
    <div class="top3-item" @click="clickUser">
      <div class="top3-img" :class="'category-' + item.bigcategoryId">
        <img
          v-if="item.imageUrl"
          :src="item.imageUrl"
          alt="Main Character Image"
        />
      </div>
      <span class="title title-4">{{ idx + 1 }}</span>
      <div class="name">
        <p class="title-7 title">
          {{ item.secondcharacterNicknameAdj }}
          {{ item.secondcharacterNicknameNoun }}
        </p>
        <span class="title title-5" style="color: var(--main-lime)">{{
          item.username
        }}</span>
      </div>
      <div>
        <span class="title title6">{{ item.totalExp }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RankingTop3Item",
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

<style scoped src="./RankingTop3Item.css"></style>
