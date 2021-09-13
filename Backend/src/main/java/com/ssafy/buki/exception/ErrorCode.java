package com.ssafy.buki.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
/* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    ALREADY_HAS_USER(HttpStatus.BAD_REQUEST, "이미 가입되어 있는 유저입니다"),
    ALREADY_HAS_NICKNAME(HttpStatus.BAD_REQUEST, "이미 등록된 닉네임입니다"),
    EXCEED_INTEREST_CATEGORY(HttpStatus.BAD_REQUEST, "관심 카테고리는 6개까지 저장 가능합니다."),
    EXCEED_INTEREST_REGION(HttpStatus.BAD_REQUEST, "관심 지역은 3개까지 저장 가능합니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),


    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    JWT_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "로그아웃 된 사용자입니다"),


    /* 403 FORBIDDEN : 접근 권한 여부 */
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

            ;


    private final HttpStatus httpStatus;
    private final String detail;

}