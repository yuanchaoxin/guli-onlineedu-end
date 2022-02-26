package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-02-26
 */
@RestController
@CrossOrigin
//@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    /**
     * 添加课程分类
     * @param multipartFile
     * @return
     */
    @PostMapping("/eduservice/subject/add")
    public R addSubject(MultipartFile multipartFile) {

        eduSubjectService.saveSubject(multipartFile, eduSubjectService);
        return R.success();
    }
}

