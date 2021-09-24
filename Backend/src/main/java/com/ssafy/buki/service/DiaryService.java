package com.ssafy.buki.service;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.domain.diary.DiaryRepository;
import com.ssafy.buki.domain.diary.DiaryReqDto;
import com.ssafy.buki.domain.ranking.RankingResDto;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;
    private UserRepository userRepository;
    private BigCategoryRepository bigCategoryRepository;
    private SecondCharacterRepository secondCharacterRepository;

    private RankingService rankingService;


    public void saveDiary(DiaryReqDto diaryReqDto, Long userId) {
        User user = userRepository.getById(userId);
        BigCategory bigCategory = bigCategoryRepository.findBigCategoryById(diaryReqDto.getBigcategoryId());

        // 일기 저장
        Diary diary = new Diary(diaryReqDto.getContent(), diaryReqDto.getImage(), diaryReqDto.getSmallcategoryName(), diaryReqDto.getShare(), LocalDateTime.now(), user, bigCategory);
        diaryRepository.save(diary);

        // 부캐 생성 | 경험치 적립

        if (RankingService.setOperations == null) rankingService.setAllExpData();
        SecondCharacter secondCharacter = secondCharacterRepository.findSecondCharacterByUserIdAndBigCategoryId(userId, diaryReqDto.getBigcategoryId());
        if (secondCharacter == null) { // 부캐 생성
            if (secondCharacterRepository.countSecondCharacterByUserId(userId) > 0) {
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDate.now(), false, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
            } else {
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDate.now(), true, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
            }
            Double ranking = RankingService.setOperations.score("ranking", user.getId().toString());
            RankingService.setOperations.add("ranking", user.getId().toString(), ranking + 100);
        } else { //경험치 적립
            if (!secondCharacter.getDate().equals(LocalDate.now())) { // 적립 O
//                if(!secondCharacter.getDate().equals(LocalDate.now())){ // 적립 O
                secondCharacterRepository.plusExp(user, bigCategory);
                Double ranking = RankingService.setOperations.score("ranking", user.getId().toString());
                RankingService.setOperations.add("ranking", user.getId().toString(), ranking + 100);
            }
        }
    }
}
