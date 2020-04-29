package com.bf.dt.service;


import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.vo.UserMenu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    MsgResult findByName(String loginName, String password, HttpServletRequest request);
    MsgResult findMenuByUser(String uid);
    MsgResult findAll(String page,String limit);

}