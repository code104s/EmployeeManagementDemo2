package com.offlink.quanlynhanvien.service.Project;

import com.offlink.quanlynhanvien.DAO.ProjectRepository;
import com.offlink.quanlynhanvien.entity.Project;
import com.offlink.quanlynhanvien.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    // inject the ProjectRepository
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findProjectById(long theId) {
        Optional<Project> result = projectRepository.findById(theId);
        Project project = null;
        if(result.isPresent()) {
            project = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id luong : id = " + theId);
        }
        return project;
    }

    @Override
    public void save(Project theProject) {
        projectRepository.save(theProject);
    }

    @Override
    public void deletedProjectById(long theId) {
        projectRepository.deleteById(theId);
    }
}
