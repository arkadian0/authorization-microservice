package com.teamfinder.client.response;

public class TfValidationDto {
    private String code;
    private String message;

    public TfValidationDto() {
    }

    public TfValidationDto(String code) {
        this.code = code;
    }

    public TfValidationDto(String code, String message) {
        this(code);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}