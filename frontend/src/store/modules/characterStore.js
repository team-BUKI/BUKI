import axios from "axios";
import SERVER from "@/api/api";

const characterStore = {
  namespaced: true,
  state: {
    characterList: [
      {
        id: 0,
        bigCategoryId: 0,
        bigCategoryName: "",
        characterInfo: "",
      },
      {
        id: 1,
        bigCategoryId: 1,
        bigCategoryName: "미술",
        characterName: "아티",
        characterInfo: "명문 미대 출신으로 인스타 팔로워 10만의 스타",
      },
      {
        id: 2,
        bigCategoryId: 2,
        bigCategoryName: "공예",
        characterName: "크래피",
        characterInfo: "조용한 성격으로 손재주가 뛰어나다",
      },
      {
        id: 3,
        bigCategoryId: 3,
        bigCategoryName: "요리",
        characterName: "쿠미",
        characterInfo: "퇴근 후 맛있는 음식을 만들어 먹는 게 낙. 요리 유튜브 운영 중 ",
      },
      {
        id: 4,
        bigCategoryId: 4,
        bigCategoryName: "음악",
        characterName: "멜로니",
        characterInfo: "노래를 사랑해 오디션 프로그램에서 나갔다 본선까지 진출",
      },
      {
        id: 5,
        bigCategoryId: 5,
        bigCategoryName: "액티비티",
        characterName: "액스터",
        characterInfo: "굉장히 활발한 성격으로 매주 실내 클라이밍을 하러 간다",
      },
      {
        id: 6,
        bigCategoryId: 6,
        bigCategoryName: "운동",
        characterName: "요고",
        characterInfo: "최근 운동에 재미를 붙여 바디 프로필을 준비한다 ",
      },
      {
        id: 7,
        bigCategoryId: 7,
        bigCategoryName: "라이프",
        characterName: "자까",
        characterInfo: "별명이 잡학다식으로 부업으로 소설을 집필한다",
      },
      {
        id: 8,
        bigCategoryId: 8,
        bigCategoryName: "사진•영상",
        characterName: "포티",
        characterInfo: "실력있는 프리랜서 포토그래퍼로 뮤지를 반려묘 '치즈'",
      },
      {
        id: 9,
        bigCategoryId: 9,
        characterName: "스톡이",
        bigCategoryName: "자기계발",
        characterInfo: "요즘 취미가 주식 재태크이다. 차익으로 컴퓨터를 바꿨다.",
      },
    ],
    myCharacterList: [], // 보유 부캐 리스트
    topCharacter: "", //대표 부캐
    adjective: "", //대표 별청
  },
  getters: {
    getMyCharacterList(state) {
      return state.myCharacterList;
    },
  },
  mutations: {
    SET_MY_CHARACTER_LIST({ state }, data) {
      state.myCharacterList = data;
    },
  },
  actions: {
    // 보유 부캐 확인하기
    async getMySecondCharacters({ dispatch, rootGetters }) {
      if (rootGetters.token != "") {
        await axios.get(SERVER.URL + SERVER.ROUTES.getSecondCharacter, {
          headers: rootGetters.authorization
        }).then(({ data }) => {
          console.log(data);
          if (data != null) {
            dispatch("setMyCharacterList", data);
          }
        })
        .catch((err) => {
          console.log(err);
        });
        // await axios({
        //   methods: "get",
        //   headers: { Authorization: `Bearer ${rootState.accountStore.token}` },
        //   url: API_SERVER_URL + "/api/second",
        // })
        //   .then(({ data }) => {
        //     console.log(data);
        //     if (data != null) {
        //       dispatch("setMyCharacterList", data);
        //     }
        //   })
        //   .catch((err) => {
        //     console.log(err);
        //   });
      }
    },
    setMyCharacterList({ commit }, data) {
      commit("SET_MY_CHARACTER_LIST", data);
    },
  },
};
export default characterStore;
