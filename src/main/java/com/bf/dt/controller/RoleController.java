package com.bf.dt.controller;

import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.RoleService;
import com.bf.dt.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired(required = false)
    UserRoleService userRoleService;

    @Autowired(required = false)
    RoleService roleService;

    @RequestMapping("findByUid")
    public MsgResult findByUid(String uid){
        MsgResult msgResult = userRoleService.findByUid(uid);


        return msgResult;
    }
    @RequestMapping("change")
    public MsgResult change(String checkId, String uid){
        MsgResult msgResult = userRoleService.setRole(checkId,uid);
        return msgResult;
    }


    @RequestMapping("addRole")
    public MsgResult addRole(Role role){
        MsgResult msgResult = roleService.addRole(role);
        return msgResult;
    }








}
