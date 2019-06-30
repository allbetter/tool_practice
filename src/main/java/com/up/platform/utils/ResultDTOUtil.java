package com.up.platform.utils;

import com.up.platform.dto.ResultDTO;

public class ResultDTOUtil {

    public static ResultDTO success(Object object) {
        ResultDTO resultVO = new ResultDTO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultDTO success() {
        return success(null);
    }

    public static ResultDTO error(Integer code, String msg) {
        ResultDTO resultVO = new ResultDTO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
