package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.feign.OrderFeignService;
import com.atguigu.eduservice.frontvo.CourseFrontVo;
import com.atguigu.eduservice.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EduCourseFrontController
 * @Package com.atguigu.eduservice.controller.front
 * @Author yuanchaoxin
 * @Date 2022/3/8
 * @Version 1.0
 * @Description
 */
@RestController
@CrossOrigin
public class EduCourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Resource
    private EduChapterService eduChapterService;

    @Autowired
    private OrderFeignService orderFeignService;

    /**
     * 分页查询课程
     * @param current
     * @param size
     * @param courseFrontVo
     * @return
     */
    @PostMapping("/eduservice/eduCourseFront/listCourse/{current}/{size}")
    public R getCourseList(@PathVariable("current") long current,
                           @PathVariable("size") long size,
                           @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        Map<String,Object> map = eduCourseService.getCourseList(current, size, courseFrontVo);
        return R.success().data(map);
    }

    /**
     * 查询课程详情
     * @param courseId
     * @return
     */
    @GetMapping("/eduservice/eduCourseFront/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId, HttpServletRequest request) {

        // 课程信息
        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);

        // 章节信息
        List<ChapterVo> chapterVoList = eduChapterService.getAllChapterTreeByCourseId(courseId);

        // 会员是否已购买该课程
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean isBuy = false;
        // 已登录
        if (!StringUtils.isEmpty(memberId)) {
            isBuy = orderFeignService.getOrderStatus(courseId, memberId);
        }

        return R.success()
                .data("courseWebVo", courseWebVo)
                .data("chapterVoList", chapterVoList)
                .data("isBuy", isBuy);

    }

    @PostMapping("/eduservice/eduCourseFront/getCourseWebVoOrder/{courseId}")
    public CourseWebVoOrder getCourseWebVoOrder(@PathVariable("courseId") String courseId) {

        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);

        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo, courseWebVoOrder);

        return  courseWebVoOrder;

    }
}
