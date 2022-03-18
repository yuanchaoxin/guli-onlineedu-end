package com.atguigu.orderservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.orderservice.entity.Order;
import com.atguigu.orderservice.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
@RestController
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 根据课程id创建订单
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("/orderservice/order/createOrder/{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId, HttpServletRequest request) {

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId, memberId);
        return R.success().data("orderNo", orderNo);
    }

    /**
     * 根据订单编号查询订单
     * @param orderNo
     * @return
     */
    @GetMapping("/orderservice/order/getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable("orderNo") String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);

        Order order = orderService.getOne(wrapper);

        return R.success().data("order", order);
    }

    /**
     * 根据课程id和会员id查询订单是否已支付
     * @param courseId
     * @param memberId
     * @return
     */
    @GetMapping("/orderservice/order/getOrderStatus/{courseId}/{memberId}")
    public boolean getOrderStatus(@PathVariable("courseId") String courseId,
                            @PathVariable("memberId") String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);

        int count = orderService.count(wrapper);

        return count > 0;
    }
}

