package com.up.platform.service;

import com.up.platform.entity.GroupRelation;

import java.util.List;

public interface GroupRelationService {
    int insertBatch(List<GroupRelation> groupRelations);
}
