package com.atguigu.statisticsservice.task;

import com.atguigu.statisticsservice.service.StatisticsDailyService;
import com.atguigu.statisticsservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName StatisticsDailyTask
 * @Package com.atguigu.statisticsservice.task
 * @Author yuanchaoxin
 * @Date 2022/3/19
 * @Version 1.0
 * @Description
 */
@Component
public class StatisticsDailyScheduledTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void taskTest() {
        System.out.println("task runing");
    }

    /**
     * 每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void createStatisticsDaily() {
        statisticsDailyService.createStatisticsDailyByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

}
