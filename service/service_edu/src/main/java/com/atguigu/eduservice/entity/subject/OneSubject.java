package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OneSubject
 * @Package com.atguigu.eduservice.entity.subject
 * @Author yuanchaoxin
 * @Date 2022/2/26
 * @Version 1.0
 * @Description
 */
@Data
public class OneSubject {

    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
