package com.offlink.quanlynhanvien.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "phongban")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPB")
    private int maPB;

    @NotNull(message = "Tên phòng ban không được để trống")
    @Size(min=5, max=50, message = "Tên phòng ban phải từ 5 đến 50 ký tự")
    @Column(name="TenPB")
    private String tenPB;

    @NotNull(message = "Mô tả không được để trống")
    @Size(min=5, max=50, message = "Mô tả phải từ 5 đến 50 ký tự")
    @Column(name = "Mota")
    private String moTa;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;

    @ManyToMany(mappedBy = "departments")
    private List<Project> projects;

    // define constructor
    public Department() {

    }

    public Department(String tenPB, String moTa) {
        this.tenPB = tenPB;
        this.moTa = moTa;
    }

    // define getter/setter


    public int getMaPB() {
        return maPB;
    }

    public void setMaPB(int maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    // define toString()

    @Override
    public String toString() {
        return "Department{" +
                "maPB=" + maPB +
                ", tenPB='" + tenPB + '\'' +
                ", moTa='" + moTa + '\'' +
                ", employees=" + employees +
                ", projects=" + projects +
                '}';
    }
}
