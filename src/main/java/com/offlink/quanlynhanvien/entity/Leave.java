package com.offlink.quanlynhanvien.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="nghiphep")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNP")
    private int id;

    @ManyToOne
    @JoinColumn(name = "MaNV")
    private Employee employee;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @Column(name = "Ngaybatdau")
    private LocalDate ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được để trống")

    @Column(name = "Ngayketthuc")
    private LocalDate ngayKetThuc;

    @Column(name = "Lydo")
    private LiDoType lyDo;

    public enum LiDoType{
        NghiOm,
        NghiPhep,
        NghiKhongPhep
    }

    @Column(name = "Trangthai")
    private Status trangThai;

    public enum Status{
        Duyet,
        KhongDuyet,
        ChoDuyet
    }

    // define constructor
    public Leave() {

    }

    public Leave(LocalDate ngayBatDau, LocalDate ngayKetThuc, LiDoType lyDo, Status trangThai) {
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.lyDo = lyDo;
        this.trangThai = trangThai;
    }

    // define getter/setter


    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public LiDoType getLyDo() {
        return lyDo;
    }

    public void setLyDo(LiDoType lyDo) {
        this.lyDo = lyDo;
    }

    public Status getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Status trangThai) {
        this.trangThai = trangThai;
    }

    // define toString()

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", employee=" + employee +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", lyDo='" + lyDo + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
