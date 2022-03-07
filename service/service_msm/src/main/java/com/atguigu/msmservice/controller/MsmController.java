package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MsmController
 * @Package com.atguigu.msmservice.controller
 * @Author yuanchaoxin
 * @Date 2022/3/6
 * @Version 1.0
 * @Description
 */
@RestController
@CrossOrigin
public class MsmController {

    @Resource
    private MsmService msmService;

    @GetMapping("/msmservice/msm/send/{phone}")
    public R sendMsg(@PathVariable("phone") String phone) {

        boolean result = msmService.sendMsg(phone);
        if (!result) {
            return R.error().message("发送短信失败");
        }
        return R.success();
    }
}
