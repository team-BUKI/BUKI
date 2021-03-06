import { API_SERVER_URL, API_FLASK_SERVER_URL } from "@/constant/index.js";

export default {
  URL: API_SERVER_URL,
  FLASK_URL: API_FLASK_SERVER_URL,
  ROUTES: {
    // User
    login: "/user/login",
    updateNickname: "/user/nickname/",
    checkNickname: "/user/nickname/", // + nickname
    getUserInfo: "/user/info",
    saveUserInfo: "/user/info",
    deleteUser: "/user/delete",
    updateSecondNicknameAdj: "/user/adj/", // + secondNicknameAdj
    getSecondNicknameAdj: "/user/adj",

    // Interest
    setInterestCategory: "/interest/category",
    getInterestCategory: "/interest/category",
    setInterestRegion: "/interest/region",
    getInterestRegion: "/interest/region",
    setInterestClass: "/interest/class",
    getInterestClass: "/interest/class/", // + pageId

    // Class
    getFirstRecommendClass: "/class/recommend",
    getPopularClass: "/class/popular",
    searchClassByCategory: "/class/category/", // + pageId?bigcategoryId=&smallcategoryId=&sigunguId=&minPrice=&maxPrice=
    searchClassByKeyword: "/class/keyword/", // + pageId?keyword=
    putClickLog: "/class/clicklog/", // + classId

    // Diary
    getAllDiary: "/diary/", // + pageId/userId
    getMonthlyDiary: "/diary/monthly/", // + userId/year/month
    getDailyDiary: "/diary/daily/", // + userId/date (ex. 2021-09-09)
    writeDiary: "/diary",
    updateDiary: "/diary",
    deleteDiary: "/diary/", // + diaryId
    uploadImage: "/diary/image",

    // SecondCharacter
    getMySecondCharacters: "/second",
    setRepresentCharacter: "/second",

    // Ranking
    getRankingList: "/ranking",

    // Bigdata
    getSecondRecommendClass: "/recommend/", // + userId
    recommendCategory: "/surveys",
  },
};
