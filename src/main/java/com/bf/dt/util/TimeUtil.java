package com.bf.dt.util;

import com.bf.dt.dao.system.UserMapper;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        service.scheduleWithFixedDelay(runnable, 0, 200000, TimeUnit.MILLISECONDS);




        }




    public static String getTime(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    //计算时间  获取多少天之后
    public static Date getDays(int days){
        //日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,days);
        return calendar.getTime();
    }
    //多少月后
    public static Date getMonths(int months){
        //日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,months);
        return calendar.getTime();
    }
    //多少年后
    public static Date getYear(int year){
        //日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,year);
        return calendar.getTime();
    }
    //多少分钟
    public static Date getMinutes(int minutes){
        //日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minutes);
        return calendar.getTime();
    }
    //计算今天剩余秒
    public static int getLastSeconds(){
        Date currdate=new Date();
        //获取今天的最后一秒
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date lastdate=sdf.parse(getDate()+" 23:59:59");
            return (int)((lastdate.getTime()-currdate.getTime())/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //获取当前距离指定日期的天数
    public static int getDistanceDays(Date date){
        Calendar calendar=Calendar.getInstance();
        return (int)(calendar.getTime().getTime()/1000/24/3600-date.getTime()/1000/24/3600);
    }

    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-12);
        System.out.println(getDistanceDays(calendar.getTime()));
    }

    public static String getFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }



}
