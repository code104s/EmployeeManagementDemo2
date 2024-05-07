package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    // public List<Department> findAllByOrderByTenPBAsc();

    // search function
    @Query("SELECT e FROM Department e WHERE e.tenPB LIKE %:keyword%")
    List<Department> searchDepartment(@Param("keyword") String keyword);
}
