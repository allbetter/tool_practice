package com.up.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.up.platform.bo.PageBO;
import com.up.platform.repository.BusinessUnitRepository;
import com.up.platform.entity.BusinessUnit;
import com.up.platform.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "BusinessUnitService")
public class BusinessUnitServiceImpl implements BusinessUnitService {

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Override
    public int addBU(BusinessUnit businessUnit) {
        return businessUnitRepository.insertSelective(businessUnit);
    }

    @Override
    public List<BusinessUnit> findAllBU(PageBO page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageLimit());
        return businessUnitRepository.selectAllBU();
    }


}
