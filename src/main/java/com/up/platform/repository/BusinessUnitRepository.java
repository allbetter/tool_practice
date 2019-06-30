package com.up.platform.repository;

import com.up.platform.entity.BusinessUnit;

import java.util.List;

public interface BusinessUnitRepository {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessUnit record);

    int insertSelective(BusinessUnit record);

    BusinessUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessUnit record);

    int updateByPrimaryKey(BusinessUnit record);

    List<BusinessUnit> selectAllBU();
}