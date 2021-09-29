<template>
  <div>
    <div class="container">
      <div class="title-div">
        <span class="title title-3">일기 수정하기</span>
        <div class="icon-wrapper" @click="$router.go(-1)">
          <i class="fas fa-times"></i>
        </div>
      </div>
      <div class="contents">
        <div class="diary-title">
          <div
            class="diary-category title-5"
            :class="'category-' + bigcategoryId"
          >
            {{ smallcategoryName }}
          </div>
          <div class="title title-4">{{ dateStr }}</div>
        </div>
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
        <div class="update-button button-4 title-5" @click="clickUpdateButton">
          수정하기
        </div>
      </div>
      <my-footer :selected="'diary'" />
    </div>
  </div>
</template>

<script>
import MyFooter from "@/views/common/MyFooter.vue";
import { mapActions } from "vuex";
import SERVER from "@/api/api";
import axios from "axios";
import Swal from "sweetalert2";

export default {
  name: "DiaryUpdate",
  components: {
    MyFooter,
  },
  // props
  props: {},
  // data
  data() {
    return {
      diaryId: 0,
      bigcategoryId: "",
      smallcategoryName: "",
      content: "",
      image: "",
      share: true,
    };
  },
  // computed
  computed: {
    dateStr: {
      get() {
        let date = this.$route.params.data.date;
        let year = date.substring(0, 4);
        let month = date.substring(5, 7);
        let day = date.substring(8, 10);
        return year + "년 " + month + "월 " + day + "일";
      },
      set() {},
    },
  },
  // lifecycle hook
  mounted() {
    if (this.$route.params.data) {
      this.setDiaryInfo();
    } else {
      Swal.fire({
        text: "올바른 접근이 아닙니다",
        showConfirmButton: false,
        timer: 1000,
      }).then(() => {
        this.$router.go(-1);
      });
    }
  },
  // methods
  methods: {
    ...mapActions("diaryStore", ["updateDiary"]),
    // 해당 일기 내용 셋팅
    setDiaryInfo() {
      let item = this.$route.params.data;
      this.diaryId = item.id;
      this.bigcategoryId = item.bigcategoryId;
      this.smallcategoryName = item.smallcategoryName;
      this.content = item.content;
      this.image = item.image;
      this.share = item.share;
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
    // 일기 수정하기
    clickUpdateButton() {
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
        id: this.diaryId,
        content: this.content,
        image: this.image,
        share: this.share,
      };
      this.updateDiary(data);
    },
  },
};
</script>

<style scoped src="./DiaryUpdate.css"></style>
