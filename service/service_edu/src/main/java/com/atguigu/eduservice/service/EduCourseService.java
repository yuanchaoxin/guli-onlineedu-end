package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.frontvo.CourseFrontVo;
import com.atguigu.eduservice.frontvo.CourseWebVo;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.CourseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourse(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourse(com.atguigu.eduservice.entity.vo.CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);

    IPage<EduCourse> pageCourseCondition(Page<EduCourse> page, CourseQueryVo courseQueryVo);

    void deleteCourseById(String courseId);

    Map<String, Object> getCourseList(long current, long size, CourseFrontVo courseFrontVo);

    CourseWebVo getFrontCourseInfo(String courseId);
}
