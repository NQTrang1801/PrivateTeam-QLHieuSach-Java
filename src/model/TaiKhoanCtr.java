/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ConnectionOracle;

/**
 *
 * @author DLHa
 */
public class TaiKhoanCtr {
    public static TaiKhoanClass Login(String tdn, String mk) throws Exception{
        String sql = "SELECT * FROM TAIKHOAN WHERE TenTK LIKE ? AND MatKhau LIKE ?";
       try(
         Connection connection = ConnectionOracle.getOracleConnection();
         PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);      
        ){
           ps.setString(1, tdn);
           ps.setString(2, mk);
           ResultSet rs = ps.executeQuery();
            if(rs.next()){
                TaiKhoanClass tk = new TaiKhoanClass();
                tk.setMaTK(rs.getString("MaTK"));
                tk.setTenTK(rs.getString("TenTK"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setLoaiTK(rs.getString("LoaiTK"));
                tk.setMaNV(rs.getString("MaNV"));
                return tk;
            }
            ps.close();
            connection.close();
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       return null;
    }
    
    public static TaiKhoanClass find(String MaTK) throws Exception {
        String SQL = "select * from TAIKHOAN where MaTK =?";
        
        try(
            Connection conn = ConnectionOracle.getOracleConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
        ){
        ps.setString(1, MaTK);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
           TaiKhoanClass tk = new TaiKhoanClass();
            tk.setMaTK(rs.getString("MaTK"));
            tk.setTenTK(rs.getString("TenTK"));
            tk.setMatKhau(rs.getString("MatKhau"));
            tk.setLoaiTK(rs.getString("LoaiTK"));
            tk.setMaNV(rs.getString("MaNV"));
            return tk;
       }
       return null;
        }
    }
    public static boolean DeleteTK(String MaTK) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "DELETE FROM TAIKHOAN WHERE MATK = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, MaTK);
           return ps.executeUpdate() >0;
       }
    }
    public static boolean InsertTK(TaiKhoanClass tk) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "INSERT INTO TAIKHOAN(MaTK, TenTK, MatKhau, LoaiTK, MaNV) VALUES(?,?,?,?,?)";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(1, tk.getMaTK());
           ps.setString(2, tk.getTenTK());
           ps.setString(3, tk.getMatKhau());
           ps.setString(4, tk.getLoaiTK());
           ps.setString(5, tk.getMaNV());
           return ps.executeUpdate() >0;
       }
    }
    
    public static boolean UpdateTK(TaiKhoanClass tk) throws Exception{
       Connection connection = ConnectionOracle.getOracleConnection();
       String sql = "UPDATE TAIKHOAN SET TenTK = ?, MatKhau = ?, LoaiTK = ?, MaNV = ? WHERE MATK = ?";
       try(
           PreparedStatement ps = connection.prepareStatement(sql);
        ){
           ps.setString(5, tk.getMaTK());
           ps.setString(1, tk.getTenTK());
           ps.setString(2, tk.getMatKhau());
           ps.setString(3, tk.getLoaiTK());
           ps.setString(4, tk.getMaNV());
           return ps.executeUpdate() >0;
       }
    }
}
