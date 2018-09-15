/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.driver;

import java.util.List;
import java.util.Scanner;
import sit.sit303.model.Office;
import sit.sit303.model.controller.OfficeController;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class TestORM {

    public static void main(String[] args) {
//        testFind();
testGetAll();
    }

    public static void testFind() {

        Scanner sc = new Scanner(System.in);
        String officeCode = null;
        do {
            System.out.print("Enter office code to serach(stop to exit);");
            officeCode = sc.next();
            if (!"stop".equalsIgnoreCase(officeCode)) {
                Office office = OfficeController.find(officeCode);
                if (office != null) {
                    System.out.println(office);
                } else {
                    System.out.println("Office code: " + officeCode + " does not exiist !!!");
                }

            }

        } while (!"stop".equalsIgnoreCase(officeCode));
   
    }
    public static void testGetAll() {
        List<Office> offices = OfficeController.getAll();
        for (Office office : offices) {
            System.out.println(office);
        }
        
    }
}
