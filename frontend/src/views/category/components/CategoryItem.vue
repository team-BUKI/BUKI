<template>
  <div v-if="idx != 0">
    <div class="button-div" :class="'category-' + idx" @click="clickButton">
      <img
        :src="`https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/category/category-${idx}.png`"
      />
      <span class="title-4">{{ item }}</span>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "CategoryItem",
  components: {},
  // props
  props: {
    item: String,
    idx: Number,
    isModal: Boolean,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions("classStore", ["searchClassByCategory"]),
    // 카테고리로 클래스 검색
    clickButton() {
      if (this.isModal) {
        // 카테고리 검색 결과 바꾸기
        this.$router.replace({
          path: this.$route.path,
          query: { bigcategory: this.idx },
        });
        this.$router.go();
      } else {
        this.$router.push({
          name: "CategorySearch",
          query: { bigcategory: this.idx },
        });
      }
    },
  },
};
</script>

<style scoped src="./CategoryItem.css"></style>
