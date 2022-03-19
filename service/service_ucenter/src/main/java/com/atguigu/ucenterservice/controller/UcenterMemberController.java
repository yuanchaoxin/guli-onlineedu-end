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

    /**
     * 登录
     * @param ucenterMember
     * @return
     */
    @PostMapping("/ucenterservice/member/login")
    public R login(@RequestBody UcenterMember ucenterMember) {

        String token = ucenterMemberService.login(ucenterMember);

        return R.success().data("token", token);
    }

    /**
     * 注册用户
     * @param registerVo
     * @return
     */
    @PostMapping("/ucenterservice/member/register")
    public R register(@RequestBody RegisterVo registerVo) {

        ucenterMemberService.register(registerVo);
        return R.success();
    }

    /**
     * 根据token获取用户信息
     * @param httpRequest
     * @return
     */
    @GetMapping("/ucenterservice/member/getMemberInfo")
    public R getMemberInfo(HttpServletRequest httpRequest) {

        UcenterMember ucenterMember = ucenterMemberService.getMemberInfo(httpRequest);
        return R.success().data("member", ucenterMember);
    }

    /**
     * 根据id获取会员信息
     * @param memberId
     * @return
     */
    @PostMapping("/ucenterservice/member/getUcenterMemberOrder/{memberId}")
    public UcenterMemberOrder getUcenterMemberOrder(@PathVariable("memberId") String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);

        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberOrder);

        return ucenterMemberOrder;
    }

    /**
     * 根据时间获取当天注册会员人数
     * @param day
     * @return
     */
    @GetMapping("/ucenterservice/member/getRegisterCountByDay/{day}")
    public R getRegisterCountByDay(@PathVariable("day") String day) {

        Integer registerCount = ucenterMemberService.getRegisterCountByDay(day);
        return R.success().data("registerCount", registerCount);
    }
}

