package com.up.platform.mapper;

import com.up.platform.entity.GroupInfo;

public interface GroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    GroupInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);

    GroupInfo selectByTypeId(Integer typeId);
}