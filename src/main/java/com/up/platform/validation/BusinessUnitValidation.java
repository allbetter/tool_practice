package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BusinessUnitValidation {

    private Integer id;

    @NotEmpty(message = "BU名必填")
    private String buName;
    
}
