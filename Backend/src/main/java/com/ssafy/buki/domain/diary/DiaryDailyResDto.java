package com.ssafy.buki.domain.diary;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryDailyResDto {
    String nickname;
    List<DiaryResDto> diaryList;
}
