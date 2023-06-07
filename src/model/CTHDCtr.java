/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.ConnectionOracle;
import java.sql.CallableStatement;
/**
 *
 * @author NQTrang
 */
public class CTHDCtr {
    public static CTHDClass find(String MaHD) throws Exception {
        String SQL = "select * from CTHD where MaHD =?";  //  Xây dựng câu lệnh SQL để truy vấn thông tin chi tiết hóa đơn dựa trên mã hóa đơn là `MaHD`
        
        try(
            Connection conn =ConnectionOracle.getOracleConnection() ;   //Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID.
            PreparedStatement ps = conn.prepareStatement(SQL);  //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        ){
        ps.setString(1, MaHD);   //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaHD`) thành giá trị được truyền vào từ tham số của phương thức.
        ResultSet rs = ps.executeQuery();  //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
        if(rs.next()){
           CTHDClass ct = new CTHDClass();  //Tạo một đối tượng `CTHDClass` để lưu thông tin chi tiết hóa đơn
            ct.setMaHD(rs.getString("MaHD"));  //Gán giá trị của cột `MaHD` trong kết quả truy vấn vào thuộc tính `maHD` của đối tượng `CTHDClass`
            ct.setMaSach(rs.getString("MaSach"));
            ct.setsL(rs.getString("SL"));
            return ct;  //Trả về đối tượng `CTHDClass` chứa thông tin chi tiết hóa đơn
       }
       return null;  // Nếu kết quả truy vấn không tồn tại bất kỳ dòng nào, trả về `null
        }
    }
    
    public static boolean DeleteCTHD(String MaHD, String MaSach) throws Exception
    {
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "DELETE FROM CTHD WHERE MAHD=? and MaSach = ?";
       try(
           CallableStatement cs = connection.prepareCall(sql);   //Tạo một đối tượng `CallableStatement` để thực hiện câu lệnh SQL đã xây dựng
        ){
           cs.setString(1, MaHD);  //Set giá trị tham số thứ nhất của câu lệnh SQL (`MaHD`) thành giá trị được truyền vào từ tham số của phương thức
           cs.setString(2, MaSach);
           return cs.executeUpdate()>0;  //Thực thi câu lệnh SQL và trả về `true` nếu một bản ghi đã bị xóa; ngược lại trả về `false`
       }
    }
    
    public static boolean InsertCTHD(CTHDClass hd) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "{call sp_ThemCTHD(?, ?, ?)}";   //Xây dựng câu lệnh SQL gọi stored procedure `sp_ThemCTHD` với 3 tham số
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, hd.getMaHD());  //Set giá trị của thuộc tính `maHD` trong đối tượng `CTHDClass` (`hd`) làm tham số thứ nhất của stored procedure          
           ps.setString(2, hd.getMaSach());
           ps.setString(3, hd.getsL());
           return ps.executeUpdate() >0;
       }
    }
    
    public static boolean UpdateCTHD(CTHDClass hd) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "UPDATE CTHD SET SL = ? WHERE MaHD = ? and MaSach = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(2, hd.getMaHD());  //Set giá trị của thuộc tính `maHD` trong đối tượng `CTHDClass` (`hd`) làm tham số thứ hai của câu lệnh SQL.
           ps.setString(3,hd.getMaSach());
           ps.setString(1,hd.getsL());
           return ps.executeUpdate() >0;
       }
    }
}
