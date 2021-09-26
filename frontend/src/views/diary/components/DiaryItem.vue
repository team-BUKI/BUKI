<template>
  <div class="diary-item">
    <div class="diary-info">
      <div class="info-wrapper">
        <div class="category-div" :class="'category-' + item.bigcategoryId">
          <span class="title-7">{{ item.smallcategoryName }}</span>
        </div>
        <span class="title-6 sub-color">{{ dateStr }}</span>
        <div v-if="!item.share" class="icon-wrapper small">
          <i class="fas fa-lock"></i>
        </div>
      </div>
      <div class="icon-wrapper small">
        <i class="fas fa-ellipsis-h"></i>
      </div>
    </div>
    <img
      v-if="item.image"
      class="diary-image"
      :src="item.image"
      alt="Diary Image"
    />
    <div class="diary-content body-2">{{ item.content }}</div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "DiaryItem",
  components: {},
  // props
  props: {
    item: Object,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("classStore", ["smallcategory"]),
    smallcategoryName: {
      get() {
        return this.smallcategory[this.item.smallcategoryId];
      },
      set() {},
    },
    dateStr: {
      get() {
        let date = this.item.date;
        let year = date.substring(0, 4);
        let month = date.substring(5, 7);
        let day = date.substring(8, 10);
        return year + "년 " + month + "월 " + day + "일";
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./DiaryItem.css"></style>
