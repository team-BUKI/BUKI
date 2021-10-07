package com.ssafy.buki.domain.diary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryUpdateReqDto {
    private String image;
    private Long id;
    private Boolean share;
    private String content;
}
