package com.up.platform.service.impl;

import com.up.platform.entity.*;
import com.up.platform.enums.StatusEnum;
import com.up.platform.mapper.DBColumnMapper;
import com.up.platform.service.ColumnInfoService;
import com.up.platform.service.DBColumnServer;
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
        for (DBTable dbTable: dbTables) {
            Integer tableId = dbTable.getId();
            String tableName = dbTable.getTableEngName();

            DataSourceUtil.dynamicDBSource(dbManage);
            List<ColumnInfo> columnInfoList = columnInfoService.getColumnInfoBySchemaAndTable(dbManage.getDbSchemaName(), tableName);
            DataSourceUtil.masterDBSource();

            List<DBColumn> dbColumnList = new ArrayList<>();
            for (ColumnInfo columnInfo: columnInfoList) {
                DBColumn dbColumn = new DBColumn();
                dbColumn.setTableId(tableId);
                dbColumn.setColumnEngName(columnInfo.getColumn_name());
                dbColumn.setColumnType(columnInfo.getColumn_type());
                dbColumn.setColumnRemark(columnInfo.getColumn_comment());
                dbColumn.setColumnDiscarded(StatusEnum.STATUS_TRUE.getCode());
                dbColumn.setUserId(1);
                dbColumnList.add(dbColumn);
            }
            insertBatch(dbColumnList);
        }
    }

    @Override
    public int insertBatch(List<DBColumn> columns) {
        return dbColumnMapper.insertBatch(columns);
    }
}
