package com.atguigu.eduservice.feign.fallback;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.feign.MemberFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MemberFeignServiceFallBackHandle
 * @Package com.atguigu.eduservice.feign.fallback
 * @Author yuanchaoxin
 * @Date 2022/3/15
 * @Version 1.0
 * @Description
 */
@Component
public class MemberFeignServiceFallBackHandle implements MemberFeignService {
    @Override
    public R getMemberInfo(@RequestHeader("token") String token) {
        return R.error().message("获取用户信息失败");
    }
}
