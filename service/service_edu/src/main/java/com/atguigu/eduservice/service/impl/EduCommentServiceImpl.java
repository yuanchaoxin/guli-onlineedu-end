package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.feign.MemberFeignService;
import com.atguigu.eduservice.feign.VodFeignService;
import com.atguigu.eduservice.mapper.EduCommentMapper;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-14
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Resource
    private MemberFeignService memberFeignService;

    @Resource
    private VodFeignService vodFeignService;

    @Override
    public IPage<EduComment> getCommentList(Page<EduComment> page, String courseId) {

        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        IPage<EduComment> eduCommentIPage = baseMapper.selectPage(page, queryWrapper);
        return eduCommentIPage;
    }

    @Override
    public boolean addComment(CommentVo commentVo, HttpServletRequest httpRequest) {

        String jwtToken = httpRequest.getHeader("token");
        R memberInfo = memberFeignService.getMemberInfo(jwtToken);

        Map member = (Map) memberInfo.getData().get("member");

        EduComment comment = new EduComment();

        comment.setContent(commentVo.getContent());
        comment.setCourseId(commentVo.getCourseId());
        comment.setTeacherId(commentVo.getTeacherId());
        comment.setMemberId(String.valueOf(member.get("id")));
        comment.setAvatar(String.valueOf(member.get("avatar")));
        comment.setNickname(String.valueOf(member.get("nickname")));
        comment.setIsDeleted(false);
        int insert = baseMapper.insert(comment);
        return insert > 0;
    }
}
