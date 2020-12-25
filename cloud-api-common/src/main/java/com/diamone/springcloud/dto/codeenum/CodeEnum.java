package com.diamone.springcloud.dto.codeenum;

public enum CodeEnum {
    //定义各种状态
    SUCCESS(200, "success"),
    FAIL(444, "fail");
    private int code;
    private String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
