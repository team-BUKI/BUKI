package com.ssafy.buki.domain.diary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryMonthlyResDto {
    private String date;
    private Integer count;
}
