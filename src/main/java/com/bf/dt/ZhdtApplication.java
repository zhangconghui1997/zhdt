package com.bf.dt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZhdtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhdtApplication.class, args);
    }

}
