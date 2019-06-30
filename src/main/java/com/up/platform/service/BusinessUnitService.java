package com.up.platform.service;

import com.up.platform.bo.PageBO;
import com.up.platform.entity.BusinessUnit;

import java.util.List;

public interface BusinessUnitService {
    int addBU(BusinessUnit businessUnit);

    List<BusinessUnit> findAllBU(PageBO page);
}
