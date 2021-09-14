package com.ssafy.buki.service;

import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interestcategory.InterestCategoryRepository;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClassRepository;
import com.ssafy.buki.domain.interestregion.InterestRegionRepository;
import com.ssafy.buki.exception.BusinessException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.buki.exception.ErrorCode.EXCEED_INTEREST_CATEGORY;

@Service
public class InterestService {

    @Autowired
    InterestCategoryRepository interestCategoryRepository;

    @Autowired
    InterestRegionRepository interestRegionRepository;

    @Autowired
    InterestHobbyClassRepository interestHobbyClassRepository;

    // 1. Post 관심 카테고리 설정
    public void setInterestingCategory(List<Integer> categoryId){
        if(categoryId.size() > 6) throw new BusinessException(EXCEED_INTEREST_CATEGORY);
//        for (int id: categoryId
//             ) {
//            interestCategoryRepository.save(new InterestCategory())
//        }



    }
    // 2. Post 관심 지역 설정
    public void setInterestRegion(List<Integer> regionId){

    }
    // 3. Post 관심 클래스 설정
    public void setInterestClass(List<Integer> classId){

    }
    // 4. Get 관심 카테고리 가져오기
    // 5. Get 관심 지역 가져오기
    // 6. Get 관심 클래스 가져오기
}
