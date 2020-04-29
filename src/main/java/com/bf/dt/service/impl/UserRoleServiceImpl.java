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
            List<RoleVo> all = roleMapper.findAll();
            List<String> byUid = userRoleMapper.findByUid(uid);
            for (String rid: byUid) {
                for (RoleVo r: all) {
                    if (!rid.equals(r.getUuid())){
                        continue;
                    }
                    r.setChecked(true);
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


        List<String> checkIdList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("uid",uid);


            if (checkId == null || checkId == ""){
                userRoleMapper.deleteRoleByUser(map);
            }else {
                String[] checkIds = checkId.split(",");
                for (int i = 0; i < checkIds.length; i++) {
                    checkIdList.add(checkIds[i]);
                }

                List<String> byUid = userRoleMapper.findByUid(uid);

                for (String s: byUid) {
                    boolean contains = checkIdList.contains(s);
                    if (contains){
                        checkIdList.remove(s);
                    }else {
                        map.put("rid",s);
                        userRoleMapper.deleteRoleByUser(map);
                    }

                }

                for (String s: checkIdList) {
                    String uuid = MakeCode.getUUID();
                    map.put("uuid",uuid);
                    map.put("rid",s);
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
