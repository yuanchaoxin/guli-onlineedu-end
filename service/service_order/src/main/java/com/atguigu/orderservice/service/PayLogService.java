package com.atguigu.orderservice.service;

import com.atguigu.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
public interface PayLogService extends IService<PayLog> {

    Map createNatvie(String orderNo);

    void updateOrdersStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
