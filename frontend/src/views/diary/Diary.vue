<template>
  <div>
    <div class="container">
      <my-footer :selected="'diary'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "Diary",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return {
      pageId: 0,
    };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {},
};
</script>

<style scoped src="./Diary.css"></style>
