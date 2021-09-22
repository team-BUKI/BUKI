package com.ssafy.buki.domain.diary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryReqDto {
    private String image;
    private Boolean share;
    private String smallCategoryName;
    private Integer bigCategoryId;
    private String content;
}
