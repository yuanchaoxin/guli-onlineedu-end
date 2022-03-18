package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
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

    /**
     * 根据课程id查询章节
     * @param courseId
     * @return
     */
    @GetMapping("/eduservice/chapter/getAllChapterTree/{courseId}")
    public R getAllChapterTree(@PathVariable("courseId") String courseId) {

        List<ChapterVo> list = eduChapterService.getAllChapterTreeByCourseId(courseId);
        return R.success().data("list", list);
    }

    /**
     * 新增章节
     * @param eduChapter
     * @return
     */
    @PostMapping("/eduservice/chapter/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {

        eduChapterService.save(eduChapter);
        return R.success();
    }

    /**
     * 根据id查询章节
     * @param chapterId
     * @return
     */
    @GetMapping("/eduservice/chapter/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable("chapterId") String chapterId) {

        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.success().data("eduChapter", eduChapter);
    }

    /**
     * 更新章节
     * @param eduChapter
     * @return
     */
    @PostMapping("/eduservice/chapter/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {

        eduChapterService.updateById(eduChapter);
        return R.success();
    }

    @DeleteMapping("/eduservice/chapter/{chapterId}")
    public R deleteChapter(@PathVariable("chapterId") String chapterId) {

        boolean flag = eduChapterService.deleteChapter(chapterId);
        if (!flag) {
            return R.error();
        }
        return R.success();

    }

}

