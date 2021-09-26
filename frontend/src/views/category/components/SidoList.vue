<template>
  <div class="sido-list">
    <div
      v-for="(item, index) in sidoList"
      :key="index"
      :class="{ selected: item.id == sidoId }"
      class="sido-item"
      @click="clickSido(item.id)"
    >
      <span class="title-4">{{ item.name }}</span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "SidoList",
  components: {},
  // props
  props: {
    sidoList: Array,
    sidoId: Number,
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
    clickSido(sidoId) {
      this.$emit("clickSido", sidoId);
    },
  },
};
</script>

<style scoped src="./SidoList.css"></style>
