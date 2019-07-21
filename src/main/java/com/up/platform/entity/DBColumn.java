package com.up.platform.entity;

import java.util.Date;

public class DBColumn {
    private Integer id;

    private Integer tableId;

    private String columnEngName;

    private String columnType;

    private String columnRemark;

    private Integer columnDiscarded;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getColumnEngName() {
        return columnEngName;
    }

    public void setColumnEngName(String columnEngName) {
        this.columnEngName = columnEngName == null ? null : columnEngName.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getColumnRemark() {
        return columnRemark;
    }

    public void setColumnRemark(String columnRemark) {
        this.columnRemark = columnRemark == null ? null : columnRemark.trim();
    }

    public Integer getColumnDiscarded() {
        return columnDiscarded;
    }

    public void setColumnDiscarded(Integer columnDiscarded) {
        this.columnDiscarded = columnDiscarded;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}