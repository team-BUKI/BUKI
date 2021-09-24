<template>
  <div class="class-list">
    <class-item
      v-for="(classItem, index) in classList"
      :key="index"
      :item="classItem"
    />
  </div>
</template>

<script>
import ClassItem from "./ClassItem.vue";

export default {
  name: "ClassList",
  components: {
    ClassItem,
  },
  // props
  props: {
    classList: Array,
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
  methods: {},
};
</script>

<style scoped src="./ClassList.css"></style>
