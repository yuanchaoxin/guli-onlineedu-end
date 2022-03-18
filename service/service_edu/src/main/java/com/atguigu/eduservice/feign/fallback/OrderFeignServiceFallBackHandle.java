package com.atguigu.eduservice.feign.fallback;

import com.atguigu.eduservice.feign.OrderFeignService;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderFeignServiceFallBackHandle
 * @Package com.atguigu.eduservice.feign.fallback
 * @Author yuanchaoxin
 * @Date 2022/3/18
 * @Version 1.0
 * @Description
 */
@Component
public class OrderFeignServiceFallBackHandle implements OrderFeignService {

    @Override
    public boolean getOrderStatus(String courseId, String memberId) {
        return false;
    }
}
