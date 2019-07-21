package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteByIdValidation {

    @NotNull(message = "Id必填")
    private Integer id;

}
