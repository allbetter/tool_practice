package com.up.platform.utils;

import com.up.platform.dto.BooleanDTO;

public class BooleanDTOUtil {

    public static BooleanDTO setStatus(Boolean status) {
        BooleanDTO booleanDTO = new BooleanDTO();
        booleanDTO.setStatus(status);
        return booleanDTO;
    }
}
