package com.offlink.quanlynhanvien.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "phongban")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPB")
    private long maPB;

    @Column(name="TenPB")
    private String tenPB;

    @Column(name = "Mota")
    private String moTa;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    // define constructor
    public Department() {

    }

    public Department(String tenPB, String moTa) {
        this.tenPB = tenPB;
        this.moTa = moTa;
    }

    // define getter/setter

    public long getMaPB() {
        return maPB;
    }

    public void setMaPB(long maPB) {
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

    // define toString()

    @Override
    public String toString() {
        return "Department{" +
                "maPB=" + maPB +
                ", tenPB='" + tenPB + '\'' +
                ", moTa='" + moTa + '\'' +
                ", employees=" + employees +
                '}';
    }
}
