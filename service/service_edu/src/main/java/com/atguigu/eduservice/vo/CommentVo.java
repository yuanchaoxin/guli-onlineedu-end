package com.atguigu.eduservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommentVo
 * @Package com.atguigu.eduservice.vo
 * @Author yuanchaoxin
 * @Date 2022/3/15
 * @Version 1.0
 * @Description
 */
@Data
public class CommentVo {

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

}
