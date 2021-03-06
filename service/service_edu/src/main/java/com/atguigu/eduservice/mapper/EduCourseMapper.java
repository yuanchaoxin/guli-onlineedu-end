package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.frontvo.CourseWebVo;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getFrontCourseInfo(String courseId);
}
