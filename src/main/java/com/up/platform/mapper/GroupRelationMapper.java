package com.up.platform.mapper;

import com.up.platform.entity.GroupRelation;

import java.util.List;

public interface GroupRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRelation record);

    int insertSelective(GroupRelation record);

    GroupRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRelation record);

    int updateByPrimaryKey(GroupRelation record);

    int insertBatch(List<GroupRelation> groupRelations);

    GroupRelation selectByTypeId(Integer id);
}