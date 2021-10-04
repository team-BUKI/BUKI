import Vue from "vue";
import axios from "axios";
import SERVER from "@/api/api";
import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.$cookies.config("1d");

const characterStore = {
  namespaced: true,
  state: {
    characterList: [
      {
        bicategoryName: "",
        characterInfo: "",
      },
      {
        bigcategoryName: "미술",
        characterName: "아티",
        characterInfo: "명문 미대 출신으로 인스타 팔로워 10만의 스타",
      },
      {
        bigcategoryName: "공예",
        characterName: "크래피",
        characterInfo: "조용한 성격으로 손재주가 뛰어나다",
      },
      {
        bigcategoryName: "요리",
        characterName: "쿠미",
        characterInfo: "퇴근 후 맛있는 음식을 만들어 먹는 게 낙. 요리 유튜브 운영 중 ",
      },
      {
        bigcategoryName: "음악",
        characterName: "멜로니",
        characterInfo: "노래를 사랑해 오디션 프로그램에서 나갔다 본선까지 진출",
      },
      {
        bigcategoryName: "액티비티",
        characterName: "액스터",
        characterInfo: "굉장히 활발한 성격으로 매주 실내 클라이밍을 하러 간다",
      },
      {
        bigcategoryName: "운동",
        characterName: "요고",
        characterInfo: "최근 운동에 재미를 붙여 바디 프로필을 준비한다 ",
      },
      {
        bigcategoryName: "라이프",
        characterName: "자까",
        characterInfo: "별명이 잡학다식으로 부업으로 소설을 집필한다",
      },
      {
        bigcategoryName: "사진•영상",
        characterName: "포티",
        characterInfo: "실력있는 프리랜서 포토그래퍼로 뮤즈는 반려묘 '치즈'",
      },
      {
        characterName: "스톡이",
        bigcategoryName: "자기계발",
        characterInfo: "요즘 취미가 주식 재태크이다. 차익으로 컴퓨터를 바꿨다.",
      },
    ],
    mySecondCharacter: [], // 보유 부캐 리스트
    characterListInfo: [], //전체 캐릭터 페이지에서 쓸 완전체 캐릭터 정보
    representCharacter: {}, //대표 부캐
    adjective: "", //별청
    imagePath: [
      "",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character1-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character2-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character3-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character4-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character5-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character6-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character7-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character8-1.gif",
      "https://buki-aws-bucket.s3.ap-northeast-2.amazonaws.com/assets/character/character9-1.gif",
    ],
  },
  getters: {
    mySecondCharacter(state) {
      let res = Vue.$cookies.get("mySecondCharacter");
      state.mySecondCharacter = JSON.parse(res);
      return state.mySecondCharacter;
    },
    getRepresentCharacterName(state) {
      // return state.representCharacter.name;
      if (state.mySecondCharacter.length > 0) {
        for (let i = 0; i < state.mySecondCharacter.length; i++) {
          if (state.mySecondCharacter[i].represent) {
            state.representCharacter = state.mySecondCharacter[i];
            let idx = state.mySecondCharacter[i].bigcategoryId;
            let name = state.characterList[idx].characterName;
            return name;
          }
        }
      }
    },
    getRepresentCharacterIdx(state) {
      return state.representCharacterIdx;
    },
    getRepresentCharacter(state) {
      return state.representCharacter;
    },
    getCharacterListInfo(state) {
      return state.characterListInfo;
    },
  },
  mutations: {
    // 보유 부캐 set
    SET_MY_SECOND_CHARACTER(state, data) {
      Vue.$cookies.set("mySecondCharacter", JSON.stringify(data));
      state.mySecondCharacter = data;
    },
    // 대표 부캐 set
    SET_REPRESENT_CHARACTER(state) {
      for (let i = 0; i < state.mySecondCharacter.length; i++) {
        if (state.mySecondCharacter[i].represent) {
          state.representCharacter = state.mySecondCharacter[i];
          let idx = state.mySecondCharacter[i].bigcategoryId;
          state.representCharacter.name = state.characterList[idx].characterName;
          break;
        }
      }
    },
    // 부캐 swap
    UPDATE_REPRESENT_CHARACTER(state, data) {
      state.representCharacter = state.mySecondCharacter[data];
      state.representCharacter.name =
        state.characterList[state.mySecondCharacter[data].bigcategoryId].characterName;
    },
    // 전체 부캐 정보 세팅
    SET_MY_TOTAL_CHARACTER_LIST(state, { getters }) {
      state.characterListInfo[0] = {};
      state.mySecondCharacter = getters.mySecondCharacter;
      for (let i = 1; i < state.characterList.length; i++) {
        state.characterListInfo[i] = state.characterList[i];
        let exist = false;
        var obtain = {
          obtain: true,
        };
        for (let j = 0; j < state.mySecondCharacter.length; j++) {
          if (state.mySecondCharacter[j].bigcategoryId == i) {
            Object.assign(state.characterListInfo[i], state.mySecondCharacter[j], obtain);
            exist = true;
            break;
          }
        }
        if (!exist) {
          let temp = {
            exp: 0,
            level: 0,
            image: state.imagePath[i],
            represent: false,
            obtain: false,
          };
          Object.assign(state.characterListInfo[i], temp);
        }
      }
    },
  },
  actions: {
    // 보유 부캐 확인하기
    async getMySecondCharacters({ commit, rootGetters }) {
      if (rootGetters.token != "") {
        await axios
          .get(SERVER.URL + SERVER.ROUTES.getMySecondCharacters, {
            headers: rootGetters.authorization,
          })
          .then(({ data }) => {
            if (data != null) {
              commit("SET_MY_SECOND_CHARACTER", data);
              commit("SET_REPRESENT_CHARACTER");
              return data;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    setMyCharacterList({ commit }, data) {
      commit("SET_MY_CHARACTER_LIST", data);
    },
    //representCharacter 바꾸기
    updateRepresentCharacter({ commit }, data) {
      commit("UPDATE_REPRESENT_CHARACTER", data);
    },
    // 전체 캐릭터 list + 내 보유 캐릭터 정보 가져오기
    async getTotalCharacterList({ commit, rootGetters, getters }) {
      commit("SET_MY_TOTAL_CHARACTER_LIST", { getters });
    },
    // 대표 부캐
    async setRepresentCharacter({ commit, state, getters, rootGetters }, data) {
      axios
        .put(SERVER.URL + SERVER.ROUTES.setRepresentCharacter, data, {
          headers: rootGetters.authorization,
        })
        .then(({ res }) => {
          var idx = 0;
          for (let i = 0; i < state.mySecondCharacter.length; i++) {
            if (state.mySecondCharacter[i].id == data.prevId) {
              state.mySecondCharacter[i].represent = false;
            }
            if (state.mySecondCharacter[i].id == data.afterId) {
              idx = i;
              state.mySecondCharacter[i].represent = true;
            }
          }
          commit("SET_MY_SECOND_CHARACTER", state.mySecondCharacter);
          commit("UPDATE_REPRESENT_CHARACTER", idx);
          commit("SET_MY_TOTAL_CHARACTER_LIST", { getters });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
export default characterStore;
