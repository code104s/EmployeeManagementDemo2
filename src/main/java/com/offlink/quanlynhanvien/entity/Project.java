package com.offlink.quanlynhanvien.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Maduan")
    private int id;

    @Column(name = "Tenduan")
    private String tenDuAn;

    @Column(name = "Mota")
    private String moTa;

    @Column(name = "Ngaybatdau")
    private String ngayBatDau;

    @Column(name = "Ngayketthuc")
    private String ngayKetThuc;

    public enum Status{
        ChuaBatDau,
        DangThucHien,
        DaHoanThanh,
        TamDung
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "Trangthai")
    private Status trangThai;

    @Column(name = "Danhsachnhanvien")
    private String danhSachNhanVien;

    @Column(name = "Ngansach")
    private double nganSach;

    // define fields
    @ManyToMany
    @JoinTable(
            name = "Projectnhanvien",
            joinColumns = @JoinColumn(name = "Maduan"),
            inverseJoinColumns = @JoinColumn(name = "MaNV")
    )
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "Projectphongban",
            joinColumns = @JoinColumn(name = "Maduan"),
            inverseJoinColumns = @JoinColumn(name = "MaPB")
    )
    private List<Department> departments;

    // define constructor
    public Project() {

    }

    public Project(String tenDuAn, String moTa, String ngayBatDau, String ngayKetThuc, Status trangThai, String danhSachNhanVien, double nganSach) {
        this.tenDuAn = tenDuAn;
        this.moTa = moTa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.danhSachNhanVien = danhSachNhanVien;
        this.nganSach = nganSach;
    }

    // define getter/setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Status getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Status trangThai) {
        this.trangThai = trangThai;
    }

    public String getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(String danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }

    public double getNganSach() {
        return nganSach;
    }

    public void setNganSach(double nganSach) {
        this.nganSach = nganSach;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    // define toString()
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", tenDuAn='" + tenDuAn + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayBatDau='" + ngayBatDau + '\'' +
                ", ngayKetThuc='" + ngayKetThuc + '\'' +
                ", trangThai=" + trangThai +
                ", danhSachNhanVien='" + danhSachNhanVien + '\'' +
                ", nganSach=" + nganSach +
                ", employees=" + employees +
                ", departments=" + departments +
                '}';
    }
}
