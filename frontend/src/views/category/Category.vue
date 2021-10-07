<template>
  <div>
    <div class="container">
      <my-header />
      <div class="contents">
        <span class="title title-3">카테고리</span>
        <category-list />
      </div>
      <my-footer :selected="'category'" />
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import CategoryList from "./components/CategoryList.vue";

export default {
  name: "Category",
  components: {
    MyHeader,
    MyFooter,
    CategoryList,
  },
  // props
  props: {},
  // data
  data() {
    return {};
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./Category.css"></style>
