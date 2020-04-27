package com.bf.dt.service;


import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.vo.UserMenu;

import java.util.List;

public interface UserService {
    MsgResult findByName(String loginName,String password);
    MsgResult findMenuByUser(String uid);

}