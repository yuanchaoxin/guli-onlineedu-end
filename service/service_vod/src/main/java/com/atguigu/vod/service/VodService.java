package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @Author yuanchaoxin
 * @Date 2022/2/25 23:51
 * @Version 1.0
 */
public interface VodService {

    String uploadAliyunVideo(MultipartFile file);

    void deleteAliyunVideoById(String videoId);

    void batchDeleteAliyunVideoByIds(List<String> videoIdList);

    String getPlayAuth(String videoId);
}
