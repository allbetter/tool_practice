package com.up.platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GroupInfoDTO {

    private Integer id;

    private Integer typeId;

    @JsonProperty("name")
    private String groupName;

    @JsonProperty("define")
    private Integer groupDefine;

    @JsonProperty("sort")
    private Integer groupSort;

    @JsonProperty("type")
    private Integer groupType;

    private Integer userId;

    private Date createTime;

    private Date updateTime;
}
