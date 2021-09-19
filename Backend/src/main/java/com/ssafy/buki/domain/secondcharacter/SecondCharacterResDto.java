package com.ssafy.buki.domain.secondcharacter;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondCharacterResDto {
    private Long id;
    private int exp;
    private String image;
    private int bigCategoryId;
    private String bigCategoryName;
    private Boolean represent;
}
