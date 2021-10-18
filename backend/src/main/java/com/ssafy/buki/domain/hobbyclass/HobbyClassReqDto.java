package com.ssafy.buki.domain.hobbyclass;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HobbyClassReqDto {
    private Integer bigcategoryId ;
    private Integer smallcategoryId;
    private Integer sigunguId;
    private Integer minPrice;
    private Integer maxPrice;
}
