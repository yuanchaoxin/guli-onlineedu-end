package com.atguigu.eduservice.feign.fallback;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.feign.VodFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName VodFeignServiceFallBackHandle
 * @Package com.atguigu.eduservice.feign.fallback
 * @Author yuanchaoxin
 * @Date 2022/3/5
 * @Version 1.0
 * @Description
 */
@Component
public class VodFeignServiceFallBackHandle implements VodFeignService {

    @Override
    public R deleteAliyunVideoById(String videoId) {
        System.out.println("删除视频失败，请稍后重试");
        return R.error().message("删除视频失败，请稍后重试");
    }

    @Override
    public R batchDeleteAliyunVideo(List<String> videoIdList) {
        return R.error().message("批量删除视频失败，请稍后重试");
    }
}
