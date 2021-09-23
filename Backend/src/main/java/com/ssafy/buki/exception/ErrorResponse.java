package com.ssafy.buki.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String msg;

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(errorCode.getHttpStatus().name());
        errorResponse.setMsg(errorCode.getDetail());

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}
