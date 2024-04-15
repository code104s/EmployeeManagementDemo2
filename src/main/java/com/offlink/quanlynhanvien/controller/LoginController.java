package com.offlink.quanlynhanvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")

    public String loginPage() {
         return "employees/plain-login";
    }
}
