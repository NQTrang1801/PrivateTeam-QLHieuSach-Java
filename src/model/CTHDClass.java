/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NQTrang
 */
public class CTHDClass {
    private String maHD, maSach, sL;

    public CTHDClass() {
    }

    public CTHDClass(String maHD, String maSach, String sL) {
        this.maHD = maHD;
        this.maSach = maSach;
        this.sL = sL;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }  
}
