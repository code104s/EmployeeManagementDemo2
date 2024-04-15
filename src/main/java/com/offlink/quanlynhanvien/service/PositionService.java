package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> findAll();

    Position findPositionById(long theId);

    void save(Position thePosition);

    void deletedPositionById(long theId);
}
