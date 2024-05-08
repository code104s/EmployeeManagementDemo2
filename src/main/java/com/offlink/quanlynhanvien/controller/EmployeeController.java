package com.offlink.quanlynhanvien.controller;


import com.offlink.quanlynhanvien.DAO.DepartmentRepository;
import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Position;
import com.offlink.quanlynhanvien.service.DepartmentService;
import com.offlink.quanlynhanvien.service.EmployeeService;
import com.offlink.quanlynhanvien.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
@Valid
public class EmployeeController {

    // load employee data
    @Autowired
    private EmployeeService employeeService;;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    public EmployeeController(EmployeeService theEmployeeService,
                              DepartmentService theDepartmentService,
                              PositionService thePositionService) {
        employeeService = theEmployeeService;
        departmentService = theDepartmentService;
        positionService = thePositionService;
    }

    // mapping
    @GetMapping("/list")
    public String listEmployee(Model theModel, @RequestParam(required = false) String keyword) {

        // get the employee form database
        List<Employee> theEmployee = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployee);

        return "employees/list-employee";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model

        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);

        // get list of department and position
        List<Department> theDepartments = departmentService.findAll();
        List<Position> thePositions = positionService.findAll();

        // lay danh sach tat ca Department va Position vao model
        theModel.addAttribute("departments", theDepartments);
        theModel.addAttribute("positions", thePositions);

        return "employees/add-employees";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") long theId, Model theModel) {

        // get the employee, department, position form the service
        Employee theEmployee = employeeService.findById(theId);

        // get department and position
        Department theDepartment = theEmployee.getDepartment();
        Position thePosition = theEmployee.getPosition();

        // set employee in the model prepopulate the form
        theModel.addAttribute("employee", theEmployee);
        theModel.addAttribute("department", theDepartment);
        theModel.addAttribute("position", thePosition);

        // get list of department and position
        List<Department> theDepartments = departmentService.findAll();
        List<Position> thePositions = positionService.findAll();

        // add list of department and position to the model
        theModel.addAttribute("departments", theDepartments);
        theModel.addAttribute("positions", thePositions);

        //return
        return "employees/update-employee";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee theEmployee,
                               @RequestParam("department") long departmentId,
                               @RequestParam("position") long positionId,
                               @Valid Employee employee,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {

            // get department and position
            Department theDepartment = departmentService.findDepartmentById(departmentId);
            Position thePosition = positionService.findPositionById(positionId);

            // set department and position
            theEmployee.setDepartment(theDepartment);
            theEmployee.setPosition(thePosition);

            // get list of department and position
            List<Department> theDepartments = departmentService.findAll();
            List<Position> thePositions = positionService.findAll();

            // add list of department and position to the model
            model.addAttribute("departments", theDepartments);
            model.addAttribute("positions", thePositions);

            return "employees/add-employees";
        }

        // get department and position
        Department theDepartment = departmentService.findDepartmentById(departmentId);
        Position thePosition = positionService.findPositionById(positionId);

        // set department and position
        theEmployee.setDepartment(theDepartment);
        theEmployee.setPosition(thePosition);

        // Check if all necessary fields are present
        if (theEmployee.getHoTen() == null || theEmployee.getSoDienThoai() == null) {
            // Log an error and return an error view
            System.err.println("Error: Missing necessary fields for Employee object.");
            return "error";
        }

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return  "redirect:/employees/list";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {
        // save the employee
        employeeService.deletedById(theId);

        // redirect to the
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model theModel) {
        // search employee from the service
        List<Employee> theEmployee = employeeService.search(keyword);

        // add to the spring model
        theModel.addAttribute("employees", theEmployee);

        return "employees/list-employee";
    }
    @GetMapping("/listByDepartment")
    public ResponseEntity<?> getEmployeeByDepartment(@RequestParam("departmentId") int departmentId) {
        List<Employee> theEmployee = employeeService.findByDepartmentMaPB(departmentId);

        // Create a new list to hold the employee objects
        List<Employee> employeeObjects = new ArrayList<>();

        for (Employee employee : theEmployee) {
            if (employee.getHoTen() == null || employee.getSoDienThoai() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Employee with missing fields found in department " + departmentId);
            }

            // Check if the employee is an object and add it to the new list
            if (employee instanceof Employee) {
                employeeObjects.add(employee);
            }
        }

        // Return the new list of employee objects
        return ResponseEntity.ok(employeeObjects);
    }


}
