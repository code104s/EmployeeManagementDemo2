package com.offlink.quanlynhanvien.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nhanvien")

@JsonIdentityInfo(

        // JsonIdentityInfo đảm bảo đối tượng tuần tự hóa 1 lần
        // ObjectIdGenerators.PropertyGenerator.class: định nghĩa thuộc tính nào sẽ được sử dụng để xác định định danh của đối tượng
        // ngăn chặn vòng lặp vô hạn

        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Employee {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNV")
    private int id;

    @NotNull(message = "Họ tên không được để trống")
    @Size(min=5, max=50, message = "Họ tên phải từ 5 đến 50 ký tự")
    @Column(name = "Hoten")
    private String hoTen;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải nhỏ hơn ngày hiện tại")
    @Column(name = "Ngaysinh")
    private LocalDate ngaySinh;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Gioitinh")
    private Gender gioiTinh;

    @NotNull(message = "Số điện thoại không được để trống")
    @Size(min=10, max=11, message = "Số điện thoại phải từ 10 đến 11 ký tự")
    @Column(name = "Sodienthoai")
    private String soDienThoai;

    @NotNull(message = "Email không được để trống")
    @Size(min=5, max=50, message = "Email phải từ 5 đến 50 ký tự")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "Email")
    private String email;


    @NotNull(message = "Địa chỉ không được để trống")
    @Size(min=5, max=50, message = "Địa chỉ phải từ 5 đến 50 ký tự")
    @Column(name = "Diachi")
    private String diaChi;

    @NotNull(message = "Chức vụ không được để trống")
    @ManyToOne
    @JoinColumn(name = "MaCV")
    private Position position;

    @NotNull(message = "Phòng ban không được để trống")
    @ManyToOne
    @JoinColumn(name = "MaPB")
    @JsonBackReference
    private Department department;

    @OneToOne
    @JoinColumn(name = "MaNP")
    private Leave leave;

    @OneToMany(mappedBy = "employee")
    private List<Salary> salaries;

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

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    // define toString()


    // Trong lớp Employee
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
                ", positionId=" + (position != null ? position.getMaCv() : "null") + // null-safe
                ", departmentId=" + (department != null ? department.getMaPB() : "null") + // null-safe
                '}';
    }

    public enum Gender{
        Nam,
        Nu,
        Khac
    }
}
