package com.offlink.quanlynhanvien.converter;

import org.springframework.core.convert.converter.Converter;
import com.offlink.quanlynhanvien.entity.SalaryId;

public class StringToSalaryIdConverter implements Converter<String, SalaryId> {

    @Override
    public SalaryId convert(String source) {
        // Convert the source string to SalaryId here
        // Assuming the source string is the ID of Salary
        SalaryId salaryId = new SalaryId();
        salaryId.setMaNV(Integer.parseInt(source));
        // Set other values for salaryId if necessary
        return salaryId;
    }
}