package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Salary;
import com.offlink.quanlynhanvien.entity.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    // tìm nhân viên theo tháng và năm
    List<Salary> findSalaryByThangAndNam(int thang, int nam);

    // findSalaryByMaNVAndThangAndNam
    @Query("SELECT s FROM Salary s WHERE s.maNV = :maNV AND s.thang = :thang AND s.nam = :nam")
    Salary findSalaryByMaNVAndThangAndNam(@Param("maNV") int maNV, @Param("thang") int thang, @Param("nam") int nam);

    Optional<Salary> findById(SalaryId theId);

    @Modifying // update or delete query
    @Query("DELETE FROM Salary s WHERE s.maNV = :maNV AND s.thang = :thang AND s.nam = :nam")
    int deletedSalaryById(@Param("maNV") int maNV, @Param("thang") int thang, @Param("nam") int nam);

}
