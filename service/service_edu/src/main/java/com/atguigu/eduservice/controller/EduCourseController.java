package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.web.bind.annotation.*;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@RestController
@CrossOrigin
//@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;

    /**
     * 新增课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/eduservice/course/addCourse")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = eduCourseService.addCourse(courseInfoVo);
        return R.success().data("courseId", courseId);
    }

    /**
     * 根据id查询课程信息
     * @param courseId
     * @return
     */
    @GetMapping("/eduservice/course/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.success().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 更新课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/eduservice/course/updateCourse")
    public R updateCourse(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourse(courseInfoVo);
        return R.success();
    }
}

