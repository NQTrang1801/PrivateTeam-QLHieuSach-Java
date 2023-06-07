/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ConnectionOracle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NTVy
 */
public class SachCtr {
    public boolean insert(SachClass S)  throws Exception {
        String SQL = "insert into SACH(MaSach, TenSach, MaTL, TenTG, NamXB, NXB, HinhThucBia, TomTat, SL, Gia, MaNCC, NgonNgu, HinhAnh) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";    
        //  Xây dựng câu lệnh SQL để truy vấn thông tin
        try(
             Connection conn = ConnectionOracle.getOracleConnection();  //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
             PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        
            ){
            ps.setString(1, S.getMaSach());  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaSach`) thành giá trị được truyền vào từ tham số của phương thức.
            ps.setString(2, S.getTenSach());
            ps.setString(3, S.getMaTL());
            ps.setString(4, S.getTenTG());
            ps.setDate(5, (Date) S.getNamXB());
            ps.setString(6, S.getNhaXB());
            ps.setString(7, S.getHinhThucBia());
            ps.setString(8, S.getTomTat());
            ps.setString (9, S.getSoLuong());
            ps.setString (10, S.getGia());
            ps.setString (11, S.getMaNCC());
            ps.setString(12, S.getNgonNgu());
            ps.setString(13,S.getHinhAnh());
            return ps.executeUpdate() >0;
        }        
    }
    
      public SachClass find(String MaSach) throws Exception {
        String SQL = "SELECT * FROM SACH where MaSach=?";
        
        try(
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaSach);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           SachClass S = new SachClass();
            S.setMaSach(rs.getString("MaSach"));
            S.setTenSach(rs.getString("TenSach"));
            S.setMaTL(rs.getString("MaTL"));
            S.setTenTG(rs.getString("TenTG"));
            S.setNamXB(rs.getDate("NamXB"));
            S.setNhaXB(rs.getString("NXB"));
            S.setHinhThucBia(rs.getString("HinhThucBia"));
            S.setTomTat(rs.getString("TomTat"));
            S.setSoLuong(rs.getString("SL"));
            S.setGia(rs.getString("Gia"));
            S.setMaNCC(rs.getString("MaNCC"));
            S.setNgonNgu(rs.getString("NgonNgu"));
            S.setHinhAnh(rs.getString("HinhAnh"));
            return S;
       }
       return null;
        }
    }
      
    public boolean update(SachClass S)  throws Exception {
        String SQL = "update SACH set TenSach=?, MaTL=?, TenTG=?, NamXB=?, NXB = ?, HinhThucBia = ?, TomTat=?, SL=?,Gia=?, MaNCC=?, NgonNgu = ?, HinhAnh=? where MaSach = ?";
        
        try(
             Connection conn = ConnectionOracle.getOracleConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        
            )
        {
            ps.setString(13, S.getMaSach());
            ps.setString(1, S.getTenSach());
            ps.setString(2, S.getMaTL());
            ps.setString(3, S.getTenTG());
            ps.setDate(4, (Date) S.getNamXB());
            ps.setString(5, S.getNhaXB());
            ps.setString(6, S.getHinhThucBia());
            ps.setString(7, S.getTomTat());
            ps.setString (8, S.getSoLuong());
            ps.setString (9, S.getGia());
            ps.setString (10, S.getMaNCC());
            ps.setString(11, S.getNgonNgu());
            ps.setString(12,S.getHinhAnh());
               
            return ps.executeUpdate() >0;
        }        
    }
    
    public boolean delete(String MaSach) throws Exception {
        String SQL = "delete from SACH where MaSach=?";
    try(   
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaSach);
       return ps.executeUpdate() >0;
        }
    }
}
