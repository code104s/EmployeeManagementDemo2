package com.offlink.quanlynhanvien.controller;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// DepartmentController.java
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService theDepartmentService) {
        departmentService = theDepartmentService;
    }

    @GetMapping("/list")
    public String list(Model thModel, @RequestParam (required = false) String keyword) {
        thModel.addAttribute("departments", departmentService.findAll());
        return "employees/list-department";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Department theDepartment = new Department();
        theModel.addAttribute("department", theDepartment);
        return "employees/add-departments";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department theDepartment) {
        departmentService.save(theDepartment);
        return "redirect:/departments/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("departmentId") long theId, Model theModel) {
        Department theDepartment = departmentService.findDepartmentById(theId);
        theModel.addAttribute("department", theDepartment);
        return "employees/add-departments";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("departmentId") long theId) {
        departmentService.deletedDepartmentById(theId);
        return "redirect:/departments/list";
    }

    // mapping search
    @GetMapping("/search")
    public String searchDepartment(@RequestParam("keyword") String keyword, Model theModel) {
        theModel.addAttribute("departments", departmentService.searchDepartment(keyword));
        return "employees/list-department";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("departmentId") long theId, Model theModel, Employee theEmployee) {
        Department theDepartment = departmentService.findDepartmentById(theId);

        theModel.addAttribute("employees", theDepartment.getEmployees());
        theModel.addAttribute("department", theDepartment);
        return "employees/department/department-detail";
    }

}