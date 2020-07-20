package com.bf.dt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
/*@EnableScheduling*/
//@ServletComponentScan(basePackages = "com.bf.dt.config")
@EnableCaching

public class ZhdtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhdtApplication.class, args);
    }

}
