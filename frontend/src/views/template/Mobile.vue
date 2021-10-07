<template>
  <div>
    <div class="container">
      <my-header />
      <div class="contents">
        <span class="title title-3">타이틀</span>
      </div>
      <my-footer :selected="'home'" />
    </div>
    <my-modal :isShow="false" />
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import MyModal from "./MyModal.vue";

export default {
  name: "Template",
  components: {
    MyHeader,
    MyFooter,
    MyModal,
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

<style scoped src="./Mobile.css"></style>
