package com.atguigu.msmservice.service.impl;

import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MsmServiceImpl
 * @Package com.atguigu.msmservice.service.impl
 * @Author yuanchaoxin
 * @Date 2022/3/6
 * @Version 1.0
 * @Description
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean sendMsg(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        String existCode = redisTemplate.opsForValue().get(phone);

        if (!StringUtils.isEmpty(existCode)) {
            return true;
        }

        String code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        /*DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
        IAcsClient client = new DefaultAcsClient(profile);

        // 设置相关固定的参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 设置发送相关的参数
        // 手机号
        request.putQueryParameter("PhoneNumbers",phone);
        // 申请阿里云 签名名称
        request.putQueryParameter("SignName","我的谷粒在线教育网站");
        // 申请阿里云 模板code
        request.putQueryParameter("TemplateCode","SMS_180051135");
        // 验证码数据，转换json数据传递
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));*/

        try {
            // 最终发送
            // CommonResponse response = client.getCommonResponse(request);
            boolean success = true; //response.getHttpResponse().isSuccess();
            if (success) {
                redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            }
            System.out.println("验证码：" + code);
            return success;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
