/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ConnectionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author DLHa
 */
public class KhachHangCtr {
    public boolean insert(KhachHangCLass KH)  throws Exception {
        String SQL = "insert into KHACHHANG(MaKH, Ho, Ten, NgaySinh, GioiTinh,SoDT, LoaiKH, DiemTichLuy)  values(?,?,?,?,?,?,?,?)";
        //  Xây dựng câu lệnh SQL để truy vấn thông tin
        try(
             Connection conn = ConnectionOracle.getOracleConnection();  //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
             PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.

        
            ){
            ps.setString(1, KH.getMaKH());  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaKH`) thành giá trị được truyền vào từ tham số của phương thức.
            ps.setString(2, KH.getHo());
            ps.setString(3, KH.getTen());
            ps.setDate(4, (java.sql.Date) KH.getNgaySinh());
            ps.setString(5, KH.getGioiTinh());
            ps.setString(6, KH.getSoDT());
            ps.setString(7, KH.getLoaiKH());
            ps.setString(8, KH.getDiemTichLuy()); 
            return ps.executeUpdate() >0;
        }        
    }
    
      public KhachHangCLass find(String MaKH) throws Exception {
        String SQL = "select * from KHACHHANG where MaKH =?";
        
        try(
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaKH);
       ResultSet rs = ps.executeQuery();   //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
       if(rs.next()){
           KhachHangCLass KH = new KhachHangCLass();
            KH.setMaKH(rs.getString("MaKH"));  //Gán giá trị của cột `MaKH` trong kết quả truy vấn vào thuộc tính `maKH` của đối tượng `KhachHangClass`
            KH.setHo(rs.getString("Ho"));
            KH.setTen(rs.getString("Ten"));
            KH.setNgaySinh(rs.getDate("NgaySinh"));
            KH.setGioiTinh(rs.getString("GioiTinh"));
            KH.setSoDT(rs.getString("SoDT"));
            KH.setLoaiKH(rs.getString("LoaiKH"));
            KH.setDiemTichLuy(rs.getString("DiemTichLuy"));
            return KH;
       }     
       return null;
        }
    }
      
    public boolean update(KhachHangCLass KH)  throws Exception {
        String SQL = "update KHACHHANG set Ho=?, Ten=?,NgaySinh=?, GioiTinh=?,SoDT=?, LoaiKH=?,DiemTichLuy=? where MaKH = ?";
        
        try(
             Connection conn = ConnectionOracle.getOracleConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        
            )
        {
            ps.setString(8, KH.getMaKH());
            ps.setString(1, KH.getHo());
            ps.setString(2, KH.getTen());
            ps.setDate(3, (java.sql.Date) KH.getNgaySinh());
            ps.setString(4, KH.getGioiTinh());
            ps.setString(5, KH.getSoDT());
            ps.setString(6, KH.getLoaiKH());
            ps.setString(7, KH.getDiemTichLuy()); 

            return ps.executeUpdate() >0;
        }        
    }
    
    public boolean delete(String MaKH) throws Exception {
        String SQL = "{call sp_XoaKhachHang(?)}";
    try(   
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaKH);
       return ps.executeUpdate() >0;
        }
    }
}
