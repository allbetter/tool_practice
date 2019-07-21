package com.up.platform.mapper;

import com.up.platform.entity.GroupRelationInfo;

public interface GroupRelationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRelationInfo record);

    int insertSelective(GroupRelationInfo record);

    GroupRelationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRelationInfo record);

    int updateByPrimaryKey(GroupRelationInfo record);
}