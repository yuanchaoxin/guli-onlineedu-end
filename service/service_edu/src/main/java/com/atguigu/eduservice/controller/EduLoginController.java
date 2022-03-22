package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EduLoginController
 * @Package com.atguigu.eduservice.controller
 * @Author yuanchaoxin
 * @Date 2022/2/21
 * @Version 1.0
 * @Description
 */
@RestController
//@CrossOrigin
public class EduLoginController {

    @PostMapping("/eduservice/user/login")
    public R login() {
        return R.success().data("token","admin");
    }

    @GetMapping("/eduservice/user/info")
    public R info() {
        return R.success()
                .data("roles","{admin}")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
