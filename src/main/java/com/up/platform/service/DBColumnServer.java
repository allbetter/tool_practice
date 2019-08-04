package com.up.platform.service;

import com.up.platform.entity.DBColumn;
import com.up.platform.entity.DBManage;
import com.up.platform.entity.DBTable;

import java.util.List;

public interface DBColumnServer {

    void syncColumn(DBManage dbManage, List<DBTable> dbTables);

    int insertBatch(List<DBColumn> dbColumns);
}
