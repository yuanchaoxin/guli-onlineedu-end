package com.atguigu.commonutils;

import lombok.Data;

/**
 *  * @ClassName ResultCode
 *  * @Package com.atguigu.commonutils
 *  * @Author yuanchaoxin
 *  * @Date 2022/2/20
 *  * @Version 1.0
 *  * @Description
 */
public enum ResultCode {

    SUCCESS(20000,"成功"),
    ERROR(20001,"失败");

    private Integer code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
