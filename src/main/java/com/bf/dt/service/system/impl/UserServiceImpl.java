package com.bf.dt.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.bf.dt.config.ProjectConfig;
import com.bf.dt.dao.system.MenuMapper;
import com.bf.dt.dao.system.RoleMenuMapper;
import com.bf.dt.dao.system.UserMapper;
import com.bf.dt.dao.system.UserRoleMapper;
import com.bf.dt.entity.LoginToken;
import com.bf.dt.entity.Menu;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import com.bf.dt.util.*;
import com.bf.dt.vo.UserMenu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    IdGenerator idGenerator;
    @Override
    public MsgResult findByName(String loginName, String password, HttpServletResponse response){

        /*
          public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            User user = userService.findByUsername(username);
            if (!user.isEnabled()) {
                return ResultFactory.buildFailResult("该用户已被禁用");
            }
            return ResultFactory.buildSuccessResult(username);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账号不存在");
        }
    }


    String userName = token.getPrincipal().toString();
        User user = userService.findByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;


        */

        String s = EncryptionUtil.AESEnc(EncryptionUtil.key, password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, s);
        User user = userMapper.findByName(loginName);
        String key=ProjectConfig.PCOUNT+user.getUuid();
        if (user != null){
            if (jedisUtil.exists(ProjectConfig.USERSD+user.getUuid())){
                return MsgResult.error("500","账号锁定中，剩余时间："+jedisUtil.ttl(ProjectConfig.USERSD+user.getUuid()));
            }else {
                try {
                    subject.login(usernamePasswordToken);
                    //生成令牌唯一id
                    String id = idGenerator.nextId()+"";
                    //有效载荷部分
                    LoginToken loginToken = new LoginToken(id,loginName,user.getUuid());
                    //生成jwt令牌
                    String token = JwtUtil.createJWT(loginToken.getId(), JSON.toJSONString(loginToken));
                    //存储redis
                    jedisUtil.setex(user.getUuid()+":"+ProjectConfig.JWT,1800,token);
                    if (jedisUtil.exists(key)){
                        jedisUtil.del(key);
                    }
                    response.addHeader(ProjectConfig.TOKENHEADER,token);
                    return MsgResult.success("200",user,"登录成功");

                }catch (IncorrectCredentialsException e){
                    jedisUtil.setex(key+"_"+System.currentTimeMillis(),600,"1");
                    Set<String> set=jedisUtil.keys(key+"*");
                    if(set.size()==3){
                        //将当前账号冻结 1小时
                        jedisUtil.setex(ProjectConfig.USERSD+user.getUuid(),3600,"10分钟连续失败三次冻结账号");
                        jedisUtil.del(key);
                        return MsgResult.error("500","连续多次账号或密码错误，账号被锁定，请1小时之后再来登录");
                    }
                    return MsgResult.error("500","用户名或密码错误");
                }
            }
        }else {
            return MsgResult.error("500","用户名或密码错误");
        }











/*
        User user = userMapper.findByName(loginName);
        if (user != null){
            if (jedisUtil.exists(ProjectConfig.USERSD+user.getUuid())){
            return MsgResult.error("500","账号锁定中，剩余时间："+jedisUtil.ttl(ProjectConfig.USERSD+user.getUuid()));
            }else {
                if (passwrod.equals(user.getPassword())){
                    //生成令牌唯一id
                    String id = idGenerator.nextId()+"";
                    //有效载荷部分
                    LoginToken loginToken = new LoginToken(id,loginName,user.getUuid());
                    //生成jwt令牌
                    String token = JwtUtil.createJWT(loginToken.getId(), JSON.toJSONString(loginToken));
                    //存储redis
                    jedisUtil.setex(user.getUuid()+":"+ProjectConfig.JWT,1800,token);
                    response.addHeader(ProjectConfig.TOKENHEADER,token);
                    return MsgResult.success("200",user,"登录成功");
                }else {
                    String key=ProjectConfig.PCOUNT+user.getUuid();
                    jedisUtil.setex(key+"_"+System.currentTimeMillis(),600,"1");
                    Set<String> set=jedisUtil.keys(key+"*");
                    if(set.size()==3){
                        //将当前账号冻结 1小时
                        jedisUtil.setex(ProjectConfig.USERSD+user.getUuid(),3600,"10分钟连续失败三次冻结账号");
                        jedisUtil.del(key+"*");
                        return MsgResult.error("500","连续多次账号或密码错误，账号被锁定，请1小时之后再来登录");
                    }
                    return MsgResult.error("500","用户名或密码错误");
                }
            }
        }else {
            return MsgResult.error("500","用户名或密码错误");
        }*/

    }


    @Override
    public MsgResult findMenuByUser(String uid) {
        //该用户下的所有菜单
        List<UserMenu> userMenus = new ArrayList<>();
        try {
            //查询该用户下的所有权限id
            List<String> rids = userRoleMapper.findByUid(uid);
            //该用户对应所有的菜单id（子菜单）
            List<String> mids = new ArrayList<>();
            //所有得父菜单id
            List<String> midps = new ArrayList<>();
            //该用户下所有的父菜单id
            List<String> newpid = new ArrayList<>();
            for (String r : rids) {
                //通过角色id查询角色下的所有菜单
                List<String> byRid = roleMenuMapper.findByRid(r);
                for (String s: byRid) {
                    mids.add(s);
                }
            }

            //查询所有得父菜单id
            midps = menuMapper.findByMain();

                for (String s:mids) {
                    //根据id查询实体类
                    Menu menu = menuMapper.selectByPrimaryKey(s);
                    if (!newpid.contains(menu.getParentid())){
                        //将该用户下所有的父菜单id放到newpid
                        newpid.add(menu.getParentid());
                    }
                }


                for (String s: newpid) {
                    //递归查询（该用户下所有的父菜单，以及父菜单下所有的子菜单）
                    UserMenu byMid = menuMapper.findByMid(s);
                    userMenus.add(byMid);
                }

                for (UserMenu userMenu: userMenus) {
                    List<UserMenu> childrens = userMenu.getChildren();
                    for (UserMenu um: childrens) {
                        //该用户拥有的菜单id为true，未拥有的为false
                        if (mids.contains(um.getUuid())){
                            um.setChecked(true);
                        }else {
                            um.setChecked(false);
                        }
                    }
                }



            return MsgResult.success("200",userMenus,"查询成功");

        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询权限失败");
        }

    }

    @Override
    public MsgResult findAll(String page,String limit) {
        Map<String, Object> map = new HashMap<>();
        //处理分页参数
        if (page == null || page == "" || limit == null || limit == ""){
            map = null;
        }else {
             map = PageUtil.getPage(page, limit);
        }

        try {
            List<User> all = userMapper.findAll(map);
            //总共多少条数据
            Integer count = userMapper.count();
            //根据layui的要求，返会所需要的数据
            return MsgResult.page("0",all,"查询成功",count);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询用户列表失败");
        }

    }

    @Override
    public MsgResult deleteById(String id) {
        try {
            //删除用户
            userMapper.deleteById(id);
            return MsgResult.success("200",null,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","删除失败");
        }
    }

    @Override
    public MsgResult findById(String uuid) {
        try {
            User byId = userMapper.findById(uuid);
            return MsgResult.success("200",byId,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("200","查询失败");
        }
    }

    @Override
    public MsgResult checkLogin(String token) {

        //1、校验Token有效性
        if(JwtUtil.checkJWT(token)){
            //反解析 令牌 获取当初登录的手机号
            LoginToken loginToken=JSON.parseObject(JwtUtil.parseJWT(token),LoginToken.class);
            //获取当前手机号的令牌
            String t=jedisUtil.get(loginToken.getUid()+":"+ProjectConfig.JWT);
            //比对令牌
            if(Objects.equals(t,token)) {
                return MsgResult.success("200", token,"有效");
            }else {
//                jedisUtil.set(ProjectConfig.EXPIREDMESSAGE,"已经在其他地方登录了");
                return MsgResult.error("500","已经在其他地方登录了");
            }
        }else {
            return MsgResult.error("500","Token校验失败");
        }
    }

}
