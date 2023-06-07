/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ConnectionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HLPhong
 */
public class HoaDonCtr {
    public static HoaDonClass find(String MaHD) throws Exception {
        String SQL = "select * from HOADON where MaHD = ?";  //  Xây dựng câu lệnh SQL để truy vấn thông tin hóa đơn dựa trên mã hóa đơn là `MaHD`
        try(
            Connection conn = ConnectionOracle.getOracleConnection() ;  //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
            PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        ){
        ps.setString(1, MaHD);  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaHD`) thành giá trị được truyền vào từ tham số của phương thức.
        ResultSet rs = ps.executeQuery();  //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
        if(rs.next()){
           HoaDonClass hd = new HoaDonClass();  //Tạo một đối tượng `HoaDonClass` để lưu thông tin  hóa đơn
            hd.setMaHD(rs.getString("MaHD"));  //Gán giá trị của cột `MaHD` trong kết quả truy vấn vào thuộc tính `maHD` của đối tượng `HoaDonClass`
            hd.setNgayHD(rs.getDate("NgayHD"));        
            hd.setMaNV(rs.getString("MaNV"));
            hd.setMaKH(rs.getString("MaKH"));
            hd.setThanhTien(rs.getString("ThanhTien"));
            return hd;  //Trả về đối tượng `CTHDClass` chứa thông tin hóa đơn
       }
       return null;  // Nếu kết quả truy vấn không tồn tại bất kỳ dòng nào, trả về `null
        }
    }
    public static boolean DeleteHD(String MaHD) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "{call sp_XoaHoaDon(?)}";   //Xây dựng câu lệnh SQL gọi stored procedure `sp_XoaHoaDon` với 1 tham số
       try(
           CallableStatement cs = connection.prepareCall(sql); 
        ){
           cs.setString(1, MaHD);  //Set giá trị của thuộc tính `maHD` trong đối tượng `HoaDonClass` (`hd`) làm tham số thứ nhất của stored procedure          
           return cs.executeUpdate()>0;
       }
    }
    
    public static boolean InsertHD(HoaDonClass hd) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "INSERT INTO HOADON(MaHD,NgayHD,MaNV,MaKH,ThanhTien) VALUES(?,?,?,?,?)";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, hd.getMaHD());  //Set giá trị tham số thứ nhất của câu lệnh SQL (`MaHD`) thành giá trị được truyền vào từ tham số của phương thức
           ps.setDate(2, (Date) hd.getNgayHD());
           ps.setString(3, hd.getMaNV());
           ps.setString(4, hd.getMaKH());
           ps.setString(5, hd.getThanhTien());
           System.out.println(hd.toString());
           return ps.executeUpdate() >0;
       }
    }
    
    public static boolean UpdateHD(HoaDonClass hd) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "UPDATE HOADON SET NgayHD = ?,MaNV = ?, MaKH = ?,ThanhTien=? WHERE MaHD = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(5, hd.getMaHD());  //Set giá trị của thuộc tính `maHD` trong đối tượng `HoaDonClass` (`hd`) làm tham số thứ năm của câu lệnh SQL.
           ps.setDate(1, (Date) hd.getNgayHD());
           ps.setString(2,hd.getMaNV());
           ps.setString(3,hd.getMaKH());
           ps.setString(4, hd.getThanhTien());
           return ps.executeUpdate() >0;
       }
    }
    
}
