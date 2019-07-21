package com.up.platform.mapper;

import com.up.platform.entity.GroupRelation;

public interface GroupRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRelation record);

    int insertSelective(GroupRelation record);

    GroupRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRelation record);

    int updateByPrimaryKey(GroupRelation record);
}