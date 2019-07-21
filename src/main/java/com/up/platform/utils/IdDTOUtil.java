package com.up.platform.utils;

import com.up.platform.dto.IdDTO;

public class IdDTOUtil {

    public static IdDTO setId(Integer id) {
        IdDTO idDTO = new IdDTO();
        idDTO.setId(id);
        return idDTO;
    }
}
