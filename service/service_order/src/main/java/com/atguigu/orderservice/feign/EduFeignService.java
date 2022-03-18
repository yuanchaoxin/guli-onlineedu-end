package com.atguigu.orderservice.feign;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/3/15 23:41
 * @Version 1.0
 */
@Component
@FeignClient("service-edu")
public interface EduFeignService {

    @PostMapping("/eduservice/eduCourseFront/getCourseWebVoOrder/{courseId}")
    CourseWebVoOrder getCourseWebVoOrder(@PathVariable("courseId") String courseId);
}
