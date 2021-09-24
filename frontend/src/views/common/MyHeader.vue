<template>
  <div class="header-wrapper">
    <img
      class="logo"
      src="@/assets/images/logo.png"
      @click="$router.push({ name: 'Home' })"
    />
    <div class="icon-wrapper" @click="openModal">
      <i class="fas fa-search"></i>
    </div>
    <search-modal v-if="isOpenSearch" @closeModal="closeModal" />
  </div>
</template>

<script>
import SearchModal from "./components/SearchModal.vue";
import { mapMutations, mapState } from "vuex";

export default {
  name: "MyHeader",
  components: { SearchModal },
  // props
  props: {},
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("classStore", ["isOpenSearch"]),
  },
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    ...mapMutations("classStore", ["SET_IS_OPEN_SEARCH"]),
    // 검색 모달 열기
    openModal() {
      this.SET_IS_OPEN_SEARCH(true);
    },
    // 검색 모달 닫기
    closeModal() {
      this.SET_IS_OPEN_SEARCH(false);
    },
  },
};
</script>

<style scoped src="./MyHeader.css"></style>
