package com.atguigu.eduservice.feign;

import com.atguigu.eduservice.feign.fallback.OrderFeignServiceFallBackHandle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/18 0:03
 * @Version 1.0
 */
@Component
@FeignClient(value = "service-order",fallback = OrderFeignServiceFallBackHandle.class)
public interface OrderFeignService {

    @GetMapping("/orderservice/order/getOrderStatus/{courseId}/{memberId}")
    boolean getOrderStatus(@PathVariable("courseId") String courseId,
                                  @PathVariable("memberId") String memberId);
}
