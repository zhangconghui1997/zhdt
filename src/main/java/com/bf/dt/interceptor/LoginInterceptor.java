package com.bf.dt.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bf.dt.config.ProjectConfig;
import com.bf.dt.controller.system.LoginController;
import com.bf.dt.entity.LoginToken;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.util.JwtUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    LoginController loginController;

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(ProjectConfig.TOKENHEADER)) {
                        String token = cookie.getValue();
                        MsgResult msgResult = loginController.check(token);
                        if (msgResult.getCode() == "200") {
                            return true;
                        } else {
                         /*   String uri = httpServletRequest.getRequestURI();
                            if (uri.contains(".html")){
                                return true;
                            }*/
                       /*     System.out.println(JSON.toJSONString(msgResult));
                            Cookie cookie0 = new Cookie(ProjectConfig.EXPIREDMESSAGE,msgResult.getMessage());
                            httpServletResponse.addCookie(cookie0);*/
                            httpServletResponse.sendRedirect("/login.html");
                         /*   httpServletResponse.addHeader(ProjectConfig.EXPIREDMESSAGE,JSON.toJSONString(msgResult));*/
                            return false;
                        }
                    }
                }
            }else {
                httpServletResponse.sendRedirect("/login.html");
            }
        httpServletResponse.sendRedirect("/login.html");
        return false;

    }

}
