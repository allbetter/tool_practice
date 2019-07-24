package com.up.platform.validation;

import javax.validation.constraints.NotEmpty;

public class GroupInfoValidation {

    private Integer id;

    private Integer typeId;

    @NotEmpty(message = "分组名必填")
    private String groupName;

    private Integer groupDefine;

    private Integer groupSort;

    private Integer groupType;

    private Integer userId;
}
