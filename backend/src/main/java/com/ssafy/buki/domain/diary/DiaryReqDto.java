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
    private String smallcategoryName;
    private Integer bigcategoryId;
    private String content;
}
