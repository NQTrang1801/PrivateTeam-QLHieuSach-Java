/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 21521
 */
public class ConnectionOracle {

    public static Connection getOracleConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String sid = "orcl21";
        String userName = "PrivateTeam";
        String password = "12345678";

        return getOracleConnection(hostName, sid, userName, password);
    }

    public static Connection getOracleConnection(String hostName, String sid, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        
        return conn;
    }
}
