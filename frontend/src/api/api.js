import { API_SERVER_URL } from '@/constant/index.js';

export default {
	URL: API_SERVER_URL + '/api',
	ROUTES: {
		// User
		login: '/user/login',
		updateNickname: '/user/nickname/',
		checkNickname: '/user/nickname/', // + nickname
		getUserInfo: '/user/info',
		deleteUser: '/user/delete',
		saveUserInfo: '/user/info',

		// Interest
		setInterestCategory: '/interest/category',
		getInterestCategory: '/interest/category',
		setInterestRegion: '/interest/region',
		getInterestRegion: '/interest/region',
		setInterestClass: '/interest/class',
		getInterestClass: '/interest/class/', // + pageId

		// Class
		getRecommendClass: '/class/recommend',
		getPopularClass: '/class/popular',
		searchClassByCategory: '/class/category/', // + pageId?bigcategoryId=&smallcategoryId=&sigunguId=&minPrice=&maxPrice=
		searchClassByKeyword: '/class/keyword/', // + pageId?keyword=

		// Diary
		getAllDiary: '/diary/', // + pageId/userId
		getMonthlyDiary: '/diary/monthly/', // + userId/year/month
		getDailyDiary: '/diary/daily/', // + userId/date (ex. 2021-09-09)
		writeDiary: '/diary',
		updateDiary: '/diary',
		deleteDiary: '/diary/', // + diaryId
		uploadImage: '/diary/image',

		// SecondCharacter
		getSecondCharacter: '/second',
		setSecondCharacterNickname: '/second/nickname',
		setMainSecondCharacter: '/second',

		// Ranking
		getRankingList: '/ranking',
	},
};
