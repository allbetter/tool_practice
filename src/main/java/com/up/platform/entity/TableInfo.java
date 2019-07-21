package com.up.platform.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TableInfo {

    private String table_name;

    private String table_comment;

    private Date create_time;

    private Date update_time;

}
