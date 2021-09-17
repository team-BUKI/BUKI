<template>
  <div>
    <div class="card" @click="clickCard">
      <div class="card-image">
        <div class="card-overlay"></div>
        <img :src="item.imageUrl" />
      </div>
      <span class="card-site title-7">{{ item.siteName }}</span>
      <div class="card-interest" @click="clickInterest">
        <i v-if="item.interest" class="fas fa-heart interest"></i>
        <i v-else class="far fa-heart no-interest"></i>
      </div>
      <div class="card-category" :class="'category-' + item.bigcategoryId">
        <span class="title-7">{{ smallcategoryName }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ClassItem",
  components: {},
  // props
  props: {
    item: Object,
  },
  // data
  data() {
    return {
      smallcategoryName: "",
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["smallcategory"]),
  },
  // lifecycle hook
  mounted() {
    this.smallcategoryName = this.smallcategory[this.item.smallcategoryId];
  },
  // methods
  methods: {
    // 클래스 정보 모달 띄우기
    clickCard() {
      this.$emit("openModal", this.item);
    },
    // 관심 클래스로 등록
    clickInterest(event) {
      event.stopPropagation();
      this.item.interest = !this.item.interest;
    },
  },
};
</script>

<style scoped src="./ClassItem.css"></style>
