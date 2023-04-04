package com.example.hdy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hdy.dao")
public class HdyFitnessApplication {
    public static void main(String[] args) {
        SpringApplication.run(HdyFitnessApplication.class, args);
    }

}

