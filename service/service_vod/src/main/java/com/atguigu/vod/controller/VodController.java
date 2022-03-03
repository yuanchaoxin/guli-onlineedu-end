package com.atguigu.vod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.vod.service.VodService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/2/25 23:51
 * @Version 1.0
 */
@RestController
@CrossOrigin
public class VodController {

    @Resource
    private VodService vodService;

    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @PostMapping("/vodservice/video/uploadAliyunVideo")
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file) {

        //返回上传视频id
        String videoId = vodService.uploadAliyunVideo(file);
        return R.success().data("videoId",videoId);
    }

    @DeleteMapping("/vodservice/video/deleteAliyunVideoById/{videoId}")
    public R deleteAliyunVideoById(@PathVariable("videoId") String videoId) {

        vodService.deleteAliyunVideoById(videoId);
        return R.success();
    }
}
