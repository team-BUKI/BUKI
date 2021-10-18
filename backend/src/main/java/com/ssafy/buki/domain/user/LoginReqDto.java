package com.ssafy.buki.domain.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
    private String email;
    private SocialType socialType;
}
