package com.bf.dt.dao;


import com.bf.dt.entity.Menu;
import com.bf.dt.entity.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    List<String> findByRid(String rid);

    void deleterm (Map<String,String> map);


}