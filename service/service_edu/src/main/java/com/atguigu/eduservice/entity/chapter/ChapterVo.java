package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Package com.atguigu.eduservice.entity.chapter
 * @Author yuanchaoxin
 * @Date 2022/2/27
 * @Version 1.0
 * @Description
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
