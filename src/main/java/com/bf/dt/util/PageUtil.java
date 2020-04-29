package com.bf.dt.util;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

    /**
     *
     * @param page  第几页
     * @param limit  每页几条数据
     * @return
     */
    public static Map<String,Object> getPage(String pageTemp,String limitTemp){
        HashMap<String, Object> map = new HashMap<>();
        int page = Integer.valueOf(pageTemp);
        int limit = Integer.valueOf(limitTemp);

        int start = (page-1)*limit;
        map.put("start",start);
        map.put("limit",limit);
        return map;
    }

}
