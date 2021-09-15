<template>
  <div>
    <div class="container">
      <my-header />
      <div class="title title-3">타이틀</div>
      <my-footer :selected="'diary'" />
    </div>
    <my-modal :isShow="true" />
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import MyModal from "@/views/common/MyModal.vue";

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
