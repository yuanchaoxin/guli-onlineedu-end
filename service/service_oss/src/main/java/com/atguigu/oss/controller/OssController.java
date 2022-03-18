package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName OssController
 * @Package com.atguigu.oss.controller
 * @Author yuanchaoxin
 * @Date 2022/2/25
 * @Version 1.0
 * @Description
 */
@RestController
@CrossOrigin
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping("/ossservice/oss/upload")
    public R uploadOssFile(MultipartFile file) {

        String url = ossService.uploadFileAvatar(file);
        return R.success().data("url", url);
    }

}
