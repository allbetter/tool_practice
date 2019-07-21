package com.up.platform.service;

import com.up.platform.bo.PageBO;
import com.up.platform.entity.Department;

import java.util.List;

public interface DepartmentService {

    int addDepartment(Department department);

    int editDepartment(Department department);

    int deleteDepartment(Integer id);

    List<Department> findAllDepartment(PageBO page);
}
