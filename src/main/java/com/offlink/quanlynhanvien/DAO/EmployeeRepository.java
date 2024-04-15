package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findAllByOrderByHoTenAsc();
}
