package com.atguigu.eduservice.entity.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    @ExcelProperty(index = 0, value = "一级分类")
    private String oneSubjectName;
    @ExcelProperty(index = 1, value = "二级分类")
    private String twoSubjectName;
}
