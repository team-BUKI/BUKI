package com.ssafy.buki.service;

import com.ssafy.buki.domain.diary.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;
}
