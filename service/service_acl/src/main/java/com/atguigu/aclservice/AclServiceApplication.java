package com.atguigu.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 86178
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.aclservice.mapper")
public class AclServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AclServiceApplication.class, args);
    }

}
