<template>
  <div
    class="category"
    :style="'background-color:var(--category-' + this.category.id + ')'"
  >
    <span class="title-6 category-title">{{ this.category.name }}</span>
  </div>
</template>
<script>
export default {
  name: "CategoryTag",
  props: {
    category: {
      id: 0,
      name: "",
    },
  },
};
</script>
<style scoped src="./CategoryTag.css"></style>
