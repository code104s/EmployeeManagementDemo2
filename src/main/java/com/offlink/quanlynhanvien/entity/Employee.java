package com.offlink.quanlynhanvien.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "nhanvien")
public class Employee {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNV")
    private int id;

    @NotEmpty(message = "Họ tên không được để trống")
    @Size(min=5, max=50, message = "Họ tên phải từ 5 đến 50 ký tự")
    @Column(name = "Hoten")
    private String hoTen;

    @Column(name = "Ngaysinh")
    private LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gioitinh")
    private Gender gioiTinh;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Size(min=10, max=11, message = "Số điện thoại phải từ 10 đến 11 ký tự")
    @Column(name = "Sodienthoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "Diachi")
    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "MaCV")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "MaPB")
    private Department department;
    // define constructor
    public Employee() {

    }

    public Employee(String hoTen, LocalDate ngaySinh, Gender gioiTinh, String soDienThoai, String email, String diaChi) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }
    // define getter/setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Gender getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Gender gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    // define toString()


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", position=" + position +
                ", department=" + department +
                '}';
    }

    public enum Gender{
        Nam,
        Nu,
        Khac
    }
}
