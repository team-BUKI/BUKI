package com.ssafy.buki.service;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.diary.*;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;
    private UserRepository userRepository;
    private BigCategoryRepository bigCategoryRepository;
    private SecondCharacterRepository secondCharacterRepository;
    private Common common;

    public void saveDiary(DiaryReqDto diaryReqDto, Long userId){
        User user = userRepository.getById(userId);
        BigCategory bigCategory = bigCategoryRepository.findBigCategoryById(diaryReqDto.getBigcategoryId());

        // 일기 저장
        Diary diary = new Diary(diaryReqDto.getContent(), diaryReqDto.getImage(), diaryReqDto.getSmallcategoryName(), diaryReqDto.getShare(), LocalDate.now(), user, bigCategory);
        diaryRepository.save(diary);

        // 부캐 생성 | 경험치 적립
        SecondCharacter secondCharacter = secondCharacterRepository.findSecondCharacterByUserIdAndBigCategoryId(userId, diaryReqDto.getBigcategoryId());
        if(secondCharacter == null){ // 부캐 생성
            if(secondCharacterRepository.countSecondCharacterByUserId(userId) > 0){
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDateTime.now(), false, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
            }else{
                SecondCharacter newSecondCharacter = new SecondCharacter(100, LocalDateTime.now(), true, user, bigCategory);
                secondCharacterRepository.save(newSecondCharacter);
            }
        }else{ //경험치 적립
            String tempDate = secondCharacter.getDate().toString().split("T")[0];
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate changedDate = LocalDate.parse(tempDate, format);
            if(!changedDate.equals(LocalDate.now())){ // 적립 O
                secondCharacterRepository.plusExp(user, bigCategory);
            }
        }
    }

    // 일기 수정
    public void updateDiary(DiaryUpdateReqDto diaryUpdateReqDto, User user){
        Diary diary = diaryRepository.findDiaryById(diaryUpdateReqDto.getId());
        if(diary.getUser().getId() == user.getId()){ // 예외처리 할까말까
            diaryRepository.updateDiary(diaryUpdateReqDto.getContent(), diaryUpdateReqDto.getImage(), diaryUpdateReqDto.getShare(), diaryUpdateReqDto.getId());
        }
    }

    // 일기 삭제
    public void deleteDiary(Long id){
        diaryRepository.deleteDiaryById(id);
    }

    // 특정 날짜 일기 가져오기
    public List<DiaryResDto> getDiariesByDate(Long userId, String date, User user) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate changedDate = LocalDate.parse(date, format);
        List<Diary> diaryList = diaryRepository.getDailyDiary(changedDate);

        boolean flag = false;
        if (user == null || user.getId() != userId) { // public
            flag = true;
        }

        return common.entitytoDtoAtDiary(flag, diaryList);
    }
}
