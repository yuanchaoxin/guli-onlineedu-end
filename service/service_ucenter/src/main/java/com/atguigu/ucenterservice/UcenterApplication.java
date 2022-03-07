package com.atguigu.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName UcenterApplication
 * @Package com.atguigu.ucenterservice
 * @Author yuanchaoxin
 * @Date 2022/3/6
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.ucenterservice.mapper")
public class UcenterApplication {
    
    public static void main(String[] args) {
         SpringApplication.run(UcenterApplication.class, args);
    }
    
}
