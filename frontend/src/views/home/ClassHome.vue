<template>
  <div>
    <div class="container">
      <my-header />
      <div class="banner" @click="$router.push({ name: 'Mbti' })">
        <img src="@/assets/images/banner.png" alt="MBTI Test Banner" />
      </div>
      <div class="contents">
        <div v-if="true" class="recommend">
          <div class="recommend-title">
            <span class="title title-4">싸피님을 위한 추천 클래스</span>
            <span class="title-6">전체보기</span>
          </div>
          <class-list :classList="recommendClassList" @openModal="openModal" />
        </div>
        <div v-else class="no-recommend">
          <div>
            <span class="title title-4">싸피님을 위한 추천 클래스</span>
          </div>
          <div class="no-recommend-wrapper title-5">
            <span>맞춤 추천을 위해서<br />관심 카테고리 등록이 필요해요!</span>
            <div class="no-recommend-button">
              <span class="title-6">취미 추천 받으러 가기</span>
            </div>
            <div class="no-recommend-button dark">
              <span class="title-6">관심 카테고리 설정</span>
            </div>
          </div>
        </div>
        <div class="interest">
          <div class="interest-title">
            <span class="title title-4">관심 클래스</span>
            <span class="title-6">전체보기</span>
          </div>
          <class-list :classList="interestClassList" @openModal="openModal" />
        </div>
        <div class="popular">
          <span class="title title-4">분야별 인기 클래스</span>
          <popular-class-list
            :popularClassList="popularClassList"
            @openModal="openModal"
          />
        </div>
      </div>
      <class-modal v-if="isOpen" :item="classItem" @closeModal="closeModal" />
      <my-footer :selected="'home'" />
    </div>
  </div>
</template>

<script>
import MyHeader from "@/views/common/MyHeader.vue";
import MyFooter from "@/views/common/MyFooter.vue";
import ClassList from "./components/ClassList.vue";
import PopularClassList from "./components/PopularClassList.vue";
import ClassModal from "./components/ClassModal.vue";

