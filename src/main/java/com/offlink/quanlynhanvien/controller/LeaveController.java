package com.offlink.quanlynhanvien.controller;


import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Leave;
import com.offlink.quanlynhanvien.service.EmployeeService;
import com.offlink.quanlynhanvien.service.Leave.LeaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

    // define injection
    private LeaveService leaveService;

    private EmployeeService employeeService;

    @Autowired
    public LeaveController(LeaveService theLeaveService,EmployeeService theEmployeeService) {
        leaveService = theLeaveService;
        employeeService = theEmployeeService;
    }

    // get all list of leaves
    @GetMapping("/list")
    public String list(Model thModel, @RequestParam(required = false) String keyword) {

        thModel.addAttribute("leaves", leaveService.findAll());

        return "employees/leaves/list-leaves";
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String hoTen) {
        List<Employee> employees = employeeService.findByHoTen(hoTen);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Leave theLeave = new Leave();
        List<Employee> employees = employeeService.findAll();

        theModel.addAttribute("leave", theLeave);
        theModel.addAttribute("employees", employees);

        return "employees/leaves/add-leave";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("leaveId") int theId, Model theModel) {
        Leave theLeave = leaveService.findById(theId);
        List<Employee> employees = employeeService.findAll();

        theModel.addAttribute("leave", theLeave);
        theModel.addAttribute("employees", employees);

        return "employees/leaves/update-leave";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("leaveId") int theId) {
        leaveService.deleteById(theId);
        return "redirect:/leaves/list";
    }

    // save a leave
    @PostMapping("/save")
    public String saveLeave(@ModelAttribute("leave") Leave theLeave,
                            @Valid Leave leave,
                            Model theModel,
                            BindingResult result) {

        if(result.hasErrors()) {
            return "employees/leaves/add-leave";
        }

        if(theLeave.getNgayBatDau() == null || theLeave.getNgayKetThuc() == null) {
            theModel.addAttribute("dateError", "Ngày bắt đầu và ngày kết thúc không được để trống");
            return "employees/leaves/add-leave";
        }
        // Ensure end date is after start date
        if(theLeave.getNgayKetThuc().compareTo(theLeave.getNgayBatDau()) <= 0) {

            Employee employee = employeeService.findById(theLeave.getEmployee().getId());

            theLeave.setEmployee(employee);

            List<Employee> employees = employeeService.findAll();

            theModel.addAttribute("dateError", "Ngày kết thúc phải sau ngày bắt đầu");
            return "employees/leaves/add-leave"; // or "employees/leaves/update-leave" depending on your use case
        }

        leaveService.save(theLeave);
        return "redirect:/leaves/list";
    }
}
