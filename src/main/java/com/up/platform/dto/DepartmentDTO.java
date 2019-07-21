package com.up.platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentDTO {

    private Integer id;

    @JsonProperty("name")
    private String departmentName;

}
