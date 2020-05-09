package com.bf.dt.dao.system;

import com.bf.dt.entity.RolePerm;

public interface RolePermMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RolePerm record);

    int insertSelective(RolePerm record);

    RolePerm selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RolePerm record);

    int updateByPrimaryKey(RolePerm record);
}