package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.DAO.DepartmentRepository;
import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    // define field departmentRepository
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(long theId) {
        Optional<Department> result = departmentRepository.findById(theId);
        Department department = null;
        if(result.isPresent()) {
            department = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id phong ban : id = " + theId);
        }
        return department;
    }

    @Override
    public void save(Department theDepartment) {
        departmentRepository.save(theDepartment);
    }

    @Override
    public void deletedDepartmentById(long theId) {
        departmentRepository.deleteById(theId);
    }

    @Override
    public List<Department> searchDepartment(String keyword) {
        return departmentRepository.searchDepartment(keyword);
    }

}
