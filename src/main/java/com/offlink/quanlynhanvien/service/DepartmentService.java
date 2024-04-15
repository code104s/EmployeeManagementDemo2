package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findDepartmentById(long theId);

    void save(Department theDepartment);

    void deletedDepartmentById(long theId);
}
