<template>
  <div>
    <div class="container">
      <my-header />
      <div class="contents">
        <span class="title title-3">MBTI 테스트</span>
      </div>
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "Mbti",
  components: {
    MyHeader,
    MyFooter,
  },
  // props
  props: {},
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

<style scoped src="./Mbti.css"></style>
