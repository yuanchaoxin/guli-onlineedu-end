package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.vo.CommentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-14
 */
public interface EduCommentService extends IService<EduComment> {

    IPage<EduComment> getCommentList(Page<EduComment> page, String courseId);

    boolean addComment(CommentVo commentVo, HttpServletRequest httpRequest);
}
