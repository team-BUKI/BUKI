<template>
  <span class="result-category" @click="onClick">
    <span class="title-5">{{ this.name }}</span>
    <img src="@/assets/images/xicon.png" />
  </span>
</template>
<script>
import { mapActions } from "vuex";
export default {
  name: "CategoryWithX",
  props: {
    name: String,
    idx: Number,
  },

  methods: {
    ...mapActions("accountStore", ["removeInterestCategory"]),
    onClick() {
      this.removeInterestCategory(this.idx);
    },
  },
};
</script>
<style>
.result-category {
  position: static;
  display: inline-block;
  height: 34px;

  border-radius: 15px;
  background-color: var(--main-lime);
}

.result-category > span {
  color: black;
  padding: 5px;
  line-height: 34px;
  vertical-align: middle;
}

.result-category > img {
  padding: 4px;
  line-height: 34px;
  vertical-align: middle;
}
</style>
