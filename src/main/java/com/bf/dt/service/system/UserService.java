package com.bf.dt.service.system;


import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {
    MsgResult findByName(String loginName, String password, HttpServletResponse response);
    MsgResult findMenuByUser(String uid);
    MsgResult findAll(String page, String limit);
    MsgResult deleteById(String id);
    MsgResult findById(String uuid);
    MsgResult checkLogin(String token);

}