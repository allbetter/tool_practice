package com.up.platform.mapper;

import com.up.platform.entity.DBManage;

public interface DBManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DBManage record);

    int insertSelective(DBManage record);

    DBManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBManage record);

    int updateByPrimaryKey(DBManage record);
}