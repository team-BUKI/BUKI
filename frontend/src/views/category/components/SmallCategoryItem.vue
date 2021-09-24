<template>
  <div>
    <span class="text-button title-5" @click="clickButton">{{
      item.name
    }}</span>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "SmallCategoryItem",
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
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapActions("classStore", ["searchClassByCategory"]),
    // 세부 카테고리로 클래스 검색
    clickButton() {
      // 카테고리 검색 결과 바꾸기
      this.$router.replace({
        path: this.$route.path,
        query: {
          bigcategory: this.$route.query.bigcategory,
          smallcategory: this.item.id,
        },
      });
      this.$router.go();
    },
  },
};
</script>

<style scoped src="./SmallCategoryItem.css"></style>
