package com.bf.dt.dao;

import com.bf.dt.entity.Menu;
import com.bf.dt.vo.UserMenu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);


    UserMenu findByMid(String mid);

    List<UserMenu> findByParentId(String parentid);
}