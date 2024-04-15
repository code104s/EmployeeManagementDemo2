package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
