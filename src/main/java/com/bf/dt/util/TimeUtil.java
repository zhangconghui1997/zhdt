package com.bf.dt.util;

import com.bf.dt.dao.system.UserMapper;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeUtil {


    public static String timeToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }




    public void renwu(UserMapper userMapper, String a,String name){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    User user = userMapper.findByName(name);
                    String status = user.getStatus();
                    System.out.println(status);
                    if (a.equals(status)){
                        System.out.println(name + ":终止");
                        service.shutdown();
                    }
                    System.out.println(name+":"+status);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        service.scheduleWithFixedDelay(runnable, 0, 20000, TimeUnit.MILLISECONDS);




        }


}
