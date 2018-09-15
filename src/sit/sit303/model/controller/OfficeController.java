/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sit.sit303.model.Office;

/**
 *
 * @author Student Lab
 */
public class OfficeController {

    private final static String FIND = "SELECT * FROM office o WHERE o.officecode = ?";
    private final static String GET_ALL = "SELECT * FROM office";

    public static Office find(String officeCode) {
        Connection conn = ConnectionFactory.getConnnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(FIND);
            pstm.setString(1, officeCode);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return maps(rs);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Office> getAll() {
        List<Office> officeList = new ArrayList();
        Connection conn = ConnectionFactory.getConnnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(GET_ALL);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                officeList.add(maps(rs));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return officeList;
    }

    private static Office maps(ResultSet rs) throws SQLException {
        Office office = new Office();

        office.setCity(rs.getString("city"));
        office.setCountry(rs.getString("country"));
        office.setOfficeCode(rs.getString("officecode"));
        office.setPhone(rs.getString("phone"));

        return office;
    }
}
