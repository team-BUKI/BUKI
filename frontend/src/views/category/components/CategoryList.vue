<template>
  <div class="category-list">
    <category-item
      v-for="(item, index) in bigcategory"
      :key="index"
      :item="item.name"
      :idx="index"
      :isModal="isModal"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
import CategoryItem from "./CategoryItem.vue";

export default {
  name: "CategoryList",
  components: {
    CategoryItem,
  },
  // props
  props: {
    isModal: Boolean,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./CategoryList.css"></style>
