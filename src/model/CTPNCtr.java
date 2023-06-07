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
import java.sql.Date;
/**
 *
 * @author NQTrang
 */
public class CTPNCtr {
    public static CTPNClass find(String MaPN) throws Exception {
        String SQL = "select * from CTPN where MaPN =?";  ////  Xây dựng câu lệnh SQL để truy vấn thông tin chi tiết phiếu nhập dựa trên mã phiếu nhập là `MaPN`
        
        try(
            Connection conn =ConnectionOracle.getOracleConnection() ; ////Thiết lập kết nối đến cơ sở dữ liệu Oracle với thông tin tùy chỉnh bao gồm tên đăng nhập, mật khẩu, tên máy chủ và mã SID
            PreparedStatement ps = conn.prepareStatement(SQL);   //thực hiện truy vấn với câu lệnh SQL đã xây dựng.
        ){
        ps.setString(1, MaPN);  //Set giá trị tham số đầu tiên của câu lệnh SQL (`MaPN`) thành giá trị được truyền vào từ tham số của phương thức.
        ResultSet rs = ps.executeQuery();   //Thực hiện truy vấn và lưu kết quả trả về vào đối tượng `ResultSet` (`rs`)
        if(rs.next()){
           CTPNClass ct = new CTPNClass();  ///Tạo một đối tượng `CTHDClass` để lưu thông tin chi tiết phiếu nhập
            ct.setMaPN(rs.getString("MaPN"));  //Gán giá trị của cột `MaPN` trong kết quả truy vấn vào thuộc tính `maPN` của đối tượng `CTPNClass`
            ct.setMaSach(rs.getString("MaSach"));
            ct.setsL(rs.getString("SL"));
            ct.setGia(rs.getString("Gia"));
            return ct; //Trả về đối tượng `CTHDClass` chứa thông tin chi tiết hóa đơn
       }
       return null; // Nếu kết quả truy vấn không tồn tại bất kỳ dòng nào, trả về `null
        }
    }
    
    public static boolean DeleteCTPN(String MaPN, String MaSach) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "DELETE FROM CTPN WHERE MAPN=? and MaSach = ?";
       try(
           CallableStatement cs = connection.prepareCall(sql);  //Tạo một đối tượng `CallableStatement` để thực hiện câu lệnh SQL đã xây dựng
        ){
           cs.setString(1, MaPN);  //Set giá trị tham số thứ nhất của câu lệnh SQL (`MaHD`) thành giá trị được truyền vào từ tham số của phương thức
           cs.setString(2, MaSach);
           return cs.executeUpdate()>0;  //Thực thi câu lệnh SQL và trả về `true` nếu một bản ghi đã bị xóa; ngược lại trả về `false`
       }
    }
    
    public static boolean InsertCTPN(CTPNClass pn) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "{call sp_ThemCTPN(?,?,?,?)}";  //Xây dựng câu lệnh SQL gọi stored procedure `sp_ThemCTPN` với 4 tham số
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, pn.getMaPN());    //Set giá trị của thuộc tính `maPN` trong đối tượng `CTPNClass` (`pn`) làm tham số thứ nhất của stored procedure                  
           ps.setString(2, pn.getMaSach());
           ps.setString(3, pn.getsL());
           ps.setString(4, pn.getGia());
           return ps.executeUpdate() >0;
       }
    }
    
    public static boolean UpdateCTPN(CTPNClass pn) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "UPDATE CTPN SET SL = ?, Gia = ? WHERE MaPN = ? and MaSach = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(3, pn.getMaPN());  //Set giá trị của thuộc tính `maPN` trong đối tượng `CTPNClass` (`pn`) làm tham số thứ hai của câu lệnh SQL.
           ps.setString(4,pn.getMaSach());
           ps.setString(1,pn.getsL());
           ps.setString(2, pn.getGia());
           return ps.executeUpdate() >0;
       }
    }
}
