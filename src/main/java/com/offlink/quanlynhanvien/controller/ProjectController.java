package com.offlink.quanlynhanvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/list")
    public String listProjects() {
        return "projects/list-projects";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd() {
        return "projects/project-form";
    }
}
