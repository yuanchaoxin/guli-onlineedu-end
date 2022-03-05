package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.servicebase.config.exception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitVodCilent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author 86178
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadAliyunVideo(MultipartFile file) {

        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteAliyunVideoById(String videoId) {

        try {
            if (org.springframework.util.StringUtils.isEmpty(videoId)) {
                return;
            }
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(videoId);

            client.getAcsResponse(deleteVideoRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除阿里云视频失败");
        }
    }

    @Override
    public void batchDeleteAliyunVideoByIds(List<String> videoIdList) {
        try {
            if (videoIdList == null || videoIdList.size() <= 0) {
                return;
            }
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");
            deleteVideoRequest.setVideoIds(videoIds);

            client.getAcsResponse(deleteVideoRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除阿里云视频失败");
        }
    }
}
