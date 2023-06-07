/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.ConnectionOracle;

/**
 *
 * @author NTVy
 */
public class NhanVienCtr {
    public boolean insert(NhanVienClass NV)  throws Exception {
        String SQL = "insert into NHANVIEN(MaNV,Ho, Ten,GioiTinh, DiaChi, CMND,NgaySinh,NgayBD,SoDT, Luong,HinhAnh ) values(?,?,?,?,?,?,?,?,?,?,?)";
        //  Xây dựng câu lệnh SQL để truy vấn thông tin
        try(
             Connection conn = ConnectionOracle.getOracleConnection();   //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
             PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        
            ){
            ps.setString(1, NV.getMaNV());  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaNV`) thành giá trị được truyền vào từ tham số của phương thức.
            ps.setString(2, NV.getHo());
            ps.setString(3, NV.getTen());
            ps.setString(4, NV.getGioiTinh());
            ps.setString(5, NV.getDiaChi());
            ps.setString(6, NV.getCMND());
            ps.setDate(7, (Date) NV.getNgaySinh());
            ps.setDate(8, (Date) NV.getNgayBD());
            ps.setString(9, NV.getSoDT());
            ps.setInt (10, NV.getLuong());
            ps.setString(11,NV.getHinhAnh());
            return ps.executeUpdate() >0;
        }        
    }
    
      public NhanVienClass find(String MaNV) throws Exception {
        String SQL = "select * from NHANVIEN where MaNV=?";
        
        try(
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaNV);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           NhanVienClass NV = new NhanVienClass();
            NV.setMaNV(rs.getString("MaNV"));
            NV.setHo(rs.getString("Ho"));
            NV.setTen(rs.getString("Ten"));
            NV.setGioiTinh(rs.getString("GioiTinh"));
            NV.setDiaChi(rs.getString("DiaChi"));
            NV.setCMND(rs.getString("CMND"));
            NV.setNgaySinh(rs.getDate("NgaySinh"));
            NV.setNgayBD(rs.getDate("NgayBD"));
            NV.setSoDT(rs.getString("SoDT"));
            NV.setLuong(rs.getInt("Luong"));
            NV.setHinhAnh(rs.getString("HinhAnh"));
            return NV;
       }
       return null;
        }
    }
      
    public boolean update(NhanVienClass NV)  throws Exception {
        String SQL = "update NHANVIEN set Ho=?, Ten=?,GioiTinh=?, DiaChi=?, CMND=?,NgaySinh=?, NgayBD=?,SoDT=?, Luong=?,HinhAnh=? where MaNV = ?";
        
        try(
             Connection conn = ConnectionOracle.getOracleConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        
            )
        {
            ps.setString(11, NV.getMaNV());
            ps.setString(1, NV.getHo());
            ps.setString(2, NV.getTen());
            ps.setString(3, NV.getGioiTinh());
            ps.setString(4, NV.getDiaChi());
            ps.setString(5, NV.getCMND());
            ps.setDate(6, (Date) NV.getNgaySinh());
            ps.setDate(7, (Date) NV.getNgayBD());
            ps.setString(8, NV.getSoDT());
            ps.setInt(9, NV.getLuong());
            ps.setString(10,NV.getHinhAnh());
               
            return ps.executeUpdate() >0;
        }        
    }
    
    public boolean delete(String MaNV) throws Exception {
        String SQL = "{call sp_XoaNhanVien(?)}";
    try(   
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaNV);
       return ps.executeUpdate() >0;
        }
    }
}
