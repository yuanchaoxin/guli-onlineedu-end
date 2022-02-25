package com.atguigu.servicebase.config.handle;

import com.atguigu.commonutils.R;
import com.atguigu.servicebase.config.exception.GuliException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandle
 * @Package com.atguigu.servicebase.config.handle
 * @Author yuanchaoxin
 * @Date 2022/2/20
 * @Version 1.0
 * @Description
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception exception) {
        exception.printStackTrace();
        return R.error().message("服务器异常，休息一下吧。。。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R handleArithmeticException(ArithmeticException exception) {
        exception.printStackTrace();
        return R.error().message("ArithmeticException异常，休息一下吧。。。");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R handleGuliException(GuliException exception) {
        exception.printStackTrace();
        return R.error().message(exception.getMessage()).code(exception.getCode());
    }
}
