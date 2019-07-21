package com.up.platform.service.impl;


import com.github.pagehelper.PageHelper;
import com.up.platform.bo.PageBO;
import com.up.platform.entity.Department;
import com.up.platform.mapper.DepartmentMapper;
import com.up.platform.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int editDepartment(Department department) {
        return departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public int deleteDepartment(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Department> findAllDepartment(PageBO page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageLimit());
        return departmentMapper.selectAllDepartment();
    }
}
