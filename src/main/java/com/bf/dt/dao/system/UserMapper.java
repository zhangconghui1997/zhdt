package com.bf.dt.dao.system;


import com.bf.dt.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User findByName(String loginName);

    List<User> findAll(Map<String, Object> map);

    Integer count();

     void deleteById(String uuid);

}