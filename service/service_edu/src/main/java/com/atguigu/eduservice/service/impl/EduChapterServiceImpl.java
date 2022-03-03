package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.config.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getAllChapterTreeByCourseId(String courseId) {

        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);

        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);

        ArrayList<ChapterVo> list = new ArrayList<>();

        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            list.add(chapterVo);

            for (EduVideo eduVideo : eduVideoList) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo, videoVo);

                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    chapterVo.getChildren().add(videoVo);
                }
            }
        }
        return list;
    }

    @Override
    public boolean deleteChapter(String chapterId) {

        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(queryWrapper);
        if (count > 0) {
            throw new GuliException(20001, "该章节下还存在小节，请先删除小节");
        }

        int result = baseMapper.deleteById(chapterId);

        return result > 0;
    }

    @Override
    public void deleteChapterByCourseId(String courseId) {

        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);

        baseMapper.delete(queryWrapper);
    }
}
