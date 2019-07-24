package com.up.platform.service;

import com.up.platform.entity.GroupInfo;

public interface GroupInfoService {

    int addGroupInfo(GroupInfo groupInfo);

    int editGroupInfo(GroupInfo groupInfo);

    int deleteGroupInfo(Integer id);
}
