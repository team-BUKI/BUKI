package com.ssafy.buki.domain.interesthobbyclass;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestHobbyClassReqDto {
    private boolean interest;
    private Long hobbyClassId;
}
