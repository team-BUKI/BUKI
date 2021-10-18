package com.ssafy.buki.domain.secondcharacter;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondCharacterReqDto {
    private Long prevId;
    private Long afterId;
}
