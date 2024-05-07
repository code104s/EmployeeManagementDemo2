package com.offlink.quanlynhanvien.service.Salary;

import com.offlink.quanlynhanvien.entity.Salary;
import com.offlink.quanlynhanvien.entity.SalaryId;

import java.util.List;

public interface SalaryService {
    //define a method to get the list of salaries
    List<Salary> findAll();

    //define a method to get a salary by id
    Salary findSalaryById(SalaryId theId);

    //define a method to save a salary
    void save(Salary theSalary);

    /*//define a method to delete a salary by id
    void deletedSalaryById(long theId);*/

    //define a method to delete a salary by SalaryId
    // SalaryService.java
    boolean deletedSalaryById(int maNV, int thang, int nam);

    List<Salary> findSalariesByThangAndNam(int thang, int nam);

    Salary findSalaryById(int maNV, int thang, int nam);

    // saveUpdateSalary
    void saveUpdateSalary(Salary theSalary);
}
