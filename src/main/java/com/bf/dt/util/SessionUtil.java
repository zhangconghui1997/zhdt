package com.bf.dt.util;

import com.bf.dt.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void main(String[] args) {
        String s = "/user/userList.html";
        int i = s.indexOf("/", s.indexOf("/")+1);
        String substring = s.substring(1, i);
        System.out.println(substring);
    }

}
