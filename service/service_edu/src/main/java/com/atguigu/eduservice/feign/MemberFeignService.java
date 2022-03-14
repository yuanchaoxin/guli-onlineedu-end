package com.atguigu.eduservice.feign;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.feign.fallback.MemberFeignServiceFallBackHandle;
import com.atguigu.eduservice.feign.fallback.VodFeignServiceFallBackHandle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/15 0:37
 * @Version 1.0
 */
@FeignClient(name = "service-ucenter")
@Component
public interface MemberFeignService {

    @GetMapping("/ucenterservice/member/getMemberInfo")
    R getMemberInfo(@RequestHeader("token") String token);

}