package com.up.platform.entity;

import java.util.Date;

public class DBTable {
    private Integer id;

    private Integer dbId;

    private String tableZhName;

    private String tableEngName;

    private String tableRemark;

    private Integer tableDiscarded;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getTableZhName() {
        return tableZhName;
    }

    public void setTableZhName(String tableZhName) {
        this.tableZhName = tableZhName == null ? null : tableZhName.trim();
    }

    public String getTableEngName() {
        return tableEngName;
    }

    public void setTableEngName(String tableEngName) {
        this.tableEngName = tableEngName == null ? null : tableEngName.trim();
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark == null ? null : tableRemark.trim();
    }

    public Integer getTableDiscarded() {
        return tableDiscarded;
    }

    public void setTableDiscarded(Integer tableDiscarded) {
        this.tableDiscarded = tableDiscarded;
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