package com.bf.dt.service.impl;

import com.bf.dt.dao.RoleMapper;
import com.bf.dt.dao.UserRoleMapper;
import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.UserRoleService;
import com.bf.dt.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    r.setLAY_CHECKED(true);
                }
            }
            return MsgResult.success("0",all,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询失败");
        }
    }
}
