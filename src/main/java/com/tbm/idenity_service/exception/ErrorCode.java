package com.tbm.idenity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001,"User Existed"),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategori Error"),
    INVALID_KEY(1000,"Invalid Key"),
    USERNAME_INVALID(1002,"Username must be at least 3 characters"),
    PASSSWORD_INVALID(1003,"Password must be at least 8 characters")

            ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
