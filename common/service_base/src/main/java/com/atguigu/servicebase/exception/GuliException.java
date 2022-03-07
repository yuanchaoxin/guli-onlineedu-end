package com.atguigu.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName GuliException
 * @Package com.atguigu.servicebase.config.exception
 * @Author yuanchaoxin
 * @Date 2022/2/20
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;
    private String message;
}
