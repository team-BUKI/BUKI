package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.bigcategory.BigCategoryResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClassResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClassReqDto;
import com.ssafy.buki.domain.sido.SidoResDto;
import com.ssafy.buki.domain.sigungu.SigunguResDto;
import com.ssafy.buki.domain.smallcategory.SmallCategoryResDto;
import com.ssafy.buki.service.HobbyClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/class")
@Slf4j
@RequiredArgsConstructor
public class HobbyClassController {

    private final HobbyClassService hobbyClassService;
    private final Common common;
    // 1. Get - 사용자 추천 클래스 가져오기
    @GetMapping("/recommend")
    public ResponseEntity<List<HobbyClassResDto>> getRecommendClass(@ApiIgnore final Authentication authentication){
        return ResponseEntity.status(200).body(hobbyClassService.getRecommendClass(common.getUserByToken(authentication)));
    }
    // 2. Get - 인기 클래스 가져오기
    // 관심클래스인지 체크 필요
    @GetMapping("/popular")
    public ResponseEntity<List<List<HobbyClassResDto>>> getPopularClass(@ApiIgnore final Authentication authentication){
        return ResponseEntity.status(200).body(hobbyClassService.getPopularClass(common.getUserByToken(authentication)));
    }

    // 5. Get - 카테고리로 검색한 클래스 가져오기
    @GetMapping("/category/{classId}")
    public ResponseEntity<List<HobbyClassResDto>> getClassSearchByCategory(@ApiIgnore final Authentication authentication, @PathVariable int classId, @ModelAttribute HobbyClassReqDto hobbyClassReqDto){
        return ResponseEntity.status(200).body(hobbyClassService.getClassSearchByCategory(common.getUserByToken(authentication), classId, hobbyClassReqDto));
    }

    // 6. Get - 키워드로 검색한 클래스 가져오기
    @GetMapping("/keyword/{classId}")
    public ResponseEntity<List<HobbyClassResDto>> getClassSearchByKeyword(final Authentication authentication, @PathVariable int classId, @RequestParam String keyword){
        return ResponseEntity.status(200).body(hobbyClassService.getClassSearchByKeyword(common.getUserByToken(authentication), classId, keyword));
    }


}

// 3. Get - 대분류 카테고리 가져오기 - O
//    @GetMapping("/bigcategory")
//    public ResponseEntity<List<BigCategoryResDto>> getBigCategory(){
//        return ResponseEntity.status(200).body(hobbyClassService.getBigCategory());
//    }
//
//    // 4. Get - 세부 카테고리 가져오기 - O
//    @GetMapping("/{bigcategory_id}/smallcategory")
//    public ResponseEntity<List<SmallCategoryResDto>> getSmallCategory(@PathVariable Integer bigcategory_id){
//        return ResponseEntity.status(200).body(hobbyClassService.getSmallCategory(bigcategory_id));
//    }
// 7. 지역(시도) 리스트 가져오기
//    @GetMapping("/region")
//    public ResponseEntity<List<SidoResDto>> getSidoList(){
//        return ResponseEntity.status(200).body(hobbyClassService.getSidoList());
//    }
//    // 8. 지역(시군구) 리스트 가져오기
//    @GetMapping("/region/{sidoId}")
//    public ResponseEntity<List<SigunguResDto>> getSiGunGuList(@PathVariable Integer sidoId){
//        return ResponseEntity.status(200).body(hobbyClassService.getSiGunGuList(sidoId));
//    }