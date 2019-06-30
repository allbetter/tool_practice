package com.up.platform.vo;

import com.up.platform.bo.PageBO;
import lombok.Data;

@Data
public class ResultVO<T> {

    /** 具体内容. */
    private T data;

    /** 分页内容. */
    private PageBO page;

}
