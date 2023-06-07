/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DLHa
 */
public class KhachHangCLass {
    private String maKH;
    private String ho;
    private String ten;
    private Date ngaySinh;
    private String gioiTinh;
    private String soDT;
    private String loaiKH;
    private String diemTichLuy;

    public KhachHangCLass() {
    }

    public KhachHangCLass(String maKH, String ho, String ten, Date ngaySinh, String gioiTinh, String soDT, String loaiKH, String diemTichLuy) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.loaiKH = loaiKH;
        this.diemTichLuy = diemTichLuy;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    public String getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(String diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
    
    
}
