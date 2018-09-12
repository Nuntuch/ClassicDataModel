/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student Lab
 */
public class TestJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDataSource");//ADD DriveDB
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
