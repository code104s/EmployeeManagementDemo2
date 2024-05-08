package com.offlink.quanlynhanvien.controller;

import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Project;
import com.offlink.quanlynhanvien.service.DepartmentService;
import com.offlink.quanlynhanvien.service.EmployeeService;
import com.offlink.quanlynhanvien.service.Project.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        List<Department> departments = departmentService.findAll();
        List<Employee> managers = employeeService.findAll();
        theModel.addAttribute("managers", managers);
        theModel.addAttribute("departments", departments);
        theModel.addAttribute("project", new Project());
        return "employees/projects/add-projects";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") Project theProject) {
        // Lấy thông tin về quản lý dự án từ request

        Employee manager = employeeService.findById(theProject.getManager().getId());
        theProject.setManager(manager);
        projectService.save(theProject);
        return "redirect:/projects/list";
    }

    @PostMapping("/saveUpdate")
    public String saveOrUpdateProject(@ModelAttribute("project") Project theProject,
                                      @RequestParam("employeeIds") List<Long> employeeIds) {
        // Find the employees by their ids
        List<Employee> employees = employeeService.findAllByIdIn(employeeIds);

        // Add the employees to the project
        theProject.setEmployees(employees);

        // Save the updated project
        projectService.save(theProject);

        // Redirect to the project list page
        return "redirect:/projects/list";
    }

    // delete project
    @GetMapping("/delete")
    public String deleteProject(@RequestParam("projectId") long theId) {
        projectService.deletedProjectById(theId);
        return "redirect:/projects/list";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("projectId") long theId, Model theModel) {
        // Get the project from the service
        Project theProject = projectService.findProjectById(theId);
        List<Department> departments = departmentService.findAll();
        List<Employee> theManager = employeeService.findAll();

        // Set project as a model attribute to pre-populate the form
        theModel.addAttribute("project", theProject);
        theModel.addAttribute("departments", departments);
        theModel.addAttribute("managers", theManager);

        theModel.addAttribute("manager", new Employee());

        return "employees/projects/update-projects";
    }

    @GetMapping("/detail")
    public String detailProject(@RequestParam("projectId") int projectId, Model theModel) {
        Project project = projectService.findProjectById(projectId);
        theModel.addAttribute("project", project);
        return "employees/projects/detail-project";
    }

    @GetMapping("/getEmployeesByDepartment")
    @ResponseBody
    public List<Employee> getEmployeesByDepartment(@RequestParam("departmentId") int maPB) {
        return employeeService.findByDepartmentMaPB(maPB);
    }

    @GetMapping("/selectManageDepartment")
    public String selectDepartment(@RequestParam("departmentId") int departmentId, Model theModel) {
        List<Employee> employees = employeeService.findByDepartmentMaPB(departmentId);
        theModel.addAttribute("employees", employees);
        return "employees/projects/selectManageProject";
    }

    @PostMapping("/saveManager")
    @ResponseBody
    public void saveManager(@RequestParam("managerId") int managerId, HttpSession session) {
        session.setAttribute("selectedManagerId", managerId);
    }

    // show form add employee to project
    @GetMapping("/showFormAddEmployeeToProject")
    public String addEmployee(@RequestParam("projectId") int projectId, Model theModel) {

        Project project = projectService.findProjectById(projectId);

        List<Employee> employees = employeeService.findByDepartmentMaPB(project.getDepartment().getMaPB());
        theModel.addAttribute("project", project);
        theModel.addAttribute("employees", employees);
        return "employees/projects/add-member-project";
    }

    // save employee to project
    @PostMapping("/addEmployeeToProject")
    public String addEmployeeToProject(@RequestParam("projectId") long projectId,
                                       @RequestParam("employeeIds") List<Long> employeeIds) {
        // Find the project by id
        Project theProject = projectService.findProjectById(projectId);

        // Find the employees by their ids
        List<Employee> employees = employeeService.findAllByIdIn(employeeIds);

        // Add the employees to the project
        theProject.setEmployees(employees);

        // Save the updated project
        projectService.save(theProject);

        // Redirect to the project list page
        return "redirect:/projects/list";
    }
}
