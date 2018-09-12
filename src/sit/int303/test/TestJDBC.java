/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.transport.Connection;

/**
 *
 * @author Student Lab
 */
public class TestJDBC {
//    private final  static String URL = "potocal : subpotocal : ชื่อsever";
    private final  static  String URL = "jdbc : derby : //localhost:1527/classmodelg3";
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");//ADD DriveDB
           Connection conn = DriverManager.getConnection(URL, "app", "app");  //สร้างconnection
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
