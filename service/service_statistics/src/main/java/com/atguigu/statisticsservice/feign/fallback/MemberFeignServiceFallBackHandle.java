package com.atguigu.statisticsservice.feign.fallback;

import com.atguigu.commonutils.R;
import com.atguigu.statisticsservice.feign.MemberFeignService;
import org.springframework.stereotype.Component;

/**
 * @ClassName MemberFeignServiceFallBackHandle
 * @Package com.atguigu.statisticsservice.feign.fallback
 * @Author yuanchaoxin
 * @Date 2022/3/18
 * @Version 1.0
 * @Description
 */
@Component
public class MemberFeignServiceFallBackHandle implements MemberFeignService {

    @Override
    public R getRegisterCountByDay(String day) {
        return R.error().message("获取注册人数失败");
    }
}
