package com.up.platform.service.impl;

import com.up.platform.entity.TableInfo;
import com.up.platform.mapper.TableInfoMapper;
import com.up.platform.service.TableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Autowired
    private TableInfoMapper tableInfoMapper;



    @Override
    public List<TableInfo> getTableInfoBySchema(String schema) {
        return tableInfoMapper.getTableInfoBySchema(schema);
    }
}
