package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.DAO.EmployeeRepository;
import com.offlink.quanlynhanvien.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    // define
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRest) {
        employeeRepository = theEmployeeRest;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByHoTenAsc();
    }

    @Override
    public Employee findById(long theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if(result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id nhan vien : id = " + theId);
        }
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deletedById(long theId) {
        employeeRepository.deleteById(theId);
    }
}