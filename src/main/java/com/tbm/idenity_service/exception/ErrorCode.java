package com.tbm.idenity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User Existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004,"User Not Existed", HttpStatus.NOT_FOUND),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategori Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1000,"Invalid Key", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002,"Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007,"You do not have permission", HttpStatus.FORBIDDEN),
    PASSWORD_INVALID(1003,"Password must be at least {min} characters", HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
