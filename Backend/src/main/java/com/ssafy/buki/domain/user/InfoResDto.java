package com.ssafy.buki.domain.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoResDto {
    private String email;
    private String nickname;
    private String secondcharacterNicname;
}
