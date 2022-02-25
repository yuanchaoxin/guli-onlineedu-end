package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName EduApplaication
 * @Package com.atguigu.eduservice
 * @Author yuanchaoxin
 * @Date 2022/2/20
 * @Version 1.0
 * @Description
 */
@EnableSwagger2
@ComponentScan(basePackages = {"com.atguigu"})
@SpringBootApplication
public class EduApplaication {

    public static void main(String[] args) {
         SpringApplication.run(EduApplaication.class, args);
    }

}
