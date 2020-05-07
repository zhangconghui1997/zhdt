package com.bf.dt.service.impl;

import com.bf.dt.dao.MenuMapper;
import com.bf.dt.dao.RoleMenuMapper;
import com.bf.dt.entity.Menu;
import com.bf.dt.entity.RoleMenu;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.MenuService;
import com.bf.dt.util.UUIDUtil;
import com.bf.dt.vo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                if (pids.contains(menu.getParentid())){
                    continue;
                }else {
                    pids.add(menu.getParentid());
                }

            }

            for (String s: midps) {
                UserMenu byMid = menuMapper.findByMid(s);
               /* if (pids.contains(s)){
                    byMid.setChecked(true);
                }else {
                    byMid.setChecked(false);
                }*/


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

    @Override
    public MsgResult change(String checkId, String rid) {
        try {
            String[] checkIds = checkId.split(",");
            ArrayList<String> checkIdList = new ArrayList<>();
            List<String> byRid = roleMenuMapper.findByRid(rid);

            for (int i = 0; i < checkIds.length; i++) {
                checkIdList.add(checkIds[i]);
            }

            for (String s: checkIdList) {
                if (byRid.contains(s)){
                    continue;
                }else {
                    RoleMenu roleMenu = new RoleMenu();
                    String uuid = UUIDUtil.getUUID();
                    roleMenu.setUuid(uuid);
                    roleMenu.setRid(rid);
                    roleMenu.setMid(s);
                    roleMenuMapper.insert(roleMenu);
                }
            }


            for (String s : byRid) {
                if (checkIdList.contains(s)){
                    continue;
                }else {
                    Map<String,String> map = new HashMap<>();
                    map.put("rid",rid);
                    map.put("mid",s);
                    roleMenuMapper.deleterm(map);
                }
            }


            return MsgResult.success("200",null,"权限分配成功");

        } catch (Exception e) {
            e.printStackTrace();
            return  MsgResult.error("500","权限分配失败");
        }
    }
}
