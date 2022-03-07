package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName R
 * @Package com.atguigu.commonutils
 * @Author yuanchaoxin
 * @Date 2022/2/20
 * @Version 1.0
 * @Description
 */
@Data
public class R {

    @ApiModelProperty("是否成功")
    private Boolean status;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String,Object> data = new HashMap<String, Object>();

    //构造器私有
    private R() {}

    // 成功静态方法
    public static R success() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setStatus(Boolean.TRUE);
        return r;
    }

    // 成功静态方法
    public static R error() {
        R r = new R();
        r.setCode(ResultCode.ERROR.getCode());
        r.setMessage(ResultCode.ERROR.getMessage());
        r.setStatus(Boolean.FALSE);
        return r;
    }

    public R success(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String k, Object v) {
        this.data.put(k,v);
        return this;
    }

    public R data(Map<String,Object> data) {
        this.setData(data);
        return this;
    }
}
