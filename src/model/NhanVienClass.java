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
public class NhanVienClass {
    private String maNV;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String diaChi;
    private String CMND;
    private Date ngaySinh;
    private Date ngayBD;
    private String soDT;
    private int luong;
    private String hinhAnh;

    public NhanVienClass() {
    }

    public NhanVienClass(String maNV, String ho, String ten,String gioiTinh, String diaChi, String CMND, Date ngaySinh, Date ngayBD, String soDT, int luong,String hinhAnh) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh=gioiTinh;
        this.diaChi = diaChi;
        this.CMND = CMND;
        this.ngaySinh = ngaySinh;
        this.ngayBD = ngayBD;
        this.soDT = soDT;
        this.luong = luong;
        this.hinhAnh=hinhAnh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    

    @Override

    
    public String toString() {
        return "NhanVienClass{" + "maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", CMND=" + CMND + ", ngaySinh=" + ngaySinh + ", ngayBD=" + ngayBD + ", soDT=" + soDT + ", luong=" + luong + ", hinhAnh=" + hinhAnh + '}';
    }
    
    
    
    
    
}
