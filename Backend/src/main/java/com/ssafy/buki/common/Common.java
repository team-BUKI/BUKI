package com.ssafy.buki.common;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassResDto;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClassRepository;
import com.ssafy.buki.domain.user.User;
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

    public User getUserByToken(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            throw new BusinessException(INVALID_AUTH_TOKEN);
        }
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getUser(userId);
        return user;
    }

    public User getUserByTokenNotException(Authentication authentication){
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
            if(interestHobbyClass == null) isInterest = false;
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
            if(interestHobbyClass == null) isInterest = false;
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
}
