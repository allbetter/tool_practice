package com.up.platform.mapper;

import com.up.platform.entity.ColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ColumnInfoMapper {

    @Select("SELECT COLUMN_NAME, COLUMN_TYPE, COLUMN_COMMENT from information_schema.columns where table_name=#{table} and table_schema=#{schema};")
    List<ColumnInfo> getColumnInfoBySchemaAndTable(String schema, String table);

}
