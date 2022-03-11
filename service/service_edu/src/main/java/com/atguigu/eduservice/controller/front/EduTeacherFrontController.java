package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EduTeacherFrontController
 * @Package com.atguigu.eduservice.controller.front
 * @Author yuanchaoxin
 * @Date 2022/3/8
 * @Version 1.0
 * @Description
 */
@RestController
@CrossOrigin
public class EduTeacherFrontController {

    @Resource
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/eduservice/eduteacherFront/listTeacher/{current}/{size}")
    public R getTeacherList(@PathVariable("current") long current, @PathVariable("size") long size) {

        Map<String,Object> map = eduTeacherService.getTeacherList(current, size);
        return R.success().data(map);
    }

    @GetMapping("/eduservice/eduteacherFront/getTeacherInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable("teacherId") String teacherId) {

        EduTeacher teacher = eduTeacherService.getById(teacherId);

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);

        return R.success().data("teacher", teacher).data("courseList", courseList);
    }
}
