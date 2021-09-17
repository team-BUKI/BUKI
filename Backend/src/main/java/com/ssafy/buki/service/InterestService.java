package com.ssafy.buki.service;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassRepository;
import com.ssafy.buki.domain.hobbyclass.HobbyClassResDto;
import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interestcategory.InterestCategoryRepository;
import com.ssafy.buki.domain.interestcategory.InterestCategoryResDto;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClassRepository;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClassReqDto;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.interestregion.InterestRegionRepository;
import com.ssafy.buki.domain.interestregion.InterestRegionResDto;
import com.ssafy.buki.domain.sigungu.SigunguRepository;
import com.ssafy.buki.domain.smallcategory.SmallCategoryRepository;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.buki.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestService {


    private final InterestCategoryRepository interestCategoryRepository;

    private final SmallCategoryRepository smallCategoryRepository;

    private final InterestRegionRepository interestRegionRepository;

    private final SigunguRepository sigunguRepository;

    private final InterestHobbyClassRepository interestHobbyClassRepository;

    private final HobbyClassRepository hobbyClassRepository;

    // 1. Post 관심 카테고리 설정
    public List<InterestCategoryResDto> setInterestingCategory(List<Integer> categoryId, User user) {
        if (categoryId.size() > 6) throw new BusinessException(EXCEED_INTEREST_CATEGORY);
        //데이터 삭제
        if (interestCategoryRepository.findInterestCategoriesByUserId(user.getId()).size() > 0) {
            interestCategoryRepository.deleteInterestCategoriesByUserId(user.getId());
        }

        for (Integer id : categoryId
        ) {
            InterestCategory interestCategory = InterestCategory.builder().user(user).smallCategory(smallCategoryRepository.findSmallCategoryById(id)).build();
            interestCategoryRepository.save(interestCategory);
        }

        return getInterestCategory(user);
    }

    // 2. Post 관심 지역 설정
    public List<InterestRegionResDto> setInterestRegion(List<Integer> regionId, User user) {
        if (regionId.size() > 3) throw new BusinessException(EXCEED_INTEREST_REGION);
        //데이터 삭제
        if (interestRegionRepository.findInterestRegionsByUserId(user.getId()).size() > 0) {
            interestRegionRepository.deleteInterestRegionByUserId(user.getId());
        }

        for (Integer id : regionId
        ) {
            InterestRegion interestRegion = InterestRegion.builder().user(user).sigungu(sigunguRepository.findSigunguById(id)).build();
            interestRegionRepository.save(interestRegion);
        }

        return getInterestRegion(user);
    }

    // 3. Post 관심 클래스 설정
    public void setInterestClass(InterestHobbyClassReqDto interestHobbyClassReq, User user) {
        Long id = interestHobbyClassReq.getHobbyClassId();
        InterestHobbyClass interestHobbyClass = interestHobbyClassRepository.findInterestHobbyClassByHobbyClassIdAndUserId(id, user.getId());
        if (interestHobbyClassReq.isInterest()) { //관심 클래스일 경우
            if (interestHobbyClass == null) throw new BusinessException(NOT_RIGHT_DATA);
            interestHobbyClassRepository.deleteInterestHobbyClassByHobbyClassIdAndUserId(id, user.getId());
        } else { //관심 클래스가 아닐 경우
            if (interestHobbyClass != null) throw new BusinessException(ALREADY_HAS_DATA);
            HobbyClass hobbyClass = hobbyClassRepository
                    .findHobbyClassById(id);

            interestHobbyClassRepository.save(InterestHobbyClass.builder()
                    .user(user)
                    .hobbyClass(hobbyClass)
                    .build());

        }

    }
    // 4. Get 관심 카테고리 가져오기

    public List<InterestCategoryResDto> getInterestCategory(User user) {
        List<InterestCategory> interestCategories = interestCategoryRepository.findInterestCategoriesByUserId(user.getId());

        List<InterestCategoryResDto> interestCategoryList = new ArrayList<>();
        for (InterestCategory interestCategory : interestCategories
        ) {
            interestCategoryList.add(InterestCategoryResDto.builder().id(interestCategory.getSmallCategory().getId())
                    .name(interestCategory.getSmallCategory().getName()).build());
        }
        return interestCategoryList;
    }

    // 5. Get 관심 지역 가져오기
    public List<InterestRegionResDto> getInterestRegion(User user) {
        List<InterestRegion> interestRegions = interestRegionRepository.findInterestRegionsByUserId(user.getId());
        List<InterestRegionResDto> interestRegionList = new ArrayList<>();

        for (InterestRegion interestRegion : interestRegions
        ) {
            interestRegionList.add(InterestRegionResDto.builder()
                    .sigunguId(interestRegion.getSigungu().getId())
                    .name(interestRegion.getSigungu().getName())
                    .build());
        }

        return interestRegionList;
    }

    // 6. Get 관심 클래스 가져오기
    public List<HobbyClassResDto> getInterestClass(User user) {
        List<InterestHobbyClass> interestHobbyClassList = interestHobbyClassRepository.findInterestHobbyClassByUserId(user.getId());
        List<HobbyClassResDto> hobbyClassReslist = new ArrayList<>();

        for (InterestHobbyClass interestHobbyClass : interestHobbyClassList
        ) {
            HobbyClass hobbyClass = hobbyClassRepository.findHobbyClassById(interestHobbyClass.getHobbyClass().getId());
            HobbyClassResDto hobbyClassResDto = HobbyClassResDto.builder()
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
                    .interest(true)
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build();

            hobbyClassReslist.add(hobbyClassResDto);
        }

        return hobbyClassReslist;

    }

}
