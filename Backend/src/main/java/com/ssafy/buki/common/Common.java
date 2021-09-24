package com.ssafy.buki.common;

import com.ssafy.buki.domain.diary.DiaryMonthlyResDto;
import com.ssafy.buki.domain.diary.DiaryResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassResDto;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClassRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.ssafy.buki.exception.ErrorCode.INVALID_AUTH_TOKEN;

@Component
@RequiredArgsConstructor
public class Common {

    private final UserService userService;
    private final InterestHobbyClassRepository interestHobbyClassRepository;

    public User getUserByToken(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException(INVALID_AUTH_TOKEN);
        }
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getUser(userId);
        return user;
    }

    public User getUserByTokenNotException(Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getUser(userId);
        return user;
    }

    public List<HobbyClassResDto> entityListToDto(List<HobbyClass> hobbyClassList, User user) {
        List<HobbyClassResDto> list = new ArrayList<>();

        for (HobbyClass hobbyClass : hobbyClassList
        ) {
            boolean isInterest = true;
            InterestHobbyClass interestHobbyClass = interestHobbyClassRepository.findInterestHobbyClassByHobbyClassIdAndUserId(hobbyClass.getId(), user.getId());
            if (interestHobbyClass == null) isInterest = false;
            list.add(HobbyClassResDto.builder()
                    .id(hobbyClass.getId())
                    .title(hobbyClass.getTitle())
                    .type(hobbyClass.getType())
                    .site(hobbyClass.getSite())
                    .siteUrl(hobbyClass.getSiteUrl())
                    .price(hobbyClass.getPrice())
                    .likeCnt(hobbyClass.getLikeCnt())
                    .imageUrl(hobbyClass.getImageUrl())
                    .sidoId(hobbyClass.getSido().getId())
                    .sigunguId(hobbyClass.getSigungu().getId())
                    .interest(isInterest)
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build());
        }

        return list;
    }

    public List<HobbyClassResDto> entityPageToDto(Page<HobbyClass> hobbyClassList, User user) {
        List<HobbyClassResDto> list = new ArrayList<>();

        for (HobbyClass hobbyClass : hobbyClassList
        ) {
            boolean isInterest = true;
            InterestHobbyClass interestHobbyClass = interestHobbyClassRepository.findInterestHobbyClassByHobbyClassIdAndUserId(hobbyClass.getId(), user.getId());
            if (interestHobbyClass == null) isInterest = false;
            list.add(HobbyClassResDto.builder()
                    .id(hobbyClass.getId())
                    .title(hobbyClass.getTitle())
                    .type(hobbyClass.getType())
                    .site(hobbyClass.getSite())
                    .siteUrl(hobbyClass.getSiteUrl())
                    .price(hobbyClass.getPrice())
                    .likeCnt(hobbyClass.getLikeCnt())
                    .imageUrl(hobbyClass.getImageUrl())
                    .sidoId(hobbyClass.getSido().getId())
                    .sigunguId(hobbyClass.getSigungu().getId())
                    .interest(isInterest)
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build());
        }

        return list;
    }

    public List<HobbyClassResDto> entityListToDto(List<HobbyClass> hobbyClassList) {
        List<HobbyClassResDto> list = new ArrayList<>();

        for (HobbyClass hobbyClass : hobbyClassList
        ) {

            list.add(HobbyClassResDto.builder()
                    .id(hobbyClass.getId())
                    .title(hobbyClass.getTitle())
                    .type(hobbyClass.getType())
                    .site(hobbyClass.getSite())
                    .siteUrl(hobbyClass.getSiteUrl())
                    .price(hobbyClass.getPrice())
                    .likeCnt(hobbyClass.getLikeCnt())
                    .imageUrl(hobbyClass.getImageUrl())
                    .sidoId(hobbyClass.getSido().getId())
                    .sigunguId(hobbyClass.getSigungu().getId())
                    .interest(false)
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build());
        }

        return list;
    }

    public List<HobbyClassResDto> entityPageToDto(Page<HobbyClass> hobbyClassList) {
        List<HobbyClassResDto> list = new ArrayList<>();

        for (HobbyClass hobbyClass : hobbyClassList
        ) {
            list.add(HobbyClassResDto.builder()
                    .id(hobbyClass.getId())
                    .title(hobbyClass.getTitle())
                    .type(hobbyClass.getType())
                    .site(hobbyClass.getSite())
                    .siteUrl(hobbyClass.getSiteUrl())
                    .price(hobbyClass.getPrice())
                    .likeCnt(hobbyClass.getLikeCnt())
                    .imageUrl(hobbyClass.getImageUrl())
                    .sidoId(hobbyClass.getSido().getId())
                    .sigunguId(hobbyClass.getSigungu().getId())
                    .interest(false)
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build());
        }

        return list;
    }

    public List<DiaryResDto> entitytoDtoAtDiary(boolean flag, List<Diary> diaryList) {
        List<DiaryResDto> diaryResDtoList = new ArrayList<>();
        if (flag) {
            for (Diary diary : diaryList) {
                if (diary.getShare()) {
                    DiaryResDto diaryResDto = new DiaryResDto(
                            diary.getId(),
                            diary.getBigCategory().getName(),
                            diary.getSmallCategoryName(),
                            diary.getContent(),
                            diary.getShare(),
                            diary.getImage(),
                            diary.getDate().toString());
                    diaryResDtoList.add(diaryResDto);
                }
            }
        } else {
            for (Diary diary : diaryList) {
                DiaryResDto diaryResDto = new DiaryResDto(
                        diary.getId(),
                        diary.getBigCategory().getName(),
                        diary.getSmallCategoryName(),
                        diary.getContent(),
                        diary.getShare(),
                        diary.getImage(),
                        diary.getDate().toString());
                diaryResDtoList.add(diaryResDto);
            }
        }
        return diaryResDtoList;
    }

    public List<DiaryMonthlyResDto> monthlyDiary(boolean flag, List<Diary> diaryList){
        List<DiaryMonthlyResDto> list = new ArrayList<>();
        if(flag){
            for(Diary diary: diaryList){
                if(diary.getShare()){
                    String date = diary.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if(list.size() > 0){
                        if(list.get(list.size()-1).getDate().equals(date)){
                            int cnt = list.get(list.size()-1).getCount()+1;
                            list.remove(list.size()-1);
                            list.add(new DiaryMonthlyResDto(date, cnt));
                        }else{
                            list.add(new DiaryMonthlyResDto(date, 1));
                        }
                    }else{
                        list.add(new DiaryMonthlyResDto(date, 1));
                    }
                }
            }
        }else{
            for(Diary diary: diaryList){
                    String date = diary.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if(list.size() > 0){
                        if(list.get(list.size()-1).getDate().equals(date)){
                            int cnt = list.get(list.size()-1).getCount()+1;
                            list.remove(list.size()-1);
                            list.add(new DiaryMonthlyResDto(date, cnt));
                        }else{
                            list.add(new DiaryMonthlyResDto(date, 1));
                        }
                    }else{
                        list.add(new DiaryMonthlyResDto(date, 1));
                    }
            }
        }
        return list;
    }

    public int getSecondCharacterLevel(SecondCharacter secondCharacter){
        if(secondCharacter.getExp() < 1000){
            return 1;
        }else if(secondCharacter.getExp() < 2500){
            return 2;
        }else{
            return 3;
        }
    }
}
