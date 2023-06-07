/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.ConnectionOracle;

/**
 *
 * @author HLPhong
 */
public class PhieuNhapCtr {
    public static PhieuNhapClass find(String MaPN) throws Exception {
        String SQL = "select * from PHIEUNHAP where MaPN = ?";  //  Xây dựng câu lệnh SQL để truy vấn thông tin phiếu nhập dựa trên mã hóa đơn là `MaPN`
        try(
            Connection conn = ConnectionOracle.getOracleConnection() ;  //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
            PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        ){
        ps.setString(1, MaPN);  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaPN`) thành giá trị được truyền vào từ tham số
        ResultSet rs = ps.executeQuery();   //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
        if(rs.next()){
           PhieuNhapClass pn = new PhieuNhapClass();  //Tạo một đối tượng `PhieuNhapClass` để lưu thông tin  phiếu nhập
            pn.setMaPN(rs.getString("MaPN"));   //Gán giá trị của cột `MaPN` trong kết quả truy vấn vào thuộc tính `maPN` của đối tượng `PhieuNhapClass`
            pn.setNgayNhap(rs.getDate("NgayNhap"));
            pn.setTongTien(rs.getString("TongTien"));
            pn.setMaNV(rs.getString("MaNV"));
            return pn;  //Trả về đối tượng `PhieuNhapClass` chứa thông tin phiếu nhập
       }
       return null;   // Nếu kết quả truy vấn không tồn tại bất kỳ dòng nào, trả về `null
        }
    }
    public static boolean DeletePN(String MaPN) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "{call sp_XoaPhieuNhap(?)}";  //Xây dựng câu lệnh SQL gọi stored procedure `sp_XoaPhieuNhap` với 1 tham số
       try(
           CallableStatement cs = connection.prepareCall(sql); 
        ){
           cs.setString(1, MaPN);  //Set giá trị của thuộc tính `MaPN` trong đối tượng `PhieuNhapClass` (`pn`) làm tham số thứ nhất của stored procedure          
           return cs.executeUpdate()>0;
       }
    }
    
    public static boolean InsertPN(PhieuNhapClass pn) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "INSERT INTO PHIEUNHAP(MaPN, NgayNhap,TongTien,MaNV) VALUES(?,?,?,?)";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, pn.getMaPN());   //Set giá trị tham số thứ nhất của câu lệnh SQL (`MaPN`) thành giá trị được truyền vào từ tham số của phương thức
           ps.setDate(2, (Date) pn.getNgayNhap());
           ps.setString(3, pn.getTongTien());
           ps.setString(4, pn.getMaNV());
           return ps.executeUpdate() >0;
       }
    }
    
    public static boolean UpdatePN(PhieuNhapClass pn) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "UPDATE PHIEUNHAP SET NgayNhap = ?, TongTien = ?, MaNV = ? WHERE MaPN = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(4, pn.getMaPN());   //Set giá trị của thuộc tính `maPN` trong đối tượng `PhieuNhapClass` (`pm`) làm tham số thứ tư của câu lệnh SQL.
           ps.setDate(1, (Date) pn.getNgayNhap());
           ps.setString(2, pn.getTongTien());
           ps.setString(3,pn.getMaNV());
           return ps.executeUpdate() >0;
       }
    }
}
