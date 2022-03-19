package com.atguigu.statisticsservice.service;

import com.atguigu.statisticsservice.entity.StatisticsDaily;
import com.atguigu.statisticsservice.entity.vo.StatisticsDailyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-18
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsDailyByDay(String day);

    Map<String, Object> getStatisticsDailyData(StatisticsDailyVo statisticsDailyVo);
}
