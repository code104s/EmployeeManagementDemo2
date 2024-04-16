package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position,Long> {

    // search Function
    @Query("SELECT e FROM Position e WHERE e.tenCV LIKE %:keyword%")
    List<Position> searchPosition(@Param("keyword") String keyword);
}
