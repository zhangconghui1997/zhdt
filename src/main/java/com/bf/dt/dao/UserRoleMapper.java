package com.bf.dt.dao;

import com.bf.dt.entity.Role;
import com.bf.dt.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);


    List<String> findByUid(String uid);

}