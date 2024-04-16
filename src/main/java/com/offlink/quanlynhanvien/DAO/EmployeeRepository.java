package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findAllByOrderByIdAsc();

    // search function
    @Query("SELECT e FROM Employee e WHERE e.hoTen LIKE %:keyword% OR e.email LIKE %:keyword% OR e.soDienThoai LIKE %:keyword% OR e.diaChi LIKE %:keyword%")
    List<Employee> search(@Param("keyword") String keyword);
}
