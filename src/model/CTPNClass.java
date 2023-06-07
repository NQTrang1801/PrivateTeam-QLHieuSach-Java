/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NQTrang
 */
public class CTPNClass {
    private String maPN,maSach,sL,gia;

    public CTPNClass() {
    }

    public CTPNClass(String maPN, String maSach, String sL, String gia) {
        this.maPN = maPN;
        this.maSach = maSach;
        this.sL = sL;
        this.gia = gia;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
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

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
    
  
}
