package com.bf.dt.service.system;


import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;

public interface UserService {
    MsgResult findByName(String loginName, String password);
    MsgResult findMenuByUser(String uid);
    MsgResult findAll(String page, String limit);
    MsgResult deleteById(String id);
    MsgResult findById(String uuid);

}