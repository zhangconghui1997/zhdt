package com.bf.dt.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.bf.dt.config.ProjectConfig;
import com.bf.dt.entity.LoginToken;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import com.bf.dt.util.EncryptionUtil;

import com.bf.dt.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
@RequestMapping("user")
public class LoginController {
    @Autowired
    UserService userService;


    /**
     * 用户登录接口
     * @param loginName  登录名称
     * @param password   登录密码
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public MsgResult login(String loginName, String password, HttpServletResponse response){
      return userService.findByName(loginName,password,response);
    }

    /**
     *检验token有效性
     */
    //检查是否有效
    @GetMapping("checklogin")
    public MsgResult check(@RequestParam("token")String token){
        return userService.checkLogin(token);
    }


    /**
     * 获取该用户下的所有菜单
     * @param uid
     * @return
     */

    @ResponseBody
    @RequestMapping("menus")
    public MsgResult menus(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ProjectConfig.TOKENHEADER)) {
                token = cookie.getValue();
                String s = JwtUtil.parseJWT(token);
                LoginToken loginToken = JSONObject.parseObject(s, LoginToken.class);
                MsgResult msgResult = userService.findMenuByUser(loginToken.getUid());
                return msgResult;
            }
        }
        response.sendRedirect("/login.html");
        return MsgResult.error("500","认证失败");

    }


    /**
     * 分页查询所有用户列表
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public MsgResult list(String page, String limit){
        MsgResult msgResult = userService.findAll(page,limit);
        if (msgResult.getCode() == "200"){
            System.out.println(msgResult.getData());
        }
        return msgResult;
    }


    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteById")
    public MsgResult deleteById(String id){
        MsgResult msgResult = userService.deleteById(id);

        return msgResult;
    }


    /**
     * 根据用户id查找用户名
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("findNameById")
    public MsgResult findNameById(String uid, HttpServletRequest request){
        try {
            User user = (User)request.getSession().getAttribute("user");
            return MsgResult.success("200",user.getRealName(),"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","失败");
        }


    }




/*    public static void main(String[] args) {
*//*        String s = EncryptionUtil.AESEnc(EncryptionUtil.key, "123");
        String s1 = EncryptionUtil.AESEnc(EncryptionUtil.key, "1234");
        String s2 = EncryptionUtil.AESEnc(EncryptionUtil.key, "12345");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);*//*
        String version1 = SpringBootVersion.getVersion();
        String version = SpringVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);

    }*/




}
