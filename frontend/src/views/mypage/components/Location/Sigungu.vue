<template>
  <div
    class="sigungu-wrapper"
    @click="onClick"
    :class="{ 'sigungu-selected-wrapper': this.isClick }"
  >
    <span class="title-5 sigungu-title" :class="{ 'sigungu-selected-title': this.isClick }">{{
      this.name
    }}</span>
    <span class="title-5 sigungu-class-num">{{ this.classNumber }}</span>
  </div>
</template>
<script>
import Swal from "sweetalert2";
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  name: "Sigungu",
  props: {
    name: String,
    idx: Number,
  },
  data() {
    return {
      classNumber: 20,
      isClick: false,
    };
  },
  created() {
    if (this.interestLocation.includes(this.idx)) {
      this.isClick = true;
    }
  },
  computed: {
    ...mapState("accountStore", ["interestLocation"]),
    ...mapGetters("accountStore", ["getInterestLocationLength"]),
  },
  watch: {
    interestLocation: {
      deep: true,
      handler: function () {
        if (!this.interestLocation.includes(this.idx)) {
          this.isClick = false;
        }
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["addInterestLocation", "removeInterestLocation"]),

    onClick() {
      if (!this.isClick) {
        if (this.getInterestLocationLength >= 3) {
          Swal.fire({
            text: "관심 카테고리는 6개까지 등록 가능합니다.",
            showConfirmButton: false,
            timer: 1000,
          });
          return;
        }
        this.addInterestLocation(this.idx);
        this.isClick = true;
      } else {
        this.removeInterestLocation(this.idx);
        this.isClick = false;
      }
    },
  },
};
</script>
<style>
.sigungu-wrapper {
  display: flex;
  flex-direction: row;
  align-items: center;

  position: static;
  width: 100%;
  height: 48px;
}

.sigungu-title {
  display: inline-block;
  margin-left: 20px;
}

.sigungu-class-num {
  margin-left: 10px;
  color: var(--text-violet);
}

.sigungu-selected-wrapper {
  background-color: var(--main-lime);
}
.sigungu-selected-title {
  color: black;
}
</style>
