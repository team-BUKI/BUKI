package com.ssafy.buki.controller;

import com.ssafy.buki.common.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final S3Uploader s3Uploader;

    // 1. 이미지 업로드
    @PostMapping("/image")
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile);
    }
}
