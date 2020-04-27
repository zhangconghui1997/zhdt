package com.bf.dt.dao;


import com.bf.dt.entity.Perm;

public interface PermMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);
}