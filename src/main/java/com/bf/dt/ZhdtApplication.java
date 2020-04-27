package com.bf.dt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bf.dt.dao")
public class ZhdtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhdtApplication.class, args);
    }

}
