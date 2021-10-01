package com.ssafy.buki.service;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.diary.*;
import com.ssafy.buki.domain.ranking.RankingResDto;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

@Service
@AllArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;
    private UserRepository userRepository;
    private BigCategoryRepository bigCategoryRepository;
    private SecondCharacterRepository secondCharacterRepository;
    private Common common;

    private RankingService rankingService;


    public Integer saveDiary(DiaryReqDto diaryReqDto, Long userId) {
        User user = userRepository.getById(userId);
        BigCategory bigCategory = bigCategoryRepository.findBigCategoryById(diaryReqDto.getBigcategoryId());

        // 새로 부캐 생성됐는지 체크
        int bigcategoryId = 0;

        // 일기 저장
        Diary diary = new Diary(diaryReqDto.getContent(), diaryReqDto.getImage(), diaryReqDto.getSmallcategoryName(), diaryReqDto.getShare(), LocalDate.now(), user, bigCategory);
        diaryRepository.save(diary);

        // 부캐 생성 | 경험치 적립

        if (RankingService.setOperations == null) {
            rankingService.init();
        }
//        if (RankingService.setOperations == null) rankingService.setAllExpData();
        SecondCharacter secondCharacter = secondCharacterRepository.findSecondCharacterByUserIdAndBigCategoryId(userId, diaryReqDto.getBigcategoryId());
        if (secondCharacter == null) { // 부캐 생성
            if (secondCharacterRepository.countSecondCharacterByUserId(userId) > 0) {
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDate.now(), false, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
            } else {
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDate.now(), true, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
                String noun = bigCategoryRepository.getById(bigCategory.getId()).getNicknameNoun();
                userRepository.updateSecondCharacterNicknameNoun(user.getId(), noun);
            }
            bigcategoryId = bigCategory.getId();
            Double ranking = RankingService.setOperations.score("ranking", user.getId().toString());
            if (ranking == null) ranking = 0.0;
            RankingService.setOperations.add("ranking", user.getId().toString(), ranking + 100);
        } else { //경험치 적립
            if (!secondCharacter.getDate().equals(LocalDate.now())) { // 적립 O
//                if(!secondCharacter.getDate().equals(LocalDate.now())){ // 적립 O
                secondCharacterRepository.plusExp(user, bigCategory);

                Double ranking = RankingService.setOperations.score("ranking", user.getId().toString());
                if (ranking == null) {
                    Long exp = secondCharacterRepository.totalExpByUser(user.getId());
                    if (exp != null) {
                        RankingService.setOperations.add("ranking", user.getId().toString(), exp);
                        ranking = exp.doubleValue();
                    }
                }
                RankingService.setOperations.add("ranking", user.getId().toString(), ranking + 100);

                // 보너스 경험치
                List<Diary> diaryList = diaryRepository.getContinuousDiary(user.getId(), bigCategory.getId(), LocalDate.now().minusDays(6), LocalDate.now());
                Stack<String> stack = new Stack<>();

                for(Diary d : diaryList){
                    String tmp = d.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
                    if(stack.isEmpty()){ // 스택에 값X
                        stack.push(tmp);
                        continue;
                    }
                    if(stack.peek().equals(tmp)){ // 스택 값과 같을 경우
                        continue;
                    }
                    stack.push(tmp);
                }

                if(stack.size() >= 7){ //연속일수 7일 이상
                    LocalDate bonusDate = secondCharacterRepository.findSecondCharacterByUserIdAndBigCategoryId(user.getId(), bigCategory.getId()).getBonusDate();
                    if(bonusDate != null && LocalDate.now().minusDays(6).isAfter(bonusDate)){
                        secondCharacterRepository.plusBonusExp(user, bigCategory);
                    }
                }
            }
        }
        return bigcategoryId;
    }

    // 일기 수정
    public void updateDiary(DiaryUpdateReqDto diaryUpdateReqDto, User user) {
        Diary diary = diaryRepository.findDiaryById(diaryUpdateReqDto.getId());
        if (diary.getUser().getId() == user.getId()) { // 예외처리 할까말까
            diaryRepository.updateDiary(diaryUpdateReqDto.getContent(), diaryUpdateReqDto.getImage(), diaryUpdateReqDto.getShare(), diaryUpdateReqDto.getId());
        }
    }

    // 일기 삭제
    public void deleteDiary(Long id) {
        diaryRepository.deleteDiaryById(id);
    }

    // 특정 날짜 일기 가져오기
    public DiaryDailyResDto getDiariesByDate(Long userId, String date, User user) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate changedDate = LocalDate.parse(date, format);
        List<Diary> diaryList = diaryRepository.getDailyDiary(changedDate, userId);
        String nickname = userRepository.findUserById(userId).getNickname();

        boolean flag = false;
        if (user == null || user.getId() != userId) { // public
            flag = true;
        }

        return common.entitytoDtoAtDiary(flag, diaryList, nickname);
    }

    // 한 달 동안 작성한 일기 개수 체크
    public int[] getDiariesByMonthly(Long userId, Integer year, Integer month, User user) {

        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month.toString();
        } else {
            monthStr = month.toString();
        }

        String startDate = new String(year + "-" + monthStr + "-" + "01");
        String endDate = new String(year + "-" + monthStr + "-" + days[month - 1]);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, format);
        LocalDate end = LocalDate.parse(endDate, format);

        List<Diary> diaryList = diaryRepository.getMonthlyDiary(start, end, userId);

        boolean flag = false;
        if (user == null || user.getId() != userId) { // public
            flag = true;
        }

        return common.monthlyDiary(flag, diaryList);
    }

    // 전체 일기 가져오기
    public List<DiaryResDto> getAllDiary(Long userId, int id, User user) {

        PageRequest pageRequest = PageRequest.of(id, 10, Sort.unsorted());
        Page<Diary> diaryList;

        if (user == null || user.getId() != userId) {
            diaryList = diaryRepository.findByUserIdAndShareTrueOrderByIdDesc(userId, pageRequest);
        } else {
            diaryList = diaryRepository.findByUserIdOrderByIdDesc(userId, pageRequest);
        }

        List<DiaryResDto> diaryResDtoList = new ArrayList<>();
        for (Diary diary : diaryList) {
            DiaryResDto diaryResDto = new DiaryResDto(
                    diary.getId(),
                    diary.getBigCategory().getId(),
                    diary.getSmallCategoryName(),
                    diary.getContent(),
                    diary.getShare(),
                    diary.getImage(),
                    diary.getDate().toString());
            diaryResDtoList.add(diaryResDto);
        }
        return diaryResDtoList;
    }
}
