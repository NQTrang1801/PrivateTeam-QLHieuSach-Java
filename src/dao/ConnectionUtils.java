/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author 21521
 */
public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        return ConnectionOracle.getOracleConnection();
    }

    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {

        System.out.println("Get connection ... ");

        Connection conn = ConnectionUtils.getMyConnection();

        System.out.println("Get connection " + conn);

        System.out.println("Done!");
    }

}
