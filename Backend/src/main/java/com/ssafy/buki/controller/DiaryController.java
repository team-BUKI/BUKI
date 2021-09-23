package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.common.S3Uploader;
import com.ssafy.buki.domain.diary.DiaryReqDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity writeDiary(final Authentication authentication, @RequestBody DiaryReqDto diaryReqDto) {
        User user = common.getUserByToken(authentication);
        diaryService.saveDiary(diaryReqDto, user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
