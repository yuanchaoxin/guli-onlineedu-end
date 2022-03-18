package com.atguigu.orderservice.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.orderservice.entity.Order;
import com.atguigu.orderservice.feign.EduFeignService;
import com.atguigu.orderservice.feign.MemberFeignService;
import com.atguigu.orderservice.mapper.OrderMapper;
import com.atguigu.orderservice.service.OrderService;
import com.atguigu.orderservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private EduFeignService eduFeignService;

    /**
     * 生成订单
     * @param courseId
     * @param memberId
     * @return
     */
    @Override
    public String createOrder(String courseId, String memberId) {

        UcenterMemberOrder ucenterMemberOrder = memberFeignService.getUcenterMemberOrder(memberId);

        CourseWebVoOrder courseWebVoOrder = eduFeignService.getCourseWebVoOrder(courseId);

        Order order = new Order();

        order.setOrderNo(OrderNoUtil.getOrderNo());
        // 课程id
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebVoOrder.getTitle());
        order.setCourseCover(courseWebVoOrder.getCover());
        order.setTeacherName(courseWebVoOrder.getTeacherName());
        order.setTotalFee(courseWebVoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMemberOrder.getMobile());
        order.setNickname(ucenterMemberOrder.getNickname());
        // 订单状态（0：未支付 1：已支付）
        order.setStatus(0);
        // 支付类型 ，微信1
        order.setPayType(1);
        baseMapper.insert(order);
        // 返回订单号
        return order.getOrderNo();
    }
}
