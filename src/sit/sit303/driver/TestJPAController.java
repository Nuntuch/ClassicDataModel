/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.driver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sit.sit303.model.Employee;
import sit.sit303.model.Office;
import sit.sit303.model.controller.OfficeJpaController;
import sit.sit303.model.controller.exceptions.NonexistentEntityException;


/**
 *
 * @author Nuntuch Thongyoo
 */
public class TestJPAController {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClassicDataModelG3PU");
        OfficeJpaController officeJpaCtrl = new OfficeJpaController(emf);
        
        List <Office> officeList = officeJpaCtrl.findOfficeEntities();
        for (Office office : officeList) {
            System.out.println(office.getCity()+" "+office.getCountry());
            System.out.println("------------------------------------------------");
            List<Employee> employeeList = office.getEmployeeList();
            for (Employee employee : employeeList) {
                System.out.println("    "+employee.getFirstname()+employee.getLastname()+" "+employee.getJobtitle());
                
            }
        }
                
        officeList.get(0).setCity("Bangrak");
        try {
            officeJpaCtrl.edit(officeList.get(0));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TestJPAController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestJPAController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    
}
