package com.ssafy.buki.domain.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NicknameReqDto {
    private String nickname;
}
