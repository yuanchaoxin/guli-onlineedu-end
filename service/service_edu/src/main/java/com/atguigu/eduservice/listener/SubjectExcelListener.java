package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.execl.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @ClassName SubjectExcelListener
 * @Package com.atguigu.eduservice.listener
 * @Author yuanchaoxin
 * @Date 2022/2/26
 * @Version 1.0
 * @Description
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    // 不能实现数据库操作
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 读取excel内容，一行一行进行读取
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001,"数据为空");
        }

        //判断一级分类是否重复
        EduSubject oneEduSubject = existOneSubject(subjectData.getOneSubjectName(), eduSubjectService);
        //没有相同一级分类，进行添加
        if(oneEduSubject == null) {
            oneEduSubject = new EduSubject();
            oneEduSubject.setParentId("0");
            oneEduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(oneEduSubject);
        }

        String pid = oneEduSubject.getId();
        //判断二级分类是否重复
        EduSubject twoSubject = existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), oneEduSubject.getId());
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(pid);
            eduSubjectService.save(twoSubject);
        }
    }

    /**
     * 判断一级分类不能重复添加
     * @param title
     * @param eduSubjectService
     * @return
     */
    private EduSubject existOneSubject(String title, EduSubjectService eduSubjectService) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");

        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);

        return eduSubject;
    }

    /**
     * 判断二级分类不能重复
     * @param subjectService
     * @param title
     * @param pid
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String title,String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", pid);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }
    /**
     * 读取表头行
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    /**
     * 读取后操作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
