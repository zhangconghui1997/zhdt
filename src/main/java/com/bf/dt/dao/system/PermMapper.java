package com.bf.dt.dao.system;


import com.bf.dt.entity.Perm;

public interface PermMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);
}