package com.up.platform.service.impl;

import com.up.platform.entity.GroupInfo;
import com.up.platform.mapper.GroupInfoMapper;
import com.up.platform.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Override
    public int addGroupInfo(GroupInfo groupInfo) {
        return groupInfoMapper.insert(groupInfo);
    }

    @Override
    public int editGroupInfo(GroupInfo groupInfo) {
        return groupInfoMapper.updateByPrimaryKey(groupInfo);
    }

    @Override
    public int deleteGroupInfo(Integer id) {
        return groupInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GroupInfo selectByTypeId(Integer typeId) {
        return groupInfoMapper.selectByTypeId(typeId);
    }
}
