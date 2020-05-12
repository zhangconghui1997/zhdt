package com.bf.dt.controller.system;

import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import com.bf.dt.util.EncryptionUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired(required = false)
    UserService userService;


    /**
     * 用户登录接口
     * @param loginName  登录名称
     * @param password   登录密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public MsgResult login(String loginName, String password, HttpSession session){
        try {

            String s = EncryptionUtil.AESEnc(EncryptionUtil.key, password);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, s);
            subject.login(usernamePasswordToken);
            MsgResult msgResult = userService.findByName(loginName,s);
            session.setAttribute("user", msgResult.getData());
            return  msgResult;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            MsgResult msgResult = MsgResult.error("500", "认证失败");
            return  msgResult;
        }
    }


    /**
     * 获取该用户下的所有菜单
     * @param uid
     * @return
     */

    @ResponseBody
    @RequestMapping("/menus")
    public MsgResult menus(String uid){
        MsgResult msgResult = userService.findMenuByUser(uid);
        if (msgResult.getCode() == "200"){
            System.out.println(msgResult.getData());
        }

        return msgResult;
    }


    /**
     * 分页查询所有用户列表
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
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
    @RequestMapping("/deleteById")
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
    @RequestMapping("/findNameById")
    public MsgResult findNameById(String uid, HttpServletRequest request){
        try {
            User user = (User)request.getSession().getAttribute("user");
            return MsgResult.success("200",user.getRealName(),"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","失败");
        }


    }




    public static void main(String[] args) {
/*        String s = EncryptionUtil.AESEnc(EncryptionUtil.key, "123");
        String s1 = EncryptionUtil.AESEnc(EncryptionUtil.key, "1234");
        String s2 = EncryptionUtil.AESEnc(EncryptionUtil.key, "12345");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);*/
        String version1 = SpringBootVersion.getVersion();
        String version = SpringVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);

    }




}
