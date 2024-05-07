package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByTrangThai(Project.Status trangThai);

}
