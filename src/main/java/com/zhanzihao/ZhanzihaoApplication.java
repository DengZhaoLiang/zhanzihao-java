package com.zhanzihao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan(value = "com.zhanzihao.mapper")
@SpringBootApplication
public class ZhanzihaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhanzihaoApplication.class, args);
    }

}
