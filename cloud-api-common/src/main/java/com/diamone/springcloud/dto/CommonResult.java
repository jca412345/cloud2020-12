package com.diamone.springcloud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T date;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }
}
