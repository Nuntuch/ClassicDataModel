/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Student Lab
 */
public class ConnectionFactory {

//    private final static String URL ="potocal://subpotocal/ชื่อserver";
    private final static String URL = "jdbc:derby://localhost:1527/classicmodelg3";

    public static Connection getConnnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");//ADD Driver
            Connection conn = DriverManager.getConnection(URL, "app", "app");//สร้าง connection
            return conn ;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    return null;
    }
}
