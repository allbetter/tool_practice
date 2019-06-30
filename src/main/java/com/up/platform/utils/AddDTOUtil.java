package com.up.platform.utils;

import com.up.platform.dto.AddDTO;

public class AddDTOUtil {

    public static AddDTO setId(Integer id) {
        AddDTO addDTO = new AddDTO();
        addDTO.setId(id);
        return addDTO;
    }
}
