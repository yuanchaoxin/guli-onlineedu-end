package com.atguigu.orderservice.feign;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/15 0:37
 * @Version 1.0
 */
@FeignClient(name = "service-ucenter")
@Component
public interface MemberFeignService {

    @PostMapping("/ucenterservice/member/getUcenterMemberOrder/{memberId}")
    UcenterMemberOrder getUcenterMemberOrder(@PathVariable("memberId") String memberId);

}