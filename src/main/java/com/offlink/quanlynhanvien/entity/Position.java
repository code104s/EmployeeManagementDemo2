package com.offlink.quanlynhanvien.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "chucvu")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCV")
    private int maCv;

    @NotNull(message = "Tên chức vụ không được để trống")
    @Size(min=5, max=50, message = "Tên chức vụ phải từ 5 đến 50 ký tự")
    @Column(name = "TenCV")
    private String tenCV;

    @NotNull(message = "Mô tả không được để trống")
    @Size(min=5, max=50, message = "Mô tả phải từ 5 đến 50 ký tự")
    @Column(name="Mota")
    private String moTa;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<Employee> employees;

    // define constructor
    public Position() {

    }

    public Position(String tenCV, String moTa) {
        this.tenCV = tenCV;
        this.moTa = moTa;
    }

    // define getter/setter


    public int getMaCv() {
        return maCv;
    }

    public void setMaCv(int maCv) {
        this.maCv = maCv;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
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

    // define toString()


    // Trong lớp Position
    @Override
    public String toString() {
        return "Position{" +
                "maCv=" + maCv +
                ", tenCV='" + tenCV + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
