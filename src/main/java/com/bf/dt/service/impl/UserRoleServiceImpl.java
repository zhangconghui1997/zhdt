package com.bf.dt.service.impl;

import com.bf.dt.dao.RoleMapper;
import com.bf.dt.dao.UserRoleMapper;
import com.bf.dt.entity.Role;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.UserRoleService;
import com.bf.dt.util.MakeCode;
import com.bf.dt.util.SessionUtil;
import com.bf.dt.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired(required = false)
    UserRoleMapper userRoleMapper;

    @Autowired(required = false)
    RoleMapper roleMapper;

    @Override
    public MsgResult findByUid(String uid) {
        try {
            //所有的角色
            List<RoleVo> all = roleMapper.findAll();
            //该用户对应的角色id
            List<String> byUid = userRoleMapper.findByUid(uid);
/*
            for (String rid: byUid) {

                for (RoleVo r: all) {
                    if (!rid.equals(r.getUuid())){
                        continue;
                    }
                    r.setChecked(true);
                }

            }
*/


            //循环所有角色列表，设置用户对应的权限为true
            for (RoleVo r: all) {
                if (byUid.contains(r.getUuid())){
                    r.setChecked(true);
                    continue;
                }
            }





            return MsgResult.success("0",all,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询失败");
        }
    }


    @Override
    public MsgResult setRole(String checkId,String uid) {


        //选中的角色id
        List<String> checkIdList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("uid",uid);

            //没有选中将该用户下所有的角色删除
            if (checkId == null || checkId == ""){
                userRoleMapper.deleteRoleByUser(map);
            }else {

                String[] checkIds = checkId.split(",");
                for (int i = 0; i < checkIds.length; i++) {
                    checkIdList.add(checkIds[i]);
                }
                //查询未修改之前该用户下所有的角色id
                List<String> byUid = userRoleMapper.findByUid(uid);
                    for (String s: byUid) {
                        //选中的id是否包含未修改之前的id
                    boolean contains = checkIdList.contains(s);

                    if (contains){
                        //包含，移除该元素
                        checkIdList.remove(s);
                    }else {
                        //不包含删除该用户对应的该角色
                        map.put("rid",s);
                        userRoleMapper.deleteRoleByUser(map);
                    }

                }

                for (String s: checkIdList) {
                    String uuid = MakeCode.getUUID();
                    map.put("uuid",uuid);
                    map.put("rid",s);
                    //为该用户增加对应的角色
                    userRoleMapper.addRoleByUser(map);
                }
            }
            return MsgResult.success("200",null,"角色分配成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","角色分配失败");
        }

    }
}
