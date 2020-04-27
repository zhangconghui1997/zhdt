package com.bf.dt.dao;


import com.bf.dt.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User findByName(String loginName);

}