package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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
    public R addSubject(@RequestParam("file")MultipartFile multipartFile) {

        eduSubjectService.saveSubject(multipartFile, eduSubjectService);
        return R.success();
    }

    @GetMapping("/eduservice/subject/getAllSubjectTree")
    public R getAllSubjectTree() {

        List<OneSubject> list = eduSubjectService.getAllSubjectTree();
        return R.success().data("list", list);
    }
}

