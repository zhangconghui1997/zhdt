package com.bf.dt.controller;

import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.UserService;
import com.bf.dt.vo.UserMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired(required = false)
    UserService userService;


    @ResponseBody
    @RequestMapping("/login")
    public MsgResult login(String loginName,String password){
        System.out.println(loginName);

        MsgResult msgResult = userService.findByName(loginName,password);
        return  msgResult;

    }



    @ResponseBody
    @RequestMapping("/menus")
    public MsgResult menus(String uid){
        MsgResult msgResult = userService.findMenuByUser(uid);
        if (msgResult.getCode() == "200"){
            System.out.println(msgResult.getData());
        }

        return msgResult;
    }



}
