package com.bf.dt.interceptor;

import com.bf.dt.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "index","video","user","role","menu"
        };

        String uri = httpServletRequest.getRequestURI();
        if (uri.contains("login")){
            return true;
        }
        int i = uri.indexOf("/", uri.indexOf("/")+1);
        String page;
        if (i!=-1){
            page = uri.substring(1, i);
        }else {
            page = StringUtils.remove(uri, contextPath+"/");
        }


      /*  uri = StringUtils.remove(uri, contextPath+"/");*/



       if(begingWith(page, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if(user==null) {
                httpServletResponse.sendRedirect("/login.html");
                return false;
            }
        }





        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
