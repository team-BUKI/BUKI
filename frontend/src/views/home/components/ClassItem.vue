<template>
  <div>
    <div class="card" @click="clickCard">
      <div class="card-image">
        <img :src="item.imageUrl" />
      </div>
      <span class="card-site title-7">{{ item.site }}</span>
      <div class="card-interest" @click="clickInterest">
        <i v-if="item.interest" class="fas fa-heart interest"></i>
        <i v-else class="far fa-heart no-interest"></i>
      </div>
      <div class="category-div" :class="'category-' + item.bigcategoryId">
        <span class="title-7">{{ smallcategoryName }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "ClassItem",
  components: {},
  // props
  props: {
    item: Object,
  },
  // data
  data() {
    return {};
  },
  // computed
  computed: {
    ...mapState("classStore", ["smallcategory"]),
    smallcategoryName: {
      get() {
        return this.smallcategory[this.item.smallcategoryId];
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {},
  updated() {},
  // methods
  methods: {
    ...mapActions("classStore", ["setInterestClass"]),
    // 클래스 정보 모달 띄우기
    clickCard() {
      this.$emit("openModal", this.item);
    },
    // 관심 클래스 등록여부 변경
    clickInterest(event) {
      event.stopPropagation();
      let data = { hobbyClassId: this.item.id, interest: this.item.interest };
      this.setInterestClass(data);
      this.item.interest = !this.item.interest;
    },
  },
};
</script>

<style scoped src="./ClassItem.css"></style>
