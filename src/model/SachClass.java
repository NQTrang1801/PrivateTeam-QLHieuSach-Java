/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author NTVy
 */
public class SachClass {
    private String maSach;
    private String tenSach;
    private String maTL;
    private String tenTG;
    private Date namXB;
    private String nhaXB;
    private String hinhThucBia;
    private String tomTat;
    private String soLuong;
    private String gia;
    private String maNCC;
    private String ngonNgu;
    private String hinhAnh;

    public SachClass() {
    }

    public SachClass(String maSach, String tenSach, String MaTL, String tenTG, Date namXB, String nhaXB, String hinhThucBia, String tomTat, String soLuong, String gia, String MaNCC, String ngonNgu, String hinhAnh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTL = maTL;
        this.tenTG = tenTG;
        this.namXB = namXB;
        this.nhaXB = nhaXB;
        this.hinhThucBia = hinhThucBia;
        this.tomTat = tomTat;
        this.soLuong = soLuong;
        this.gia = gia;
        this.maNCC = maNCC;
        this.ngonNgu = ngonNgu;
        this.hinhAnh=hinhAnh;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public Date getNamXB() {
        return namXB;
    }

    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getHinhThucBia() {
        return hinhThucBia;
    }

    public void setHinhThucBia(String hinhThucBia) {
        this.hinhThucBia = hinhThucBia;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }
    
    

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getmaNCC() {
        return maNCC;
    }

    public void setMaCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
    

    
    
}
