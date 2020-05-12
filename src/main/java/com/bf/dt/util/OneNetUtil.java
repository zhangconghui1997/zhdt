package com.bf.dt.util;

import com.bf.dt.entity.DtSitePO;
import com.bf.dt.service.onenet.OneNetAreaService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OneNetUtil {


    /**
     * 获取已授权的场地列表，为空则查询全部列表
     * @param dtSiteService
     * @param userId
     * @param orgId
     * @return
     */
    public static List<DtSitePO> getSiteListByAuth(OneNetAreaService dtSiteService, String userId, String orgId){
        List<DtSitePO> dtSitePOS = new ArrayList<>();
        try {
            dtSitePOS = dtSiteService.retrieveByAuth(userId);
            if (dtSitePOS == null || dtSitePOS.size() == 0){
                dtSitePOS = dtSiteService.retrieveByYuanQuId(orgId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtSitePOS;
    }



    /**
     * 发送请求
     * @param URL
     * @return
     */
    public static String req(String URL){
        String result = "";
        try {
            //如果传进来有中文字符 可以使用
            // .encode 方法进行编码转换
            //String start = "杭州";    //转换前
            //start = URLEncoder.encode(start, "utf-8");  //转换中
            //System.out.printin(start)   //转换后  %E6%9D%AD%E5%B7%9E
            java.net.URL url = new URL(URL);
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1096.1 Safari/536.6");
            conn.setRequestProperty("content-type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setDoOutput(true);
            if (conn.getResponseCode() == 302) {
                System.out.println(302);
                return "error";
            }
            if (conn.getResponseCode() == 200) {
                System.out.println(200);
            }
            if (conn.getResponseCode() == 400) {
                System.out.println(conn.getResponseCode());
                return null;
            }
            if (conn.getResponseCode() == 403) {
                System.out.println(conn.getResponseCode());
                return null;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String s = "";
            while ((s = rd.readLine()) != null) {
                sb.append(s);
            }
            // System.out.println(sb);
            if (sb.length() == 0) {
                sb.append("[]");
            }
            result = sb.toString();
            System.out.println(result); //到这里就获取到结果了result是结果
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}



