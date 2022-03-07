package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.feign.VodFeignService;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Resource
    private VodFeignService vodFeignService;

    @Override
    public void deleteVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        videoQueryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(videoQueryWrapper);

        if (videoList != null && videoList.size() > 0) {
            List<String> videoIds = videoList.stream().map(e -> e.getVideoSourceId()).collect(Collectors.toList());

            R result = vodFeignService.batchDeleteAliyunVideo(videoIds);
            if (!result.getStatus()) {
                throw new GuliException(result.getCode(),result.getMessage());
            }
        }

        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);

        baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteVideoById(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);

        baseMapper.deleteById(id);

        if (eduVideo != null && StringUtils.isNotBlank(eduVideo.getVideoSourceId())) {
            R result = vodFeignService.deleteAliyunVideoById(eduVideo.getVideoSourceId());
            if (!result.getStatus()) {
                throw new GuliException(result.getCode(),result.getMessage());
            }
        }

    }
}
