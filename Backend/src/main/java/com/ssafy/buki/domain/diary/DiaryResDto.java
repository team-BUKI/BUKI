package com.ssafy.buki.domain.diary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryResDto {
    private Long id;
    private String bigcategory;
    private String smallcategory;
    private String content;
    private Boolean share;
    private String image;
    private String date;
}
