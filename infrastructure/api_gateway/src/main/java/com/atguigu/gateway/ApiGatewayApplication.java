package com.atguigu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ApiGatewayApplication
 * @Package com.atguigu.gateway
 * @Author yuanchaoxin
 * @Date 2022/3/19
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    
    public static void main(String[] args) {
         SpringApplication.run(ApiGatewayApplication.class, args);
    }
    
}
