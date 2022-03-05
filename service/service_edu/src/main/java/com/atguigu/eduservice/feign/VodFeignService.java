package com.atguigu.eduservice.feign;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.feign.fallback.VodFeignServiceFallBackHandle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/4 23:52
 * @Version 1.0
 */
@FeignClient(name = "service-vod", fallback = VodFeignServiceFallBackHandle.class)
@Component
public interface VodFeignService {

    @DeleteMapping("/vodservice/video/deleteAliyunVideoById/{videoId}")
    R deleteAliyunVideoById(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vodservice/video/batchDeleteAliyunVideo")
    R batchDeleteAliyunVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
