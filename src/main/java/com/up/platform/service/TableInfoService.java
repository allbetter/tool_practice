package com.up.platform.service;

import com.up.platform.entity.TableInfo;

import java.util.List;

public interface TableInfoService {

    List<TableInfo> getTableInfoBySchema(String schema);
}
