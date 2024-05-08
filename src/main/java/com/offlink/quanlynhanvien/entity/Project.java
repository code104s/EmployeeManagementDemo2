package com.offlink.quanlynhanvien.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Maduan")
    private int id;

    @NotNull(message = "Tên dự án không được để trống")
    @Size(min=5, max=50, message = "Tên dự án phải từ 5 đến 50 ký tự")
    @Column(name = "Tenduan")
    private String tenDuAn;

    @NotNull(message = "Mô tả không được để trống")
    @Size(min=5, max=50, message = "Mô tả phải từ 5 đến 50 ký tự")
    @Column(name = "Mota")
    private String moTa;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @Column(name = "Ngaybatdau")
    private String ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được để trống")
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

    @ManyToOne
    @JoinColumn(name = "MaPB")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "MaQL")
    private Employee manager;

    // define fields
    @ManyToMany
    @JoinTable(
            name = "Projectnhanvien",
            joinColumns = @JoinColumn(name = "Maduan"),
            inverseJoinColumns = @JoinColumn(name = "MaNV")
    )
    @JsonBackReference
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


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

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
                ", department=" + department +
                ", manager=" + manager +
                ", employees=" + employees +
                ", departments=" + departments +
                '}';
    }
}
