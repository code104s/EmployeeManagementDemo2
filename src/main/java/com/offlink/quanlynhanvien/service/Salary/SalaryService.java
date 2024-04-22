package com.offlink.quanlynhanvien.service.Salary;

import com.offlink.quanlynhanvien.entity.Salary;

import java.util.List;

public interface SalaryService {
    //define a method to get the list of salaries
    List<Salary> findAll();

    //define a method to get a salary by id
    Salary findSalaryById(long theId);

    //define a method to save a salary
    void save(Salary theSalary);

    //define a method to delete a salary by id
    void deletedSalaryById(long theId);

    List<Salary> findSalariesByThangAndNam(int thang, int nam);
}
