package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.CourseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/eduservice/course/getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable("courseId") String courseId) {

        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(courseId);
        return R.success().data("coursePublish", coursePublishVo);
    }

    /**
     * 课程最终发布
     * 修改课程状态
     * @param id
     * @return
     */
    @PostMapping("/eduservice/course/publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        // 设置课程发布状态
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.success();
    }

    @ApiOperation("多条件分页查询讲师")
    @PostMapping("/eduservice/course/pageCourseCondition/{current}/{size}")
    public R pageCourseCondition(@ApiParam(name = "current", value = "当前页码", required = true)
                                  @PathVariable("current") long current,
                                  @ApiParam(name = "size", value = "每页记录数", required = true)
                                  @PathVariable("size") long size,
                                  @RequestBody(required = false) CourseQueryVo courseQueryVo) {

        Page<EduCourse> page = new Page<>(current, size);

        IPage<EduCourse> eduCoursePage = eduCourseService.pageCourseCondition(page, courseQueryVo);
        return R.success()
                .data("total", eduCoursePage.getTotal())
                .data("rows", eduCoursePage.getRecords());
    }

    @DeleteMapping("/eduservice/course/deleteCourseById/{courseId}")
    public R deleteCourseById(@PathVariable("courseId") String courseId) {

        eduCourseService.deleteCourseById(courseId);
        return R.success();
    }
}

