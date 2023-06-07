/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DLHa
 */
public class TaiKhoanClass {
    private String maTK;
    private String tenTK;
    private String matKhau;
    private String loaiTK;
    private String maNV;

    public TaiKhoanClass() {
    }

    public TaiKhoanClass(String maTK, String tenTK, String matKhau, String loaiTK, String maNV) {
        this.maTK = maTK;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.loaiTK = loaiTK;
        this.maNV = maNV;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
}
