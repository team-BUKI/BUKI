package com.ssafy.buki.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.buki.service.InterestService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interest")
public class InterestController {

    @Autowired
    InterestService interestService;
    // 1. Post 관심 카테고리 설정
//    @PostMapping("/category")
//    public void setInterestCategory(Authentication authentication, @RequestBody Map<String, List<Integer>> icategory){
//        List<Integer> categoryId = icategory.get("category");
//        interestService.setInterestingCategory(categoryId, user);
//    }
//    // 2. Post 관심 지역 설정
//    @PostMapping("/region")
//    public void setInterestRegion(Authentication authentication, @RequestBody Map<String, List<Integer>> iregion){
//        List<Integer> regionId = iregion.get("region");
//        interestService.setInterestRegion(regionId, user);
//    }
//    // 3. Post 관심 클래스 설정
//    @PostMapping("/class")
//    public void setInterestClass(Authentication authentication, @RequestBody Map<String, List<Integer>> iclass){
//        List<Integer> classId = iclass.get("class");
//        interestService.setInterestClass(classId, user);
//
//
//    }
//    // 4. Get 관심 카테고리 가져오기
//    @GetMapping("/category")
//    public ResponseEntity<T> getInterestCategory(){
//        return null;
//    }
//    // 5. Get 관심 지역 가져오기
//    @GetMapping("/region")
//    public ResponseEntity<T> getInterestRegion(){
//        return null;
//    }
//    // 6. Get 관심 클래스 가져오기
//    @GetMapping("/class/{class_id}")
//    public ResponseEntity<T> getInterestClass(@PathVariable Long id){
//        return null;
//    }
}
