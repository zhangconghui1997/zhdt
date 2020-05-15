package com.bf.dt.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskUtil{
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
   /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy--mm--dd");

    //每隔三秒执行一次任务
    @Scheduled(fixedRate = 3000)
    public void cronJobs(){
        System.out.println("时间为："+simpleDateFormat.format(new Date()));
    }*/
