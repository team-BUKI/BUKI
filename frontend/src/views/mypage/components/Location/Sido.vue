<template>
  <div
    class="sido-wrapper"
    v-if="this.idx > 0"
    @click="onClick"
    :class="{ 'sido-selected-wrapper': isSelected }"
  >
    <span class="sido-title title-4" :class="{ 'sido-active-title': isSelected }">{{ name }}</span>
  </div>
</template>
<script>
export default {
  name: "Sido",
  props: {
    name: String,
    idx: Number,
    selectedSigungu: Number,
  },
  computed: {
    isSelected() {
      return this.selectedSigungu == this.idx ? true : false;
    },
  },
  methods: {
    onClick() {
      this.$emit("clickSigungu", this.idx);
    },
  },
};
</script>
<style>
.sido-wrapper {
  /* Auto Layout */
  justify-content: center;
  align-items: center;
  text-align: center;
  padding: 0px;

  position: static;
  width: 100%;
  height: 48px;
  left: 0px;
  top: 0px;
}

.sido-selected-wrapper {
  background-color: var(--main-lime);
  color: black;
}

.sido-title {
  line-height: 48px;
}

.sido-active-title {
  color: black;
}
</style>
