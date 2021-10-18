package com.ssafy.buki.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRefreshToken {
    @Id
    @Column(name = "REFRESH_TOKEN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "REFRESH_TOKEN")
    @NotNull
    private String refreshToken;

    public UserRefreshToken(String email, String refreshToken){
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
