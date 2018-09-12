/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sit.sit303.model.Office;

/**
 *
 * @author Student Lab
 */
public class OfficeController {
    private final static String FIND ="SELECT * FROM office o WHERE o.officecode = ?";
    private final static String GET_ALL ="SELECT * FROM office";
    public  Office find(String officeCode){
    Connection conn = ConnectionFactory.getConnnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND);
        } catch (SQLException ex) {
            Logger.getLogger(OfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Office> getAll(){
    
        return null;
    }
}
