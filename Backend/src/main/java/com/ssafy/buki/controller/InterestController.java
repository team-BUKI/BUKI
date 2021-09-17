package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interestcategory.InterestCategoryResDto;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.interestregion.InterestRegionResDto;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ssafy.buki.exception.ErrorCode.INVALID_AUTH_TOKEN;

@RestController
@RequestMapping("/api/interest")
@RequiredArgsConstructor
public class InterestController {


    private final InterestService interestService;
    private final Common common;

    // 1. Post 관심 카테고리 설정
    @PostMapping("/category")
    public ResponseEntity<List<InterestCategoryResDto>> setInterestCategory(final Authentication authentication, @RequestBody List<Integer> categoryList) {
        return ResponseEntity.status(201).body(interestService.setInterestingCategory(categoryList,
                common.getUserByToken(authentication)));
    }

    //    // 2. Post 관심 지역 설정
    @PostMapping("/region")
    public ResponseEntity<List<InterestRegionResDto>> setInterestRegion(final Authentication authentication, @RequestBody List<Integer> regionList) {
        return ResponseEntity.status(201).body(interestService.setInterestRegion(regionList, common.getUserByToken(authentication)));
    }

    //    // 3. Post 관심 클래스 설정
//    @PostMapping("/class")
//    public void setInterestClass(final Authentication authentication, @RequestBody List<Integer> classList){
//        interestService.setInterestClass(classList, common.getUserByToken(authentication));
//
//
//    }
//    // 4. Get 관심 카테고리 가져오기
    @GetMapping("/category")
    public ResponseEntity<List<InterestCategoryResDto>> getInterestCategory(final Authentication authentication) {
        return ResponseEntity.status(200).body(interestService.getInterestCategory(common.getUserByToken(authentication)));
    }

    //    // 5. Get 관심 지역 가져오기
    @GetMapping("/region")
    public ResponseEntity<List<InterestRegionResDto>> getInterestRegion(final Authentication authentication) {
        return ResponseEntity.status(200).body(interestService.getInterestRegion(common.getUserByToken(authentication)));
    }
//    // 6. Get 관심 클래스 가져오기
//    @GetMapping("/class/{class_id}")
//    public ResponseEntity<T> getInterestClass(@PathVariable Long id){
//        return null;
//    }
}
