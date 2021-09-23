package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.common.S3Uploader;
import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.domain.diary.DiaryReqDto;
import com.ssafy.buki.domain.diary.DiaryResDto;
import com.ssafy.buki.domain.diary.DiaryUpdateReqDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final S3Uploader s3Uploader;
    private final DiaryService diaryService;
    private final Common common;

    // 1. 이미지 업로드
    @PostMapping("/image")
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile);
    }

    // 2. 일기 쓰기
    @PostMapping("")
    public ResponseEntity writeDiary(@ApiIgnore final Authentication authentication, @RequestBody DiaryReqDto diaryReqDto) {
        User user = common.getUserByToken(authentication);
        diaryService.saveDiary(diaryReqDto, user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 3. 일기 수정
    @PutMapping("")
    public ResponseEntity updateDiary(@ApiIgnore final Authentication authentication, @RequestBody DiaryUpdateReqDto diaryUpdateReqDto){
        User user = common.getUserByToken(authentication);
        diaryService.updateDiary(diaryUpdateReqDto, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 4. 일기 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiary(@ApiIgnore final Authentication authentication, @PathVariable Long id){
        User user = common.getUserByToken(authentication);
        diaryService.deleteDiary(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 5. 특정 날짜 일기 가져오기
    @GetMapping("/daily/{userId}/{date}")
    public ResponseEntity<List<DiaryResDto>> getDailyDiary(@ApiIgnore final Authentication authentication, @PathVariable Long userId, @PathVariable String date) {
        if(authentication == null || !authentication.isAuthenticated()){
            List<DiaryResDto> diaryList = diaryService.getDiariesByDate(userId, date, null);
            return ResponseEntity.status(HttpStatus.OK).body(diaryList);
        }
        User user = common.getUserByTokenNotException(authentication);
        List<DiaryResDto> diaryList = diaryService.getDiariesByDate(userId, date, user);
        return ResponseEntity.status(HttpStatus.OK).body(diaryList);
    }
}
