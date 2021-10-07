<template>
  <div class="category" :style="getColor()">
    <div class="title-6 category-title">{{ this.name }}</div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "CategoryTag",
  props: {
    idx: Number,
    name: String,
  },
  computed: {
    ...mapState("classStore", ["bigcategory"]),
  },

  methods: {
    getColor() {
      if (this.bigcategory != null) {
        for (let i = 1; i < this.bigcategory.length; i++) {
          if (this.bigcategory[i].smallcategoryList.includes(this.idx)) {
            return "background-color:var(--category-" + i + ")";
          }
        }
      }
      return "background-color:var(--category-9)";
    },
  },
};
</script>
<style scoped src="./CategoryTag.css"></style>
