package com.atguigu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName OssApplication
 * @Package com.atguigu.oss
 * @Author yuanchaoxin
 * @Date 2022/2/25
 * @Version 1.0
 * @Description
 */
@ComponentScan(basePackages = {"com.atguigu"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
         SpringApplication.run(OssApplication.class, args);
    }

}
