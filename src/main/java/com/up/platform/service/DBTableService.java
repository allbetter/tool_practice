package com.up.platform.service;

import com.up.platform.entity.DBTable;

import java.util.List;

public interface DBTableService {

    void syncTable(Integer id);

    int insertBatch(List<DBTable> dbTables);
}
