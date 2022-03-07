package com.atguigu.ucenterservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RegistyVo
 * @Package com.atguigu.ucenterservice.entity.vo
 * @Author yuanchaoxin
 * @Date 2022/3/6
 * @Version 1.0
 * @Description
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}