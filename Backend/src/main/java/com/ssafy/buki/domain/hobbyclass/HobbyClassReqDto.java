package com.ssafy.buki.domain.hobbyclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HobbyClassReqDto {
    private Integer bigCategoryId;
    private Integer smallCategoryId;
    private Integer sigunguId;
    private Integer minPrice;
    private Integer maxPrice;
}
