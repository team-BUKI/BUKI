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
    private String siteUrl;
    private int price;
    private String site;
    private int likeCnt;
    private String imageUrl;
}
