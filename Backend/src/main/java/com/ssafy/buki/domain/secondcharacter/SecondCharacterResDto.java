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
    private int level;
    private int bigcategoryId;
    private String bigcategoryName;
    private Boolean represent;
}
