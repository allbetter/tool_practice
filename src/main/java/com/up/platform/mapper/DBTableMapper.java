package com.up.platform.mapper;

import com.up.platform.entity.DBTable;

import java.util.List;

public interface DBTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DBTable record);

    int insertSelective(DBTable record);

    DBTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBTable record);

    int updateByPrimaryKey(DBTable record);

    int insertBatch(List<DBTable> dbTables);
}