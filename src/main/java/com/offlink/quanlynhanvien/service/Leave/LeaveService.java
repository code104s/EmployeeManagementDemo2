package com.offlink.quanlynhanvien.service.Leave;

import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Leave;

import java.util.List;

public interface LeaveService {

    // add a method to find all leaves
    List<Leave> findAll();

    // find a leave by id
    Leave findById(int theId);

    // save a leave
    void save(Leave theLeave);

    // delete a leave by id
    void deleteById(int theId);

}
