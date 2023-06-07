/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.ConnectionOracle;

/**
 *
 * @author DLHa
 */
public class NhaCungCapCtr {
    public boolean insert(NhaCungCapClass NCC)  throws Exception {
        String SQL = "insert into NHACUNGCAP(MaNCC, TenNCC,DiaChi, SDT ) values(?,?,?,?)";
         //  Xây dựng câu lệnh SQL để truy vấn thông tin
        try(
             Connection conn = ConnectionOracle.getOracleConnection();  //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
             PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.

        
            ){
            ps.setString(1, NCC.getMaNCC()); //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaNCC`) thành giá trị được truyền vào từ tham số của phương thức.
            ps.setString(2, NCC.getTenNCC());
            ps.setString(3, NCC.getDiaChi());
            ps.setString(4, NCC.getSdt());
            
               
            return ps.executeUpdate() >0;
        }        
    }
    
      public NhaCungCapClass find(String MaNCC) throws Exception {
        String SQL = "select * from NHACUNGCAP where MaNCC =?";
        
        try(
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaNCC);
       ResultSet rs = ps.executeQuery();  //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
       if(rs.next()){
           NhaCungCapClass NCC = new NhaCungCapClass();
            NCC.setMaNCC(rs.getString("MaNCC")); //Gán giá trị của cột `MaNCC` trong kết quả truy vấn vào thuộc tính `maNCC` của đối tượng `NhaCungCapClass`
            NCC.setTenNCC(rs.getString("TenNCC"));
            NCC.setDiaChi(rs.getString("DiaChi"));
            NCC.setSdt(rs.getString("SDT"));
            return NCC;
       }     
       return null;
        }
    }
      
    public boolean update(NhaCungCapClass NCC)  throws Exception {
        String SQL = "update NHACUNGCAP set  TenNCC = ?, DiaChi=?,SDT =? where MaNCC = ?";
        
        try(
             Connection conn = ConnectionOracle.getOracleConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
        
            )
        {
            ps.setString(4, NCC.getMaNCC());
            ps.setString(1, NCC.getTenNCC());
            ps.setString(2, NCC.getDiaChi());
            ps.setString(3, NCC.getSdt());

            return ps.executeUpdate() >0;
        }        
    }
    
    public boolean delete(String MaNCC) throws Exception {
        String SQL = "delete from NHACUNGCAP where MaNCC=?";
    try(   
        Connection conn = ConnectionOracle.getOracleConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ){
       ps.setString(1, MaNCC);
       return ps.executeUpdate() >0;
        }
    }
}
