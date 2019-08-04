package com.up.platform.service.impl;

import com.up.platform.entity.GroupRelation;
import com.up.platform.mapper.GroupRelationMapper;
import com.up.platform.service.GroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRelationServiceImpl implements GroupRelationService {

    @Autowired
    private GroupRelationMapper groupRelationMapper;

    @Override
    public int insertBatch(List<GroupRelation> groupRelations) {
        return groupRelationMapper.insertBatch(groupRelations);
    }
}
