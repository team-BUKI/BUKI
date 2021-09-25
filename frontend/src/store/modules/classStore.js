import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const classStore = {
  namespaced: true,
  state: {
    sido: [
      { name: "", sigunguList: [] },
      { name: "서울", sigunguList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12] },
      {
        name: "경기",
        sigunguList: [13, 14, 15, 16, 17, 18, 19, 20, 1, 22, 23, 24],
      },
      { name: "강원", sigunguList: [25] },
      { name: "부산", sigunguList: [26] },
      { name: "경상", sigunguList: [27] },
      { name: "충청", sigunguList: [28] },
      { name: "전라", sigunguList: [29] },
      { name: "제주", sigunguList: [30] },
      { name: "온라인", sigunguList: [31] },
    ],
    sigungu: [
      "",
      "서울 전체",
      "강남·서초",
      "노원·강북·도봉·중랑",
      "강서·금천·양천",
      "동대문·성북",
      "동작·관악",
      "마포·서대문·은평",
      "성동·광진",
      "송파·강동",
      "영등포·구로",
      "용산",
      "종로·중구",
      "경기 전체",
      "수원·화성",
      "성남·분당",
      "안양·용인",
      "부천·인천",
      "의정부·남양주",
      "일산·고양·파주·김포",
      "하남·광주",
      "광명·시흥",
      "이천·여주",
      "가평·양평",
      "평택·안성·오산",
      "강원 전체",
      "부산 전체",
      "경상 전체",
      "충청 전체",
      "전라 전체",
      "제주 전체",
      "온라인 전체",
    ],
    bigcategory: [
      { name: "", smallcategoryList: [] },
      { name: "미술", smallcategoryList: [1, 2, 3, 4] },
      { name: "공예", smallcategoryList: [5, 6, 7, 8, 9, 10, 11] },
      { name: "요리", smallcategoryList: [12, 13, 14] },
      { name: "음악", smallcategoryList: [15, 16, 17] },
      { name: "액티비티", smallcategoryList: [18, 19, 20] },
      { name: "운동", smallcategoryList: [21, 22] },
      { name: "라이프", smallcategoryList: [23, 24, 25, 26, 27, 28, 29] },
      { name: "사진·영상", smallcategoryList: [30, 31] },
      { name: "자기계발", smallcategoryList: [32, 33, 34, 35, 36] },
    ],
    smallcategory: [
      "",
      "드로잉",
      "디지털 드로잉",
      "캘리그래피",
      "미술DIY키트",
      "플라워",
      "뜨개·자수",
      "금속·가죽·도자기",
      "비누·조향·캔들",
      "종이·나무",
      "색다른 공예",
      "공예DIY키트",
      "요리",
      "베이킹",
      "요리DIY키트",
      "보컬·랩",
      "악기",
      "작곡·프로듀싱",
      "액티비티",
      "연기·댄스",
      "여행",
      "요가·필라테스",
      "헬스·PT",
      "글쓰기",
      "뷰티",
      "사주·타로·운세",
      "명상",
      "심리상담",
      "인테리어",
      "교양·지식",
      "사진",
      "영상",
      "커리어",
      "수익 창출",
      "언어",
      "디자인",
      "IT 개발",
    ],
    recommendClassList: [],
    interestClassList: [],
    popularClassList: [],
    searchClassList: [],
    isOpenSearch: false,
    nickname: "구구",
    token: localStorage.getItem("token"),
  },
  getters: {
    recommendClassList(state) {
      return state.recommendClassList;
    },
    interestClassList(state) {
      return state.interestClassList;
    },
    popularClassList(state) {
      return state.popularClassList;
    },
    searchClassList(state) {
      return state.searchClassList;
    },
    isOpenSearch(state) {
      return state.isOpenSearch;
    },
    nickname(state) {
      return state.nickname;
    },
    token(state) {
      return state.token;
    },
    config: (state) => ({
      headers: { Authorization: "Bearer " + state.token },
    }),
  },
  mutations: {
    SET_RECOMMEND_CLASS_LIST(state, data) {
      state.recommendClassList = data;
    },
    SET_INTEREST_CLASS_LIST(state, data) {
      state.interestClassList = data;
    },
    SET_POPULAR_CLASS_LIST(state, data) {
      state.popularClassList = data;
    },
    SET_SEARCH_CLASS_LIST(state, data) {
      state.searchClassList = data;
    },
    SET_IS_OPEN_SEARCH(state, data) {
      state.isOpenSearch = data;
    },
  },
  actions: {
    // 클래스 목록 불러와서 저장
    fetchClassList({ dispatch }) {
      dispatch("getRecommendClass");
      dispatch("getInterestClassFirst", 0);
      dispatch("getPopularClass");
    },
    // 사용자 추천 클래스 불러오기
    async getRecommendClass({ getters, commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getRecommendClass, getters.config)
        .then((res) => {
          commit("SET_RECOMMEND_CLASS_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 사용자 관심 클래스 불러오기 (메인)
    async getInterestClassFirst({ getters, commit }, id) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getInterestClass + id, getters.config)
        .then((res) => {
          commit("SET_INTEREST_CLASS_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 사용자 관심 클래스 불러오기
    async getInterestClass({ getters, commit }, data) {
      await axios
        .get(
          SERVER.URL + SERVER.ROUTES.getInterestClass + data.id,
          getters.config
        )
        .then((res) => {
          if (res.data.length == 0) {
            data.state.complete();
          } else {
            setTimeout(() => {
              let arr = getters.interestClassList;
              arr = arr.concat(res.data);
              commit("SET_INTEREST_CLASS_LIST", arr);
              data.state.loaded();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 카테고리별 인기 클래스 불러오기
    async getPopularClass({ getters, commit }) {
      await axios
        .get(SERVER.URL + SERVER.ROUTES.getPopularClass, getters.config)
        .then((res) => {
          commit("SET_POPULAR_CLASS_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 카테고리로 클래스 검색
    async searchClassByCategory({ getters, commit }, data) {
      await axios
        .get(
          SERVER.URL +
            SERVER.ROUTES.searchClassByCategory +
            data.id +
            data.query,
          getters.config
        )
        .then((res) => {
          if (res.data.length == 0) {
            data.state.complete();
          } else {
            setTimeout(() => {
              let arr = getters.searchClassList;
              arr = arr.concat(res.data);
              commit("SET_SEARCH_CLASS_LIST", arr);
              data.state.loaded();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 키워드로 클래스 검색
    async searchClassByKeyword({ getters, commit }, data) {
      await axios
        .get(
          SERVER.URL +
            SERVER.ROUTES.searchClassByKeyword +
            data.id +
            "?keyword=" +
            data.keyword,
          getters.config
        )
        .then((res) => {
          if (res.data.length == 0) {
            data.state.complete();
          } else {
            setTimeout(() => {
              let arr = getters.searchClassList;
              arr = arr.concat(res.data);
              commit("SET_SEARCH_CLASS_LIST", arr);
              data.state.loaded();
            }, 1000);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 관심 클래스 등록여부 변경
    async setInterestClass({ getters, dispatch }, data) {
      // 로그인 했을 때만 변경 가능
      if (getters.token && getters.token != "") {
        await axios
          .post(
            SERVER.URL + SERVER.ROUTES.setInterestClass,
            data,
            getters.config
          )
          .then((res) => {
            dispatch("fetchClassList");
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        Swal.fire({
          text: "로그인 후 이용해주세요",
          showConfirmButton: false,
        });
        // 로그인 페이지로 보내기
        router.push({ name: "Login" });
      }
    },
  },
};

export default classStore;