export default {
  name: "Template",
  components: {
    MyHeader,
    MyFooter,
    ClassList,
    PopularClassList,
    ClassModal,
  },
  // props
  props: {},
  // data
  data() {
    return {
      classItem: {},
      position: "",
      recommendClassList: [
        {
          id: 735,
          title: "[추석특가] 나만의 향수 만들기",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/112030",
          price: 50000,
          siteName: "프립",
          likeCnt: 7794,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
          sidoId: 1,
          sigunguId: 2,
          interest: false,
          bigcategoryId: 2,
          smallcategoryId: 8,
        },
        {
          id: 8058,
          title:
            "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
          type: "OFFLINE",
          siteUrl: "https://taling.me/Talent/Detail/874",
          price: 38500,
          siteName: "탈잉",
          likeCnt: 7232,
          imageUrl:
            "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
          sidoId: 1,
          sigunguId: 2,
          interest: false,
          bigcategoryId: 7,
          smallcategoryId: 24,
        },
        {
          id: 2012,
          title:
            "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/115497",
          price: 30000,
          siteName: "프립",
          likeCnt: 7212,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
          sidoId: 1,
          sigunguId: 7,
          interest: false,
          bigcategoryId: 3,
          smallcategoryId: 13,
        },
        {
          id: 7671,
          title:
            "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
          type: "OFFLINE",
          siteUrl: "https://taling.me/Talent/Detail/2949",
          price: 12650,
          siteName: "탈잉",
          likeCnt: 6573,
          imageUrl:
            "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
          sidoId: 1,
          sigunguId: 7,
          interest: false,
          bigcategoryId: 5,
          smallcategoryId: 19,
        },
        {
          id: 9272,
          title: "실무에 진짜 필요한 엑셀 노하우",
          type: "ONLINE",
          siteUrl: "https://taling.me/vod/view/37128",
          price: 119000,
          siteName: "탈잉",
          likeCnt: 8074,
          imageUrl:
            "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
          sidoId: 9,
          sigunguId: 31,
          interest: false,
          bigcategoryId: 9,
          smallcategoryId: 32,
        },
        {
          id: 743,
          title: "나만의 고체 향수 만들기",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/93640",
          price: 30000,
          siteName: "프립",
          likeCnt: 8111,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
          sidoId: 1,
          sigunguId: 11,
          interest: false,
          bigcategoryId: 2,
          smallcategoryId: 8,
        },
      ],
      interestClassList: [
        {
          id: 2012,
          title:
            "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/115497",
          price: 30000,
          siteName: "프립",
          likeCnt: 7212,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
          sidoId: 1,
          sigunguId: 7,
          interest: false,
          bigcategoryId: 3,
          smallcategoryId: 13,
        },
        {
          id: 7671,
          title:
            "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
          type: "OFFLINE",
          siteUrl: "https://taling.me/Talent/Detail/2949",
          price: 12650,
          siteName: "탈잉",
          likeCnt: 6573,
          imageUrl:
            "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
          sidoId: 1,
          sigunguId: 7,
          interest: false,
          bigcategoryId: 5,
          smallcategoryId: 19,
        },
        {
          id: 735,
          title: "[추석특가] 나만의 향수 만들기",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/112030",
          price: 50000,
          siteName: "프립",
          likeCnt: 7794,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
          sidoId: 1,
          sigunguId: 2,
          interest: false,
          bigcategoryId: 2,
          smallcategoryId: 8,
        },
        {
          id: 8058,
          title:
            "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
          type: "OFFLINE",
          siteUrl: "https://taling.me/Talent/Detail/874",
          price: 38500,
          siteName: "탈잉",
          likeCnt: 7232,
          imageUrl:
            "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
          sidoId: 1,
          sigunguId: 2,
          interest: false,
          bigcategoryId: 7,
          smallcategoryId: 24,
        },
        {
          id: 9272,
          title: "실무에 진짜 필요한 엑셀 노하우",
          type: "ONLINE",
          siteUrl: "https://taling.me/vod/view/37128",
          price: 119000,
          siteName: "탈잉",
          likeCnt: 8074,
          imageUrl:
            "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
          sidoId: 9,
          sigunguId: 31,
          interest: false,
          bigcategoryId: 9,
          smallcategoryId: 32,
        },
        {
          id: 743,
          title: "나만의 고체 향수 만들기",
          type: "OFFLINE",
          siteUrl: "https://www.frip.co.kr/products/93640",
          price: 30000,
          siteName: "프립",
          likeCnt: 8111,
          imageUrl:
            "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
          sidoId: 1,
          sigunguId: 11,
          interest: false,
          bigcategoryId: 2,
          smallcategoryId: 8,
        },
      ],
      popularClassList: [
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
        [
          {
            id: 735,
            title: "[추석특가] 나만의 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/112030",
            price: 50000,
            siteName: "프립",
            likeCnt: 7794,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/product_banner_1582855883582_62503",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
          {
            id: 8058,
            title:
              "[뷰티 유튜버+ 청담샵 경력] ❤ 리뉴얼 오픈❤ 자존감이 두배 올라가는 메이크업!",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/874",
            price: 38500,
            siteName: "탈잉",
            likeCnt: 7232,
            imageUrl:
              "https://s3.ap-northeast-2.amazonaws.com/taling.me/Content/Uploads/Cover/4f099ab71ac407931f06bcb0f005da9572303b17.jpg",
            sidoId: 1,
            sigunguId: 2,
            interest: false,
            bigcategoryId: 7,
            smallcategoryId: 24,
          },
          {
            id: 2012,
            title:
              "[망원] 스콘2종 (플레인/커피초코칩) 16~20개 만들기 (예약 가능)",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/115497",
            price: 30000,
            siteName: "프립",
            likeCnt: 7212,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/98466_banner_1543489230799",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 3,
            smallcategoryId: 13,
          },
          {
            id: 7671,
            title:
              "세상에 몸치는 없다! 춤토니쌤과 함께라면..★덤덤,퀸덤,버터,퍼미션투,핫소스",
            type: "OFFLINE",
            siteUrl: "https://taling.me/Talent/Detail/2949",
            price: 12650,
            siteName: "탈잉",
            likeCnt: 6573,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Cover/abcaa6a0512b54bfdb3eb2107d8b2e6d24504745.jpg",
            sidoId: 1,
            sigunguId: 7,
            interest: false,
            bigcategoryId: 5,
            smallcategoryId: 19,
          },
          {
            id: 9272,
            title: "실무에 진짜 필요한 엑셀 노하우",
            type: "ONLINE",
            siteUrl: "https://taling.me/vod/view/37128",
            price: 119000,
            siteName: "탈잉",
            likeCnt: 8074,
            imageUrl:
              "https://img.taling.me/Content/Uploads/Images/f7ee1485937a40ebabb146a318c4731055feb457.jpg",
            sidoId: 9,
            sigunguId: 31,
            interest: false,
            bigcategoryId: 9,
            smallcategoryId: 32,
          },
          {
            id: 743,
            title: "나만의 고체 향수 만들기",
            type: "OFFLINE",
            siteUrl: "https://www.frip.co.kr/products/93640",
            price: 30000,
            siteName: "프립",
            likeCnt: 8111,
            imageUrl:
              "https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2.0,f_auto,q_auto,r_5,w_192/30032_banner_1509092457433",
            sidoId: 1,
            sigunguId: 11,
            interest: false,
            bigcategoryId: 2,
            smallcategoryId: 8,
          },
        ],
      ],
      isOpen: false,
    };
  },
  // computed
  computed: {},
  // lifecycle hook
  mounted() {},
  // methods
  methods: {
    // 클래스 정보 모달 띄우기
    openModal(classItem) {
      this.classItem = classItem;
      this.isOpen = true;
    },
    // 클래스 정보 모달 닫기
    closeModal() {
      this.isOpen = false;
    },
  },
};
</script>

<style scoped src="./ClassHome.css"></style>
