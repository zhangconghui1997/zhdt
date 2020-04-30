package com.bf.dt.service.impl;

import com.bf.dt.dao.MenuMapper;
import com.bf.dt.dao.RoleMenuMapper;
import com.bf.dt.dao.UserMapper;
import com.bf.dt.dao.UserRoleMapper;
import com.bf.dt.entity.Menu;
import com.bf.dt.entity.Role;
import com.bf.dt.entity.RoleMenu;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.UserService;
import com.bf.dt.util.PageUtil;
import com.bf.dt.vo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    UserRoleMapper userRoleMapper;
    @Autowired(required = false)
    RoleMenuMapper roleMenuMapper;
    @Autowired(required = false)
    MenuMapper menuMapper;
    @Override
    public MsgResult findByName(String loginName, String password, HttpServletRequest request) {
        User user = userMapper.findByName(loginName);
        if (user != null){
            if (password.equals(user.getPassword())){
                request.getSession().setAttribute("User",user);
                return MsgResult.success("200",user,"登录成功");
            }else {
                return MsgResult.error("500","用户名或密码错误");
            }
        }else {
            return MsgResult.error("500","用户名或密码错误");
        }
    }


    @Override
    public MsgResult findMenuByUser(String uid) {
        List<UserMenu> userMenus = new ArrayList<>();
        try {
            List<String> rids = userRoleMapper.findByUid(uid);
            List<String> mids = new ArrayList<>();
            List<String> midps = new ArrayList<>();
            List<String> newpid = new ArrayList<>();
            for (String r : rids) {
                List<String> byRid = roleMenuMapper.findByRid(r);
                for (String s: byRid) {
                    mids.add(s);
                }
            }
            midps = menuMapper.findByMain();

                for (String s:mids) {
                    Menu menu = menuMapper.selectByPrimaryKey(s);
                    if (!newpid.contains(menu.getParentid())){
                        newpid.add(menu.getParentid());
                    }
                 /*
                    if (midps.contains(menu.getParentid())){
                        continue;
                    }
                    midps.remove(menu.getParentid());*/
                }
                for (String s: newpid) {
                    UserMenu byMid = menuMapper.findByMid(s);
                    userMenus.add(byMid);
                }

                for (UserMenu userMenu: userMenus) {
                    List<UserMenu> childrens = userMenu.getChildren();
                    for (UserMenu um: childrens) {
                        if (mids.contains(um.getUuid())){
                            um.setChecked(true);
                        }else {
                            um.setChecked(false);
                        }
                    }
                }




/*                List<String> mids = roleMenuMapper.findByRid(r);
                for (String m : mids) {
                    UserMenu byMid = menuMapper.findByMid(m);
                    if (byMid != null){
                        userMenus.add(byMid);
                    }

                }*/





        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询权限失败");
        }
        return MsgResult.success("200",userMenus,"查询成功");
    }

    @Override
    public MsgResult findAll(String page,String limit) {
        Map<String, Object> map = PageUtil.getPage(page, limit);
        try {
            List<User> all = userMapper.findAll(map);
            Integer count = userMapper.count();
            return MsgResult.page("0",all,"查询成功",count);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询用户列表失败");
        }

    }


}
