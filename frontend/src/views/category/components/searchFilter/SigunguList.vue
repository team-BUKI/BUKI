<template>
  <div class="sigungu-list">
    <div
      v-for="(item, index) in sigunguList"
      :key="index"
      :class="{ selected: item.id == sigunguId }"
      class="sigungu-item"
      @click="clickSigungu(item.id)"
    >
      <span class="title-4">{{ item.name }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "SigunguList",
  components: {},
  // props
  props: {
    sigunguList: Array,
    sigunguId: Number,
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
    clickSigungu(sigunguId) {
      this.$emit("clickSigungu", sigunguId);
    },
  },
};
</script>

<style scoped src="./SigunguList.css"></style>
