package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
public class EduChapterController {

    @Resource
    private EduChapterService eduChapterService;

    @GetMapping("/eduservice/chapter/getAllChapterTree/{courseId}")
    public R getAllChapterTree(@PathVariable("courseId") String courseId) {

        List<ChapterVo> list = eduChapterService.getAllChapterTreeByCourseId(courseId);
        return R.success().data("list", list);
    }
}

