package com.bf.dt.service.system;


import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;

public interface RoleService {

    MsgResult addRole(Role role);

    MsgResult findAll();

}