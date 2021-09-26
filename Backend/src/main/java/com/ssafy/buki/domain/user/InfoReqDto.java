package com.ssafy.buki.domain.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoReqDto {
    private String nickname;
    private List<Integer> category;
    private List<Integer> region;
}
