package com.offlink.quanlynhanvien.service.Salary;

import com.offlink.quanlynhanvien.DAO.SalaryRepository;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Salary;
import com.offlink.quanlynhanvien.entity.SalaryId;
import com.offlink.quanlynhanvien.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    // define
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary findSalaryById(SalaryId theId) {

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
    @Transactional // Transactional de update du lieu
    public void save(Salary theSalary) {

        // Lay chuoi thang nam tu session
        Integer thang = theSalary.getThang();
        Integer nam = theSalary.getNam();
        Integer maNV = theSalary.getMaNV();

        // tao SalaryId
        SalaryId theId = new SalaryId();
        theId.setMaNV(maNV);
        theId.setThang(thang);
        theId.setNam(nam);

        // dat SalaryId cho salary
        theSalary.setId(theId);

        // update luong
        salaryRepository.save(theSalary);
    }

    @Override
    @Transactional
    public boolean deletedSalaryById(int maNV, int thang, int nam) {
        return salaryRepository.deletedSalaryById(maNV, thang, nam) > 0;
    }



   /* @Override
    public void deletedSalaryById(long theId) {
        salaryRepository.deleteById(theId);
    }*/

    @Override
    public List<Salary> findSalariesByThangAndNam(int thang, int nam) {
        return salaryRepository.findSalaryByThangAndNam(thang, nam);
    }

    @Override
    public Salary findSalaryById(int maNV, int thang, int nam) {
        return salaryRepository.findSalaryByMaNVAndThangAndNam(maNV, thang, nam);
    }

    @Override
    public void saveUpdateSalary(Salary theSalary) {
        salaryRepository.save(theSalary);
    }


}
