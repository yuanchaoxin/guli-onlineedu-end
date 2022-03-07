package com.atguigu.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName MsmApplication
 * @Package com.atguigu.msmservice
 * @Author yuanchaoxin
 * @Date 2022/3/6
 * @Version 1.0
 * @Description
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.atguigu")
public class MsmApplication {
    
    public static void main(String[] args) {
         SpringApplication.run(MsmApplication.class, args);
    }
    
}
