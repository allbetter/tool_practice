package com.up.platform.service;

import com.up.platform.entity.DBManage;

public interface DBManageService {

    int addDBManage(DBManage dbManage);

    int editDBManage(DBManage dbManage);

    int deleteDBManage(Integer id);

    DBManage findDBManageById(Integer id);
}
