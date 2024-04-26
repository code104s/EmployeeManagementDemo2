package com.offlink.quanlynhanvien.service.Project;

import com.offlink.quanlynhanvien.DAO.DepartmentRepository;
import com.offlink.quanlynhanvien.DAO.EmployeeRepository;
import com.offlink.quanlynhanvien.DAO.ProjectRepository;
import com.offlink.quanlynhanvien.entity.Department;
import com.offlink.quanlynhanvien.entity.Employee;
import com.offlink.quanlynhanvien.entity.Project;
import com.offlink.quanlynhanvien.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    // inject the ProjectRepository
    private ProjectRepository projectRepository;

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository theProjectRepository, EmployeeRepository theEmployeeRepository, DepartmentRepository theDepartmentRepository) {
        projectRepository = theProjectRepository;
        employeeRepository = theEmployeeRepository;
        departmentRepository = theDepartmentRepository;
    }

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


    @Override
    public List<Project> findByTrangThai(Project.Status trangThai) {

        return projectRepository.findByTrangThai(trangThai);
    }
}
