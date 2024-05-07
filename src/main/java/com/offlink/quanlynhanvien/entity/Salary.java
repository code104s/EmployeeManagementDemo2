package com.offlink.quanlynhanvien.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "luongthuong")

@IdClass(SalaryId.class) // define composite key

public class Salary {

    @Column(name = "MaLT")
    private int id;

    @Id
    @Column(name = "MaNV")
    private int maNV;

    @Id
    @Column(name = "Thang")
    private int thang;

    @Id
    @Column(name = "Nam")
    private int nam;

    @Column(name = "Luongcoban")
    private double luongCoBan;

    @Column(name = "Thuong")
    private double thuong;

    @Column(name="Phucap")
    private double phuCap;

    @Column(name="Tonggiolam")
    private int tongGioLam;

    @Column(name="Tonggiotangca")
    private int tongGioTangCa;

    // many to many
    @ManyToOne
    @JoinColumn(name = "MaNV", insertable = false, updatable = false)
    private Employee employee;


    // define constructor
    public Salary() {

    }

    public Salary(int thang, int nam, double luongCoBan, double thuong, double phuCap, int tongGioLam, int tongGioTangCa) {
        this.thang = thang;
        this.nam = nam;
        this.luongCoBan = luongCoBan;
        this.thuong = thuong;
        this.phuCap = phuCap;
        this.tongGioLam = tongGioLam;
        this.tongGioTangCa = tongGioTangCa;
    }
    // define getter/setter


    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId(SalaryId id) {
        this.maNV = id.getMaNV();
        this.thang = id.getThang();
        this.nam = id.getNam();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong(double thuong) {
        this.thuong = thuong;
    }


    public int getTongGioLam() {
        return tongGioLam;
    }

    public void setTongGioLam(int tongGioLam) {
        this.tongGioLam = tongGioLam;
    }

    public int getTongGioTangCa() {
        return tongGioTangCa;
    }

    public void setTongGioTangCa(int tongGioTangCa) {
        this.tongGioTangCa = tongGioTangCa;
    }

    // define toString()


    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employee=" + employee +
                ", thang=" + thang +
                ", nam=" + nam +
                ", luongCoBan=" + luongCoBan +
                ", thuong=" + thuong +
                ", phuCap=" + phuCap +
                ", tongGioLam=" + tongGioLam +
                ", tongGioTangCa=" + tongGioTangCa +
                '}';
    }
}
