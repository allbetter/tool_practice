package com.up.platform.service.impl;

import com.up.platform.entity.DBManage;
import com.up.platform.mapper.DBManageMapper;
import com.up.platform.service.DBManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManageServiceImpl implements DBManageService {
    
    @Autowired
    private DBManageMapper dbManageMapper;
    
    @Override
    public int addDBManage(DBManage dbManage) {
        return dbManageMapper.insert(dbManage);
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
