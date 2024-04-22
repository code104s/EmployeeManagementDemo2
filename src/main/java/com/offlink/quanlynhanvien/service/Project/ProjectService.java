package com.offlink.quanlynhanvien.service.Project;

import com.offlink.quanlynhanvien.entity.Project;

import java.util.List;

public interface ProjectService {
    //define a method to get the list of projects
    List<Project> findAll();

    //define a method to get a project by id
    Project findProjectById(long theId);

    //define a method to save a project
    void save(Project theProject);

    //define a method to delete a project by id
    void deletedProjectById(long theId);
}
