package com.up.platform.service;

import com.up.platform.entity.ColumnInfo;

import java.util.List;

public interface ColumnInfoService {

    List<ColumnInfo> getColumnInfoBySchemaAndTable(String schema, String table);
}
