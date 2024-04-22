package com.offlink.quanlynhanvien.controller;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Project;
import com.offlink.quanlynhanvien.service.DepartmentService;
import com.offlink.quanlynhanvien.service.EmployeeService;
import com.offlink.quanlynhanvien.service.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    // difine a field to inject the project service
    @Autowired
    private ProjectService projectService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    public ProjectController(ProjectService theProjectService,
                             DepartmentService theDepartmentService,
                             EmployeeService theEmployeeService) {
        projectService = theProjectService;
        departmentService = theDepartmentService;
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listProjects(@RequestParam(required = false) String trangThai, Model theModel) {

        // get the list of projects from the service
        List<Project> projects;

        if(trangThai == null || trangThai.equals("TatCa")) {
            projects = projectService.findAll();
        } else {
            projects = projectService.findByTrangThai(Project.Status.valueOf(trangThai));
        }

        // add the list of projects to the model
        theModel.addAttribute("projects", projects);
        return "employees/projects/list-projects";
    }

    // Trong ProjectController.java
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();

        theModel.addAttribute("employees", employees);
        theModel.addAttribute("departments", departments);
        theModel.addAttribute("project", new Project());
        return "employees/projects/add-projects";
    }

    @GetMapping("/save")
    public String saveProject(@ModelAttribute("project") Project theProject, @RequestParam("employees") List<Integer> employeeIds) {

        projectService.save(theProject);
        return "redirect:/projects/list";
    }
}
