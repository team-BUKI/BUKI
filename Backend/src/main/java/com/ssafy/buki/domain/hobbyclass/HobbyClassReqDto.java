package com.ssafy.buki.domain.hobbyclass;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HobbyClassReqDto {
    private Integer bigCategoryId ;
    private Integer smallCategoryId;
    private Integer sigunguId;
    private Integer minPrice;
    private Integer maxPrice;
}
