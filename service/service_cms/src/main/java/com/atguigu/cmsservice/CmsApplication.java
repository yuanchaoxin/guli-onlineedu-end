package com.atguigu.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName CmsApplication
 * @Package com.atguigu.cms
 * @Author yuanchaoxin
 * @Date 2022/3/5
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.cmsservice.mapper")
public class CmsApplication {

    public static void main(String[] args) {
         SpringApplication.run(CmsApplication.class, args);
    }

}
