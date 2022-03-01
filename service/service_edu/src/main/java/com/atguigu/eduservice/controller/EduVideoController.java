package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-02-27
 */
@RestController
@CrossOrigin
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    @PostMapping("/eduservice/video/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {

        eduVideoService.save(eduVideo);
        return R.success();
    }

    @DeleteMapping("/eduservice/video/{id}")
    public R deleteVideo(@PathVariable("id") String id) {

        eduVideoService.removeById(id);
        return R.success();
    }

    @PostMapping("/eduservice/video/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {

        eduVideoService.updateById(eduVideo);
        return R.success();
    }

    @GetMapping("/eduservice/video/getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable("id") String id) {

        EduVideo eduVideo = eduVideoService.getById(id);
        return R.success().data("eduVideo", eduVideo);
    }
}

