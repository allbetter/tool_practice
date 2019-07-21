package com.up.platform.mapper;

import com.up.platform.entity.DBColumn;

public interface DBColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DBColumn record);

    int insertSelective(DBColumn record);

    DBColumn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBColumn record);

    int updateByPrimaryKey(DBColumn record);
}