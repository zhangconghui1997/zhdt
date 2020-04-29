package com.bf.dt.service;


import com.bf.dt.entity.Role;
import com.bf.dt.result.MsgResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserRoleService {

    MsgResult findByUid(String uid);

    MsgResult setRole(String checkId, String uid);

}