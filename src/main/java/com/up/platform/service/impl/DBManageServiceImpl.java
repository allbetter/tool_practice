package com.up.platform.service.impl;

import com.up.platform.entity.DBManage;
import com.up.platform.entity.GroupInfo;
import com.up.platform.entity.SysUser;
import com.up.platform.enums.DefaultEnum;
import com.up.platform.enums.GroupDefineEnum;
import com.up.platform.enums.GroupTypeEnum;
import com.up.platform.manager.RequestHolder;
import com.up.platform.mapper.DBManageMapper;
import com.up.platform.service.DBManageService;
import com.up.platform.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManageServiceImpl implements DBManageService {
    
    @Autowired
    private DBManageMapper dbManageMapper;

    @Autowired
    private GroupInfoService groupInfoService;
    
    @Override
    public int addDBManage(DBManage dbManage) {
        SysUser sysUser = RequestHolder.getCurrentUser();

        // 1. 新建数据库
        Integer DBId = dbManageMapper.insert(dbManage);

        // 2. 新建数据库默认分组(未分类)
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.settypeId(DBId);
        groupInfo.setGroupName(GroupDefineEnum.UNDEFINE.getMessage());
        groupInfo.setGroupDefine(GroupDefineEnum.UNDEFINE.getCode());
        groupInfo.setGroupSort(DefaultEnum.SORT.getCode());
        groupInfo.setGroupType(GroupTypeEnum.DATABASE.getCode());
        groupInfo.setUserId(sysUser.getUserId());
        groupInfoService.addGroupInfo(groupInfo);

        return DBId;
    }

    @Override
    public int editDBManage(DBManage dbManage) {
        return dbManageMapper.updateByPrimaryKey(dbManage);
    }

    @Override
    public int deleteDBManage(Integer id) {
        return dbManageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public DBManage findDBManageById(Integer id) {
        return dbManageMapper.selectByPrimaryKey(id);
    }
}
