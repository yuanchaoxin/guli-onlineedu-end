package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-06
 */
@RestController
@CrossOrigin
public class UcenterMemberController {

    @Resource
    private UcenterMemberService ucenterMemberService;

    @PostMapping("/ucenterservice/member/login")
    public R login(@RequestBody UcenterMember ucenterMember) {

        String token = ucenterMemberService.login(ucenterMember);

        return R.success().data("token", token);
    }

    @PostMapping("/ucenterservice/member/register")
    public R register(@RequestBody RegisterVo registerVo) {

        ucenterMemberService.register(registerVo);
        return R.success();
    }

    @GetMapping("/ucenterservice/member/getMemberInfo")
    public R getMemberInfo(HttpServletRequest httpRequest) {

        UcenterMember ucenterMember = ucenterMemberService.getMemberInfo(httpRequest);
        return R.success().data("member", ucenterMember);
    }

    @PostMapping("/ucenterservice/member/getUcenterMemberOrder/{memberId}")
    public UcenterMemberOrder getUcenterMemberOrder(@PathVariable("memberId") String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);

        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberOrder);

        return ucenterMemberOrder;
    }
}

