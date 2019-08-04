package com.up.platform.service.impl;

import com.up.platform.entity.*;
import com.up.platform.enums.StatusEnum;
import com.up.platform.manager.RequestHolder;
import com.up.platform.mapper.DBColumnMapper;
import com.up.platform.service.ColumnInfoService;
import com.up.platform.service.DBColumnServer;
import com.up.platform.service.GroupInfoService;
import com.up.platform.utils.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBColumnServerImpl implements DBColumnServer {

    @Autowired
    private DBColumnMapper dbColumnMapper;

    @Autowired
    private ColumnInfoService columnInfoService;

    @Override
    public void syncColumn(DBManage dbManage, List<DBTable> dbTables) {
        SysUser sysUser = RequestHolder.getCurrentUser();
        for (DBTable dbTable: dbTables) {
            Integer tableId = dbTable.getId();
            String tableName = dbTable.getTableEngName();
            // 1. 切换到源数据库链接，获取数据中的表信息，再切换到默认数据库链接
            DataSourceUtil.dynamicDBSource(dbManage);
            List<ColumnInfo> columnInfoList = columnInfoService.getColumnInfoBySchemaAndTable(dbManage.getDbSchemaName(), tableName);
            DataSourceUtil.masterDBSource();
            // 2. 循环得到字段信息，并插入数据库
            List<DBColumn> dbColumnList = new ArrayList<>();
            for (ColumnInfo columnInfo: columnInfoList) {
                DBColumn dbColumn = new DBColumn();
                dbColumn.setTableId(tableId);
                dbColumn.setColumnEngName(columnInfo.getColumn_name());
                dbColumn.setColumnType(columnInfo.getColumn_type());
                dbColumn.setColumnRemark(columnInfo.getColumn_comment());
                dbColumn.setColumnDiscarded(StatusEnum.STATUS_TRUE.getCode());
                dbColumn.setUserId(sysUser.getUserId());
                dbColumnList.add(dbColumn);
            }
            insertBatch(dbColumnList);
        }
    }

    @Override
    public int insertBatch(List<DBColumn> dbColumns) {
        return dbColumnMapper.insertBatch(dbColumns);
    }
}
