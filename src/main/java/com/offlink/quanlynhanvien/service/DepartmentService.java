package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findDepartmentById(long theId);

    void save(Department theDepartment);

    void deletedDepartmentById(long theId);

    List<Department> searchDepartment(String keyword);
}
