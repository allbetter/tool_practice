package com.up.platform.service.impl;

import com.up.platform.entity.*;
import com.up.platform.enums.DefaultEnum;
import com.up.platform.enums.StatusEnum;
import com.up.platform.manager.RequestHolder;
import com.up.platform.mapper.DBTableMapper;
import com.up.platform.service.*;
import com.up.platform.utils.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private GroupInfoService groupInfoService;

    @Autowired
    private GroupRelationService groupRelationService;

    @Override
    public void syncTable(Integer id) {
        SysUser sysUser = RequestHolder.getCurrentUser();
        // 1. 获取源数据库信息
        DBManage dbManage = dbManageService.findDBManageById(id);
        String schemaName = dbManage.getDbSchemaName();
        // 2. 切换到源数据库链接，获取数据中的表信息，再切换到默认数据库链接
        DataSourceUtil.dynamicDBSource(dbManage);
        List<TableInfo> tableInfoList = tableInfoService.getTableInfoBySchema(schemaName);
        DataSourceUtil.masterDBSource();
        // 3. 循环得到表信息，并插入数据库
        List<DBTable> dbTableList = new ArrayList<>();
        for (TableInfo tableInfo: tableInfoList) {
            DBTable dbTable = new DBTable();
            dbTable.setDbId(id);
            dbTable.setTableZhName(tableInfo.getTable_comment());
            dbTable.setTableEngName(tableInfo.getTable_name());
            dbTable.setTableRemark(DefaultEnum.REMARK.getMessage());
            dbTable.setTableDiscarded(StatusEnum.STATUS_TRUE.getCode());
            dbTable.setUserId(sysUser.getUserId());
            dbTable.setDbCreateTime(tableInfo.getCreate_time());
            dbTable.setDbUpdateTime(tableInfo.getUpdate_time());
            dbTableList.add(dbTable);

        }
        // INFO 返回的值为插入条数
        insertBatch(dbTableList);
        dbColumnServer.syncColumn(dbManage, dbTableList);
        // 4. 循环把表分组信息插入数据库
        GroupInfo groupInfo = groupInfoService.selectByTypeId(dbManage.getId());
        List<GroupRelation> groupRelationList = new ArrayList<>();
        for (DBTable dbTable: dbTableList) {
            Integer tableId = dbTable.getId();
            GroupRelation groupRelation = new GroupRelation();
            groupRelation.setRelationId(tableId);
            groupRelation.setGroupId(groupInfo.getId());
            groupRelation.setUserId(sysUser.getUserId());
            groupRelationList.add(groupRelation);
        }
        groupRelationService.insertBatch(groupRelationList);

    }

    @Override
    public int insertBatch(List<DBTable> dbTables) {
        return dbTableMapper.insertBatch(dbTables);
    }
}
