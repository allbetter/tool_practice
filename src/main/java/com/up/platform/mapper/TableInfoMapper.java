package com.up.platform.mapper;

import com.up.platform.entity.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TableInfoMapper {

    @Select("SELECT TABLE_NAME, TABLE_COMMENT, CREATE_TIME, UPDATE_TIME FROM information_schema.TABLES where table_schema=#{schema};")
    List<TableInfo> getTableInfoBySchema(String schema);

}
