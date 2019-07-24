package com.up.platform.service.impl;

import com.up.platform.entity.DBManage;
import com.up.platform.entity.DBTable;
import com.up.platform.entity.TableInfo;
import com.up.platform.mapper.DBTableMapper;
import com.up.platform.service.DBManageService;
import com.up.platform.service.DBTableService;
import com.up.platform.service.TableInfoService;
import com.up.platform.utils.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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

    @Override
    public int insertBatch(Integer id) {

        DBManage dbManage = dbManageService.findDBManageById(id);
        DataSourceUtil.dynamicDBSource(dbManage);
        List<TableInfo> tableInfoList = tableInfoService.getTableInfoBySchema(dbManage.getDbSchemaName());
        DataSourceUtil.masterDBSource();
        List<DBTable> dbTableList = new ArrayList<>();
        // TODO 表本身的创建和更新时间加到entity里
        for (TableInfo tableInfo: tableInfoList) {
            DBTable dbTable = new DBTable();
            dbTable.setDbId(id);
            dbTable.setTableDiscarded(0);
            dbTable.setTableEngName(tableInfo.getTable_name());
            dbTable.setTableZhName(tableInfo.getTable_comment());
            dbTable.setTableRemark("");
            // TODO userid从redis里取，或者放全局？
            dbTable.setUserId(1);d
            dbTableList.add(dbTable);
        }
        // INFO 返回的值为插入条数
        Integer result = dbTableMapper.insertBatch(dbTableList);
        return 1;
    }
}
