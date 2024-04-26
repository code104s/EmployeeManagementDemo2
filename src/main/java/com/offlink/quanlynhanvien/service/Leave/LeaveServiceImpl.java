package com.offlink.quanlynhanvien.service.Leave;

import com.offlink.quanlynhanvien.DAO.EmployeeRepository;
import com.offlink.quanlynhanvien.DAO.LeaveRepository;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Leave;
import com.offlink.quanlynhanvien.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository theLeaveRepository, EmployeeRepository theEmployeeRepository) {
        leaveRepository = theLeaveRepository;
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Leave> findAll() {
        return leaveRepository.findAll();
    }

    @Override
    public Leave findById(int theId) {
        Optional<Leave> result = leaveRepository.findById(theId);
        Leave leave = null;

        if(result.isPresent()) {
            leave = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id nghi phep : id = " + theId);
        }
        return leave;
    }

    @Override
    public void save(Leave theLeave) {
        leaveRepository.save(theLeave);
    }

    @Override
    public void deleteById(int theId) {
        leaveRepository.deleteById(theId);
    }

}
