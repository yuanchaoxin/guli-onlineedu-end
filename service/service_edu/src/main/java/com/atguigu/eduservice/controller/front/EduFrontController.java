package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EduFrontController
 * @Package com.atguigu.eduservice.controller.front
 * @Author yuanchaoxin
 * @Date 2022/3/5
 * @Version 1.0
 * @Description
 */
@RestController
@CrossOrigin
public class EduFrontController {

    @Resource
    private EduTeacherService eduTeacherService;

    @Resource
    private EduCourseService eduCourseService;

    /**
     * 查询前4名讲师和前8名课程
     * @return
     */
    @GetMapping("/eduservice/front/index")
    public R index() {

        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");

        List<EduTeacher> teacherList = eduTeacherService.list(teacherQueryWrapper);

        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(courseQueryWrapper);

        return R.success().data("teacherList", teacherList)
                .data("courseList", courseList);
    }
}
