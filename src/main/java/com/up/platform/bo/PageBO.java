package com.up.platform.bo;

import com.up.platform.constant.PageConstant;
import lombok.Data;

@Data
public class PageBO {

    private Integer pageIndex = PageConstant.PAGE_INDEX;

    private Integer pageLimit = PageConstant.PAGE_LIMIT;

}
