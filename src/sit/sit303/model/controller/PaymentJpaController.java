/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sit.sit303.model.Customer;
import sit.sit303.model.Payment;
import sit.sit303.model.PaymentPK;
import sit.sit303.model.controller.exceptions.NonexistentEntityException;
import sit.sit303.model.controller.exceptions.PreexistingEntityException;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class PaymentJpaController implements Serializable {

    public PaymentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payment payment) throws PreexistingEntityException, Exception {
        if (payment.getPaymentPK() == null) {
            payment.setPaymentPK(new PaymentPK());
        }
        payment.getPaymentPK().setCustomernumber(payment.getCustomer().getCustomernumber());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customer = payment.getCustomer();
            if (customer != null) {
                customer = em.getReference(customer.getClass(), customer.getCustomernumber());
                payment.setCustomer(customer);
            }
            em.persist(payment);
            if (customer != null) {
                customer.getPaymentList().add(payment);
                customer = em.merge(customer);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPayment(payment.getPaymentPK()) != null) {
                throw new PreexistingEntityException("Payment " + payment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payment payment) throws NonexistentEntityException, Exception {
        payment.getPaymentPK().setCustomernumber(payment.getCustomer().getCustomernumber());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment persistentPayment = em.find(Payment.class, payment.getPaymentPK());
            Customer customerOld = persistentPayment.getCustomer();
            Customer customerNew = payment.getCustomer();
            if (customerNew != null) {
                customerNew = em.getReference(customerNew.getClass(), customerNew.getCustomernumber());
                payment.setCustomer(customerNew);
            }
            payment = em.merge(payment);
            if (customerOld != null && !customerOld.equals(customerNew)) {
                customerOld.getPaymentList().remove(payment);
                customerOld = em.merge(customerOld);
            }
            if (customerNew != null && !customerNew.equals(customerOld)) {
                customerNew.getPaymentList().add(payment);
                customerNew = em.merge(customerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PaymentPK id = payment.getPaymentPK();
                if (findPayment(id) == null) {
                    throw new NonexistentEntityException("The payment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PaymentPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment payment;
            try {
                payment = em.getReference(Payment.class, id);
                payment.getPaymentPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payment with id " + id + " no longer exists.", enfe);
            }
            Customer customer = payment.getCustomer();
            if (customer != null) {
                customer.getPaymentList().remove(payment);
                customer = em.merge(customer);
            }
            em.remove(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> findPaymentEntities() {
        return findPaymentEntities(true, -1, -1);
    }

    public List<Payment> findPaymentEntities(int maxResults, int firstResult) {
        return findPaymentEntities(false, maxResults, firstResult);
    }

    private List<Payment> findPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payment.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Payment findPayment(PaymentPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payment> rt = cq.from(Payment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
