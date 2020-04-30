package com.bf.dt.service.impl;

import com.bf.dt.dao.MenuMapper;
import com.bf.dt.dao.RoleMenuMapper;
import com.bf.dt.entity.Menu;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.MenuService;
import com.bf.dt.vo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired(required = false)
    RoleMenuMapper roleMenuMapper;
    @Autowired(required = false)
    MenuMapper menuMapper;
    @Override
    public MsgResult findMenuByUser(String rid) {
        try {
            List<String> mids = roleMenuMapper.findByRid(rid);
            List<String> midps = menuMapper.findByMain();
            List<String> pids = new ArrayList<>();
            List<UserMenu> menus = new ArrayList<>();
            for (String s:mids) {
                Menu menu = menuMapper.selectByPrimaryKey(s);
                pids.add(menu.getParentid());
            }

            for (String s: midps) {
                UserMenu byMid = menuMapper.findByMid(s);
                if (pids.contains(s)){
                    byMid.setChecked(true);
                }else {
                    byMid.setChecked(false);
                }


                menus.add(byMid);
            }

            for (UserMenu userMenu: menus) {
                List<UserMenu> childrens = userMenu.getChildren();
                for (UserMenu um: childrens) {
                    if (mids.contains(um.getUuid())){
                        um.setChecked(true);
                    }else {
                        um.setChecked(false);
                    }
                }
            }
            return MsgResult.success("0",menus,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询失败");
        }
    }
}
