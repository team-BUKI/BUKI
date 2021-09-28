package com.ssafy.buki.domain.ranking;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingResDto {
//    사용자 아이디
//    형용사 명사
//    대분류 아이디랑 레벨
//    전체 exp

    private Long id;
    private Long totalExp;
    private Integer bigcategoryId;
    private String imageUrl;
    private String secondcharacterNicknameAdj;
    private String secondcharacterNicknameNoun;

}
