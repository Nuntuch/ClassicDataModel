/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.driver;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sit.sit303.model.Customer;
import sit.sit303.model.Office;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class TestEntityManager {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClassicDataModelG3PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Office office = em.find(Office.class, "7"); //Office.class คือ classที่จะเลือกดู
        System.out.println(office.getCity() + "," + office.getCountry());
        office.setCity("Bangmod");
        em.persist(office);
        em.getTransaction().commit();

        List<Customer> customerList = em.createNamedQuery("Customer.findAll").getResultList();         //ใช้ em สร้าง query name คือชื่อที่ตังไว้แล้ว
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomername() + " " + customer.getCity() + " " + customer.getCountry());
        }
    }

}
