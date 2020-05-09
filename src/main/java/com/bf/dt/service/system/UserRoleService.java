package com.bf.dt.service.system;


import com.bf.dt.result.MsgResult;

public interface UserRoleService {

    MsgResult findByUid(String uid);

    MsgResult setRole(String checkId, String uid);

}