package com.offlink.quanlynhanvien.service.Salary;

import com.offlink.quanlynhanvien.DAO.SalaryRepository;
import com.offlink.quanlynhanvien.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    // define
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary findSalaryById(long theId) {
        Optional<Salary> result = salaryRepository.findById(theId);
        Salary salary = null;
        if(result.isPresent()) {
            salary = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id luong : id = " + theId);
        }
        return salary;
    }

    @Override
    public void save(Salary theSalary) {
        salaryRepository.save(theSalary);
    }

    @Override
    public void deletedSalaryById(long theId) {
        salaryRepository.deleteById(theId);
    }

    @Override
    public List<Salary> findSalariesByThangAndNam(int thang, int nam) {
        return salaryRepository.findSalaryByThangAndNam(thang, nam);
    }
}
