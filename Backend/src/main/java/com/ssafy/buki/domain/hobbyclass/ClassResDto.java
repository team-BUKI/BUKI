package com.ssafy.buki.domain.hobbyclass;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassResDto {
    private Long id;
    private String title;
    private String type;
    private String site;
    private String siteUrl;
    private int price;
    private Integer likeCnt;
    private String imageUrl;
    private Integer sidoId;
    private Integer sigunguId;
    private boolean interest;
    private Integer bigcategoryId;
    private Integer smallcategoryId;
}
