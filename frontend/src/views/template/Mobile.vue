<template>
  <div class="container">
    <my-header />
    <div class="title title-3">타이틀</div>
    <my-footer />
  </div>
</template>

<script>
import "./Mobile.css";
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";

export default {
  name: "Template",
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
