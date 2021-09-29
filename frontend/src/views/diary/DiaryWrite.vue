<template>
  <div>
    <div class="container">
      <div class="title-div">
        <span class="title title-3">새 글 쓰기</span>
        <div class="icon-wrapper" @click="$router.go(-1)">
          <i class="fas fa-times"></i>
        </div>
      </div>
      <div class="contents">
        <div class="diary-category">
          <div class="select-div">
            <select
              v-model="bigcategoryId"
              class="title-5"
              @change="setSmallCategoryList()"
            >
              <option
                v-for="(item, index) in bigcategoryList"
                :key="index"
                :value="item.id"
              >
                {{ item.name }}
              </option>
            </select>
          </div>
          <div class="select-div">
            <select v-model="smallcategoryName" class="title-5">
              <option
                v-for="(item, index) in smallcategoryList"
                :key="index"
                :value="item"
              >
                {{ item }}
              </option>
            </select>
          </div>
        </div>
        <div class="today title title-4">{{ today }}</div>
        <div class="diary-content">
          <img
            v-if="image && image != ''"
            :src="image"
            alt="Diary Image"
            class="diary-image"
          />
          <textarea
            v-model.trim="content"
            class="body-2"
            type="text"
            placeholder="내용을 입력해주세요"
            @keyup="resizeContent"
          />
          <div class="diary-info">
            <div @click="changeShare">
              <div v-if="share" class="diary-info-share">
                <div class="icon-wrapper">
                  <i class="fas fa-unlock"></i>
                </div>
                <span class="title-6">공개</span>
              </div>
              <div v-else class="diary-info-share">
                <div class="icon-wrapper">
                  <i class="fas fa-lock"></i>
                </div>
                <span class="title-6">비공개</span>
              </div>
            </div>
            <input
              class="image-input"
              ref="uploadImage"
              type="file"
              accept="image/*"
              @change="uploadDiaryImage"
            />
            <div class="icon-wrapper" @click="clickImageButton">
              <i class="fas fa-image"></i>
            </div>
          </div>
        </div>
        <div class="write-button button-4 title-5" @click="clickWriteButton">
          등록하기
        </div>
      </div>
      <my-footer :selected="'diary'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapState, mapActions } from "vuex";
import SERVER from "@/api/api";
import axios from "axios";
import Swal from "sweetalert2";

export default {
  name: "DiaryWrite",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return {
      bigcategoryId: 0,
      bigcategoryList: [{ id: "0", name: "카테고리 선택" }],
      smallcategoryName: "소분류 선택",
      smallcategoryList: ["소분류 선택"],
      content: "",
      image: "",
      share: true,
    };
  },
  // computed
  computed: {
    ...mapState("classStore", ["bigcategory", "smallcategory"]),
    today: {
      get() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() + 1;
        let date = today.getDate();
        if (month < 10) month = "0" + month;
        if (date < 10) date = "0" + date;
        return year + "년 " + month + "월 " + date + "일";
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    this.setBigCategoryList();
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["writeDiary"]),
    // 카테고리 옵션 목록 설정
    setBigCategoryList() {
      for (var i = 1; i < this.bigcategory.length; i++) {
        this.bigcategoryList.push({ id: i, name: this.bigcategory[i].name });
      }
    },
    // 소분류 선택 옵션 목록 설정
    setSmallCategoryList() {
      this.smallcategoryName = "소분류 선택";
      this.smallcategoryList = ["소분류 선택"];
      let arr = this.bigcategory[this.bigcategoryId].smallcategoryList;
      for (var i = 0; i < arr.length; i++) {
        this.smallcategoryList.push(this.smallcategory[arr[i]]);
      }
    },
    // 내용 작성란 크기 조절
    resizeContent(event) {
      let obj = event.target;
      obj.style.height = "0px";
      obj.style.height = obj.scrollHeight + "px";
      // 스크롤 같이 이동
      window.scrollTo({ top: obj.scrollHeight });
    },
    // 일기 공개여부 변경
    changeShare() {
      this.share = !this.share;
    },
    // 사진 업로드창 보여주기
    clickImageButton() {
      this.$refs["uploadImage"].click();
    },
    // 일기에 들어갈 사진 업로드
    uploadDiaryImage() {
      let form = new FormData();
      let uploadImage = this.$refs["uploadImage"].files[0];
      form.append("multipartFile", uploadImage);
      axios
        .post(SERVER.URL + SERVER.ROUTES.uploadImage, form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          this.image = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 새로운 일기 등록하기
    clickWriteButton() {
      if (this.bigcategoryId == 0 || this.smallcategoryName == "소분류 선택") {
        Swal.fire({
          text: "카테고리를 선택해주세요",
          showConfirmButton: false,
          timer: 1000,
        });
        return;
      } else if (this.content.length == 0) {
        Swal.fire({
          text: "일기 내용을 작성해주세요",
          showConfirmButton: false,
          timer: 1000,
        });
        return;
      }
      let data = {
        bigcategoryId: this.bigcategoryId,
        smallcategoryName: this.smallcategoryName,
        content: this.content,
        image: this.image,
        share: this.share,
      };
      this.writeDiary(data);
    },
  },
};
</script>

<style scoped src="./DiaryWrite.css"></style>
