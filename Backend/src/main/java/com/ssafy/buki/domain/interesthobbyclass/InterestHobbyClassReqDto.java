package com.ssafy.buki.domain.interesthobbyclass;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterestHobbyClassReqDto {
    private boolean interest;
    private Long hobbyclassId;
}
