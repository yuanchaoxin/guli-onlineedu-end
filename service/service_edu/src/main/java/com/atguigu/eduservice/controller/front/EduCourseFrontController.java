package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.frontvo.CourseFrontVo;
import com.atguigu.eduservice.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping("/eduservice/eduCourseFront/listCourse/{current}/{size}")
    public R getCourseList(@PathVariable("current") long current,
                           @PathVariable("size") long size,
                           @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        Map<String,Object> map = eduCourseService.getCourseList(current, size, courseFrontVo);
        return R.success().data(map);
    }

    @GetMapping("/eduservice/eduCourseFront/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId) {

        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);

        List<ChapterVo> chapterVoList = eduChapterService.getAllChapterTreeByCourseId(courseId);

        return R.success().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVoList);
    }
}
