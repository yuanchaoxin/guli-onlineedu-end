package com.atguigu.statisticsservice.feign;

import com.atguigu.commonutils.R;
import com.atguigu.statisticsservice.feign.fallback.MemberFeignServiceFallBackHandle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/18 23:24
 * @Version 1.0
 */
@Component
@FeignClient(name = "service-ucenter",fallback = MemberFeignServiceFallBackHandle.class)
public interface MemberFeignService {

    @GetMapping("/ucenterservice/member/getRegisterCountByDay/{day}")
    R getRegisterCountByDay(@PathVariable("day") String day);
}
