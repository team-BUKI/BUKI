package com.ssafy.buki.domain.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {
    private Long id;
    private String token;
    private String nickname;
    private Boolean first;
}
