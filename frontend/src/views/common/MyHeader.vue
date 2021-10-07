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
import SearchModal from "@/views/search/components/SearchModal.vue";
import { mapState, mapActions } from "vuex";

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
    ...mapActions("classStore", ["setIsOpenSearch"]),
    // 검색 모달 열기
    openModal() {
      this.setIsOpenSearch(true);
    },
    // 검색 모달 닫기
    closeModal() {
      this.setIsOpenSearch(false);
    },
  },
};
</script>

<style scoped src="./MyHeader.css"></style>
