package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherList(long current, long size) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("id");
        Page<EduTeacher> pageParam = new Page<>(current, size);
        IPage<EduTeacher> teacherIPage = baseMapper.selectPage(pageParam, queryWrapper);

        List<EduTeacher> records = pageParam.getRecords();
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
}
