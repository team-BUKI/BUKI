<template>
  <span class="result-location" @click="onClick">
    <span class="title-5">{{ this.name }}</span>
    <img src="@/assets/images/xicon.png" />
  </span>
</template>
<script>
import { mapActions } from "vuex";
export default {
  name: "LocationWithX",
  props: {
    name: String,
    idx: Number,
  },

  methods: {
    ...mapActions("accountStore", ["removeInterestLocation"]),
    onClick() {
      this.removeInterestLocation(this.idx);
    },
  },
};
</script>
<style>
.result-location {
  position: static;
  display: inline-block;
  height: 34px;

  border-radius: 15px;
  background-color: var(--main-lime);
}

.result-location > span {
  color: black;
  padding: 5px;
  line-height: 34px;
  vertical-align: middle;
}

.result-location > img {
  padding: 4px;
  line-height: 34px;
  vertical-align: middle;
}
</style>
