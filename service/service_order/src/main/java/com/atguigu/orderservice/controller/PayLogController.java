package com.atguigu.orderservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.orderservice.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-15
 */
@RestController
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;
    /**
     * 生成微信支付二维码接口
     * @param orderNo 订单号
     * @return
     */
    @GetMapping("/orderservice/payLog/createNative/{orderNo}")
    public R createNative(@PathVariable("orderNo") String orderNo) {
        // 返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNatvie(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return R.success().data(map);
    }

    /**
     * 查询订单支付状态
     * @param orderNo
     * @return
     */
    @GetMapping("/orderservice/payLog/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable("orderNo") String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        // 如果返回map里面不为空，通过map获取订单状态
        // 支付成功
        if(map.get("trade_state").equals("SUCCESS")) {
            // 添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.success().message("支付成功");
        }
        return R.success().code(25000).message("支付中");
    }
}

