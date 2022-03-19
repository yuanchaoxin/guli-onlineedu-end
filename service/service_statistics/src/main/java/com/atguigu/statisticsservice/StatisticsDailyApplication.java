package com.atguigu.statisticsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName StatisticsDailyApplication
 * @Package com.atguigu.statisticsservice
 * @Author yuanchaoxin
 * @Date 2022/3/18
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.statisticsservice.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StatisticsDailyApplication {

    public static void main(String[] args) {
         SpringApplication.run(StatisticsDailyApplication.class, args);
    }

}
