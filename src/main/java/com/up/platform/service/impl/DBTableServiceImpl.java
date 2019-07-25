package com.up.platform.service.impl;

import com.up.platform.entity.*;
import com.up.platform.enums.StatusEnum;
import com.up.platform.mapper.DBTableMapper;
import com.up.platform.service.*;
import com.up.platform.utils.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DBTableServiceImpl implements DBTableService {

    @Autowired
    private DBTableMapper dbTableMapper;

    @Autowired
    private DBManageService dbManageService;

    @Autowired
    private TableInfoService tableInfoService;

    @Autowired
    private DBColumnServer dbColumnServer;

    @Override
    public void syncTable(Integer id) {

        DBManage dbManage = dbManageService.findDBManageById(id);
        String schemaName = dbManage.getDbSchemaName();

        DataSourceUtil.dynamicDBSource(dbManage);
        List<TableInfo> tableInfoList = tableInfoService.getTableInfoBySchema(schemaName);
        DataSourceUtil.masterDBSource();

        List<DBTable> dbTableList = new ArrayList<>();
        // TODO 表本身的创建和更新时间加到entity里
        for (TableInfo tableInfo: tableInfoList) {
            DBTable dbTable = new DBTable();
            dbTable.setDbId(id);
            dbTable.setTableZhName(tableInfo.getTable_comment());
            dbTable.setTableEngName(tableInfo.getTable_name());
            dbTable.setTableRemark("");
            dbTable.setTableDiscarded(StatusEnum.STATUS_TRUE.getCode());
            // TODO userid从redis里取，或者放全局？
            dbTable.setUserId(1);
//            dbTable.setCreateTime(tableInfo.getCreate_time());
//            dbTable.setUpdateTime(tableInfo.getUpdate_time());
            dbTableList.add(dbTable);
        }
        // INFO 返回的值为插入条数
        insertBatch(dbTableList);
        dbColumnServer.syncColumn(dbManage, dbTableList);

    }

    @Override
    public int insertBatch(List<DBTable> dbTables) {
        return dbTableMapper.insertBatch(dbTables);
    }
}
