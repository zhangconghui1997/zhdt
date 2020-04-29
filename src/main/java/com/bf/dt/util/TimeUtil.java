package com.bf.dt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String timeToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }
}
