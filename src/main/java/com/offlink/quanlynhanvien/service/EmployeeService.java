package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long theId);

    void save(Employee theEmployee);

    void deletedById(long theId);

    List<Employee> search(String keyword);

    List<Employee> findEmployeeNotInSalaryOfMonth(int thang, int nam);

    List<Employee> findByDepartmentId(int departmentId);

    // findByHoTen
    List<Employee> findByHoTen(String hoTen);

}
