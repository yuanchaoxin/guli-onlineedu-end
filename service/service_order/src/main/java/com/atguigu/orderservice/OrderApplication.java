package com.atguigu.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName OrderApplication
 * @Package com.atguigu.orderservice
 * @Author yuanchaoxin
 * @Date 2022/3/15
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
@MapperScan("com.atguigu.orderservice.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
         SpringApplication.run(OrderApplication.class, args);
    }

}
