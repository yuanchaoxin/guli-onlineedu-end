package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.vo.CommentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-14
 */
@RestController
@CrossOrigin
public class EduCommentController {

    @Resource
    private EduCommentService eduCommentService;

    @GetMapping("/eduservice/comment/getCommentList/{current}/{size}/{courseId}")
    public R getCommentList(@PathVariable("current") long current,
                            @PathVariable("size") long size,
                            @PathVariable("courseId") String courseId) {
        Page<EduComment> page = new Page<>(current, size);
        IPage<EduComment> eduCommentIPage = eduCommentService.getCommentList(page, courseId);
        return R.success()
                .data("total", eduCommentIPage.getTotal())
                .data("rows", eduCommentIPage.getRecords());
    }

    @PostMapping("/eduservice/comment/addComment")
    public R addComment(@RequestBody CommentVo commentVo, HttpServletRequest httpRequest) {

        boolean result = eduCommentService.addComment(commentVo, httpRequest);
        if (!result) {
            return R.error();
        }
        return R.success();
    }
}

