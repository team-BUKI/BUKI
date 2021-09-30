import axios from "axios";
import SERVER from "@/api/api";

const mbtiStore = {
  namespaced: true,
  state: {
    mbtiTest: [
      {
        question: "오랜만에 하루 휴가를 얻었다.<br/> 어떻게 보낼까?",
        answer: [
          {
            content: "집에서 침대와 물아일체가 된다",
            value: [-3, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "I",
          },
          {
            content: "친구들과 약속을 잡고 신나게 논다",
            value: [3, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "E",
          },
        ],
      },
      {
        question:
          "친구가 알바해서 모은 돈으로 해외여행 간다고 연락이 왔다.<br/> 나의 첫번째 질문은?",
        answer: [
          {
            content: "어떤 방법으로 어떻게 모았는지를 묻는다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "S",
          },
          {
            content: "모은 액수와 여행 목적지를 묻는다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "N",
          },
        ],
      },
      {
        question: "조별과제를 할 때 무임승차가 보이면 나는 어떻게 할까?",
        answer: [
          {
            content: "'스프라이트 샤워' 제대로 못하는 팀원들은 제명한다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "T",
          },
          {
            content: "'사정이 있겠지...' 한 번 봐준다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "F",
          },
        ],
      },
      {
        question: "휴가 계획을 짤 때 나는 어떤 타입인가?",
        answer: [
          {
            content: "'7시 30분까지는 관람을 마쳐야 해' 구체적으로 짠다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "J",
          },
          {
            content: "'이날은 먹방데이~~~'' 대충 테마만 생각해둔다",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "P",
          },
        ],
      },
      {
        question: "이색 데이트를 가려고 하는데 어디로 갈까?",
        answer: [
          {
            content: "뒹굴뒹굴 만화카페",
            value: [0, -2, 0, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "추리 스릴러 방탈출",
            value: [0, 1, 0, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "어드벤처 스포츠 몬스터",
            value: [0, 2, 0, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "여행 중 유명한 유적지에 가게 된 나는?",
        answer: [
          {
            content: "'인증샷 찍어야지' 일단 사진부터 찍고 본다",
            value: [0, 0, -2, 1, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'오 이런 역사가 있구나' 안내문을 읽고 둘러본다",
            value: [0, 0, 1, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content:
              "'내가 이구역 가이드' 입장 전부터 가이드북을 보며 공부한다",
            value: [0, 0, 3, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "넷플릭스를 보려는데 내가 선택할 장르는?",
        answer: [
          {
            content: "'꿀잼 유익잼' 다큐멘터리",
            value: [0, 0, 1, -3, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'아무 생각 없이 볼 수 있는' 예능",
            value: [0, 0, 0, 3, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'펜트하우스 애청자 모여라' 드라마",
            value: [0, 0, 0, 2, 0, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'가슴이 웅장해진다. 마블 덕후' 영화",
            value: [0, 0, 0, 1, 0, 0, 0, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "유명 아티스트 전시회 무료 티켓이 생겼다면?",
        answer: [
          {
            content: "'나는야 얼리버드' 이미 보고 왔다",
            value: [0, 0, 0, 0, 3, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'공짜 티켓 생겼다!' 받았으니 간다",
            value: [0, 0, 0, 0, 1, 0, 0, 0, 0],
            mbti: "",
          },
          {
            content: "'전시회 관람은 따분해' 티켓을 양도한다",
            value: [0, 0, 0, 0, -3, 0, 0, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "친구에게 생일 선물을 주려고 한다. ",
        answer: [
          {
            content: "세상에 하나뿐인 핸드메이드 제품을 만들어 선물한다",
            value: [0, 0, 0, 0, 0, 3, 1, 0, 0],
            mbti: "",
          },
          {
            content: "핸드메이드가 뭐야..? 먹는 거야? 완제품을 사준다",
            value: [0, 0, 0, 0, 0, -3, 0, 0, 2],
            mbti: "",
          },
        ],
      },
      {
        question: "2000피스 퍼즐이 생겼다. 나의 유형은",
        answer: [
          {
            content: "엄청난 집중력으로 며칠만에 끝낸다.",
            value: [0, 0, 0, 0, 0, 0, 3, 0, 0],
            mbti: "",
          },
          {
            content: "끝내 완성은 하지만 오래 걸린다.",
            value: [0, 0, 0, 0, 0, 0, 1, 0, 0],
            mbti: "",
          },
          {
            content: "언제 다 해?!라고 생각하면서 포기한다. ",
            value: [0, 0, 0, 0, 0, 0, -2, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "새로운 취미를 시작했다. 나는?",
        answer: [
          {
            content:
              "하나를 배우면 둘을 아는 나! 새로운 취미에도 곧잘 적응한다.",
            value: [0, 0, 0, 0, 0, 0, 0, 2, 0],
            mbti: "",
          },
          {
            content: "낯설지만 적응 하기위해 일단 열심히 해본다. ",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            mbti: "",
          },
        ],
      },
      {
        question: "내가 관심있는 소모임은?",
        answer: [
          {
            content: "몸도 마음도 편한 힐링 모임",
            value: [0, 0, 0, 0, 0, 0, 0, 0, 2],
            mbti: "",
          },
          {
            content: "적당히 즐거웠으면 좋겠어! 친목 모임",
            value: [1, 0, 0, 0, 0, 0, 0, 0, 1],
            mbti: "",
          },
          {
            content: "다같이 으쌰으쌰 목적 달성을 위한 스터디 모임",
            value: [0, 0, 2, 0, 0, 0, 0, 0, -2],
            mbti: "",
          },
        ],
      },
    ],
    mbtiList: [
      {
        name: "INTJ",
        desc: "뭐든지 내껄로 만들어버리는 마이웨이형"
      },
      {
        name: "INTP",
        desc: "똑똑하고 분석적인 천재형"
      },
      {
        name: "ENTJ",
        desc: "도전정신이 뚜렷한 목표지향형"
      },
      {
        name: "ENTP",
        desc: "새로운 것을 좋아하는 재간둥이형"
      },
      {
        name: "INFJ",
        desc: "외유내강 예술형"
      },
      {
        name: "INFP",
        desc: "열정 만수르 완벽주의형"
      },
      {
        name: "ENFJ",
        desc: "주변에 사람이 끊이지 않는 타고난 리더형"
      },
      {
        name: "ENFP",
        desc: "매력있는 멀티플레이어형"
      },
      {
        name: "ISTJ",
        desc: "꼼꼼한 계획형"
      },
      {
        name: "ISFJ",
        desc: "쉬는게 제일 좋은 집순이/집돌이형"
      },
      {
        name: "ESTJ",
        desc: "뭐라도 해야되는 워커홀릭형"
      },
      {
        name: "ESFJ",
        desc: "사람 좋은 인싸형"
      },
      {
        name: "ISTP",
        desc: "뭐든지 잘하는 완벽주의형"
      },
      {
        name: "ISFP",
        desc: "순간을 즐기는 예술가형"
      },
      {
        name: "ESTP",
        desc: "넘치는 에너지형"
      },
      {
        name: "ESFP",
        desc: "모임을 좋아하는 스타형"
      }

    ],
    recommendCategory: [],
    mbti: "",
    mbtiDesc: ""
  },
  mutations: {
    SET_RECOMMEND_CATEGORY(state, data) {
      state.recommendCategory = data;
    },
    SET_MBTI(state, data) {
      state.mbti = data;
      for (let i = 0; i < state.mbtiList.length; i++) {
        if (state.mbtiList[i].name === data) {
          state.mbtiDesc = state.mbtiList[i].desc;
        }
      }
    },
  },
  actions: {
    setMbti({ commit }, data) {
      commit("SET_MBTI", data);
    },

    async getRecommendCategory({ commit }, data) {
      //SERVER.FLASK_URL + SERVER.ROUTES.recommendCategory
      await axios
        .post("https://j5a303.p.ssafy.io/data/api/surveys", data)
        .then((res) => {
          console.log(res.data);
          commit("SET_RECOMMEND_CATEGORY", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default mbtiStore;
