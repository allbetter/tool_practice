package com.up.platform.service.impl;

import com.up.platform.entity.ColumnInfo;
import com.up.platform.mapper.ColumnInfoMapper;
import com.up.platform.service.ColumnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnInfoServiceImpl implements ColumnInfoService {

    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public List<ColumnInfo> getColumnInfoBySchemaAndTable(String schema, String table) {
        return columnInfoMapper.getColumnInfoBySchemaAndTable(schema, table);
    }
}
