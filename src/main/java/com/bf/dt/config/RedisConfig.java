package com.bf.dt.config;
import com.bf.dt.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {
    @Bean
    public JedisUtil createJU(){
        return JedisUtil.getInstance();
    }
}