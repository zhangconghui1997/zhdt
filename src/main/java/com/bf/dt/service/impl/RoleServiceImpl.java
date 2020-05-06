package com.bf.dt.service.impl;

import com.bf.dt.dao.RoleMapper;
import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.RoleService;
import com.bf.dt.util.TimeUtil;
import com.bf.dt.util.UUIDUtil;
import com.bf.dt.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    RoleMapper roleMapper;

    @Override
    public MsgResult addRole(Role role) {

        try {
            List<Role> allRole = new ArrayList<>();
            role.setUuid(UUIDUtil.getUUID());
            role.setFlag("1");
            role.setCreatTime(TimeUtil.timeToString(new Date()));
            int insert = roleMapper.insert(role);
            if (insert == 1){
                allRole = roleMapper.findAllRole();
            }
            return MsgResult.success("200",allRole,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","添加失败");
        }
    }

    @Override
    public MsgResult findAll() {
        try {
            List<RoleVo> all = roleMapper.findAll();
            return MsgResult.success("0",all,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","查询失败");
        }

    }


}
