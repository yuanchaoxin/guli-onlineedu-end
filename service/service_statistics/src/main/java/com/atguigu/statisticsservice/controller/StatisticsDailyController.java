package com.atguigu.statisticsservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.statisticsservice.entity.vo.StatisticsDailyVo;
import com.atguigu.statisticsservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-18
 */
@RestController
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 根据时间生成单天的统计数据
     * @param day
     * @return
     */
    @PostMapping("/statisticsservice/statistics/createStatisticsDailyByDay/{day}")
    public R createStatisticsDailyByDay(@PathVariable("day") String day) {

        statisticsDailyService.createStatisticsDailyByDay(day);
        return R.success();
    }

    /**
     * 根据条件获取统计数据生成折线图
     * @param statisticsDailyVo
     * @return
     */
    @PostMapping("/statisticsservice/statistics/getStatisticsDailyData")
    public R getStatisticsDailyData(@RequestBody StatisticsDailyVo statisticsDailyVo) {

        Map<String,Object> map = statisticsDailyService.getStatisticsDailyData(statisticsDailyVo);
        return R.success().data(map);
    }
}

