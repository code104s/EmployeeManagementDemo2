package com.offlink.quanlynhanvien.controller;


import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Salary;
import com.offlink.quanlynhanvien.entity.SalaryId;
import com.offlink.quanlynhanvien.service.EmployeeService;
import com.offlink.quanlynhanvien.service.Salary.SalaryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

    //define a field to inject the HttpSession
    @Autowired
    private HttpSession httSession; // inject HttpSession

    //define a field to inject the employee service
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    //define a field to inject the salary service
    public SalaryController(EmployeeService theEmployeeService, SalaryService salaryService) {
        employeeService = theEmployeeService;
        salaryService = salaryService;
    }

    //define a constructor for injection
    @Autowired
    public SalaryController(SalaryService theSalaryService) {
        this.salaryService = theSalaryService;
    }

    //define a method to get the previous month
    private String getPreviousMonth() {
        LocalDate previousMonth = LocalDate.now().minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return previousMonth.format(formatter);
    }

    //define a controller method to show the list of salaries
    @GetMapping("/list")
    public String listSalaries(Model theModel, @RequestParam(required = false) String keyword) {
        theModel.addAttribute("salaries", salaryService.findAll());

        String thangNam = getPreviousMonth();
        theModel.addAttribute("thangNam", thangNam);

        return "employees/salaries/list-salary";
    }

    //mapping showFormForAdd
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel, @RequestParam("thangNam") String thangNam) {
        Salary theSalary = new Salary();

        // Phân tích chuỗi thangNam thành tháng và năm
        String[] parts = thangNam.split("-");
        int nam = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);

        // đặt giá trị tháng và năm
        theSalary.setThang(thang);
        theSalary.setNam(nam);

        //lưu tháng và năm vào session
        httSession.setAttribute("thang", thang);
        httSession.setAttribute("nam", nam);

        // lấy danh sách nhân viên chuưa có trong bảng lương của tháng/năm đã xem
        List<Employee> employees = employeeService.findEmployeeNotInSalaryOfMonth(thang, nam);


        // thêm thangNam vào model
        theModel.addAttribute("thangNam", thangNam);

        theModel.addAttribute("salary", theSalary);
        theModel.addAttribute("employees", employees);

        return "employees/salaries/add-salaries";
    }

    /*// mapping save
    @PostMapping("/save")
    public String saveSalary(@ModelAttribute("salary") Salary theSalary,@RequestParam("employeeId") long employeeId) {

        // tìm employee theo id
        Employee employee = employeeService.findById(employeeId);

        // đặt employee cho salary
        theSalary.setEmployee(employee);

        // lưu lương vào cơ sở dữ liệu
        salaryService.save(theSalary);

        // lấy tháng nam từ sesion
        int thang = (int) httSession.getAttribute("thang");
        int nam = (int) httSession.getAttribute("nam");

        // tạo chuỗi thangNam
        String thangNam = nam + "-" + thang;

        // trả về trang danh sách lương của tháng đó
        return "redirect:/salaries/viewSalary?thangNam=" + thangNam;
    }*/

    @PostMapping("/saveUpdate")
    public String saveUpdatedSalary(@ModelAttribute("salary") Salary theSalary) {
        // lấy tháng nam từ sesion
        Integer thang = (Integer) httSession.getAttribute("thang");
        Integer nam = (Integer) httSession.getAttribute("nam");

        // kiểm tra xem tháng và năm có tồn tại không
        if (thang == null || nam == null) {
            // nếu không, chuyển hướng người dùng về trang danh sách lương
            return "redirect:/salaries/list";
        }

        // đặt lại giá trị tháng và năm cho đối tượng Salary
        theSalary.setThang(thang);
        theSalary.setNam(nam);

        // cập nhật lương trong cơ sở dữ liệu
        salaryService.saveUpdateSalary(theSalary);

        // tạo chuỗi thangNam
        String thangNam = nam + "-" + thang;

        // trả về trang danh sách lương của tháng đó
        return "redirect:/salaries/viewSalary?thangNam=" + thangNam;
    }


    // save new Salary
    @PostMapping("/save")
    public String saveNewSalary(@ModelAttribute("salary") Salary theSalary) {

        // Lay chuoi thang nam tu session
        Integer thang = (Integer) httSession.getAttribute("thang");
        Integer nam = (Integer) httSession.getAttribute("nam");

        // dat gia tri thang va nam cho salary
        theSalary.setThang(thang);
        theSalary.setNam(nam);

        // update luong
        salaryService.save(theSalary);

        // tao chuoi thang nam
        String thangNam = nam + "-" + thang;

        // tra ve trang danh sach
        return "redirect:/salaries/viewSalary?thangNam=" + thangNam;
    }


    // mapping update
    @GetMapping("/showFormForUpdate")
    public String updateSalary(@RequestParam("maNV") int maNV, @RequestParam("thang") int thang, @RequestParam("nam") int nam, Model theModel){
        Salary theSalary = salaryService.findSalaryById(maNV, thang, nam);
        theModel.addAttribute("salary", theSalary);
        return "employees/salaries/update-salary";
    }


    // mapping delete
    // SalaryController.java
    @GetMapping("/delete")
    public String delete(@RequestParam("maNV") int maNV, @RequestParam("thang") int thang, @RequestParam("nam") int nam) {

        // nếu không xóa được, chuyển hướng người dùng về trang danh sách lương
        if(!salaryService.deletedSalaryById(maNV, thang, nam)) {
            return "redirect:/salaries/list";
        }

        thang = (int) httSession.getAttribute("thang");
        nam = (int) httSession.getAttribute("nam");
        String thangNam = nam + "-" + thang;

        return "redirect:/salaries/viewSalary?thangNam=" + thangNam;
    }

    // Trong SalaryController.java
    @GetMapping("/viewSalary")
    public String viewSalary(@RequestParam(value = "thangNam", required = false) String thangNam,Model theModel) {

        // Nếu thangNam là null hoặc chuỗi rỗng, đặt thangNam là tháng trước
        if(thangNam == null || thangNam.isEmpty()) {
            thangNam = getPreviousMonth();
        } else {
            httSession.setAttribute("thangNam", thangNam);
        }

        // phân tích chuỗi thangNam thành tháng và năm
        String[] parts = thangNam.split("-");
        int nam = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);

        // lấy danh sách tất cả nhân viên
        List<Salary> salaries = salaryService.findSalariesByThangAndNam(thang, nam);
        theModel.addAttribute("salaries", salaries);

        // thêm thangNam vào model
        theModel.addAttribute("thangNam", thangNam);

        return "employees/salaries/list-salary"; // Trả về trang hiển thị lương

    }
}
