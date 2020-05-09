package com.bf.dt.controller.system;

import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.RoleService;
import com.bf.dt.service.system.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired(required = false)
    UserRoleService userRoleService;

    @Autowired(required = false)
    RoleService roleService;


    /**
     * 查询该用户对应的权限
     * @param uid
     * @return
     */
    @RequestMapping("findByUid")
    public MsgResult findByUid(String uid){

        MsgResult msgResult = userRoleService.findByUid(uid);


        return msgResult;
    }

    /**
     * 修改用户对应的权限
     * @param checkId
     * @param uid
     * @return
     */
    @RequestMapping("change")
    public MsgResult change(String checkId, String uid){
        MsgResult msgResult = userRoleService.setRole(checkId,uid);
        return msgResult;
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("addRole")
    public MsgResult addRole(Role role){
        MsgResult msgResult = roleService.addRole(role);
        return msgResult;
    }


    /**
     * 查询所有的角色
     * @return
     */
    @RequestMapping("findAll")
    public MsgResult findAll(){
        MsgResult msgResult = roleService.findAll();
        return msgResult;
    }








}
