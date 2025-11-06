package com.life.finmate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.life.finmate")
public class FinMateBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinMateBeApplication.class, args);
    }

}
