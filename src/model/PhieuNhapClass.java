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
public class PhieuNhapClass {
    private String maPN;
    private Date ngayNhap;
    private String tongTien;
    private String maNV;

    public PhieuNhapClass() {
    }

    public PhieuNhapClass(String maPN, Date ngayNhap, String tongTien, String maNV) {
        this.maPN = maPN;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.maNV = maNV;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
}
