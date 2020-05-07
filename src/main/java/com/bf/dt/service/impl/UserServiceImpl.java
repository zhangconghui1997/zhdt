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
    public MsgResult findByName(String loginName, String password) {
        User user = userMapper.findByName(loginName);
        if (user != null){
            if (password.equals(user.getPassword())){
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
        //处理分页参数
        Map<String, Object> map = PageUtil.getPage(page, limit);
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

}
