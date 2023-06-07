/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author HLPhong
 */
public class HoaDonClass {
    private String maHD;
    private Date ngayHD;
    private String maNV;
    private String maKH;
    private String thanhTien;

    public HoaDonClass() {
    }

    public HoaDonClass(String maHD, Date ngayHD, String maNV, String maKH, String thanhTien) {
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(Date ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "HoaDonClass{" + "maHD=" + maHD + ", ngayHD=" + ngayHD + ", maNV=" + maNV + ", maKH=" + maKH + ", thanhTien=" + thanhTien + '}';
    }
    
    
    
}
