package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.frontvo.CourseFrontVo;
import com.atguigu.eduservice.frontvo.CourseWebVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.CourseQueryVo;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private EduChapterService eduChapterService;

    @Override
    public String addCourse(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result <= 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }

        String eduCourseId = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourseId);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());

        eduCourseDescriptionService.save(eduCourseDescription);

        return eduCourseId;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourse(CourseInfoVo courseInfoVo) {

        // 修改课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int result = baseMapper.updateById(eduCourse);

        if (result <= 0) {
            throw new GuliException(20001, "更新课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Override
    public IPage<EduCourse> pageCourseCondition(Page<EduCourse> page, CourseQueryVo courseQueryVo) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if (courseQueryVo == null) {
            courseQueryVo = new CourseQueryVo();
        }

        String title  = courseQueryVo.getTitle();
        String status = courseQueryVo.getStatus();
        String begin  = courseQueryVo.getBegin();
        String end    = courseQueryVo.getEnd();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        queryWrapper.orderByDesc("view_count", "gmt_create");

        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, queryWrapper);
        return eduCourseIPage;
    }

    @Override
    public void deleteCourseById(String courseId) {

        // 删除小节
        eduVideoService.deleteVideoByCourseId(courseId);

        // 删除章节
        eduChapterService.deleteChapterByCourseId(courseId);

        // 删除描述
        eduCourseDescriptionService.removeById(courseId);

        // 删除课程
        int i = baseMapper.deleteById(courseId);

        if (i <= 0) {
            throw new GuliException(20001, "删除课程失败");
        }
    }

    @Override
    public Map<String, Object> getCourseList(long current, long size, CourseFrontVo courseFrontVo) {

        Page<EduCourse> pageParam = new Page<>(current, size);
        if (courseFrontVo == null) {
            courseFrontVo = new CourseFrontVo();
        }
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            queryWrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long oldCurrent = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long oldSize = pageParam.getSize();
        long total = pageParam.getTotal();
        // 是否有下一页
        boolean hasNext = pageParam.hasNext();
        // 是否有上一页
        boolean hasPrevious = pageParam.hasPrevious();

        // 把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", oldCurrent);
        map.put("pages", pages);
        map.put("size", oldSize);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getFrontCourseInfo(String courseId) {
        return baseMapper.getFrontCourseInfo(courseId);
    }
}
