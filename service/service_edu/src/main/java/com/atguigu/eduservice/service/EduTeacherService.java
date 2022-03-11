package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-02-20
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherList(long current, long size);
}
