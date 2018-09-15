/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nuntuch Thongyoo
 */
@Entity
@Table(name = "EMPLOYEE", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByEmployeenumber", query = "SELECT e FROM Employee e WHERE e.employeenumber = :employeenumber")
    , @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname")
    , @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname")
    , @NamedQuery(name = "Employee.findByExtension", query = "SELECT e FROM Employee e WHERE e.extension = :extension")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByJobtitle", query = "SELECT e FROM Employee e WHERE e.jobtitle = :jobtitle")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMPLOYEENUMBER")
    private Integer employeenumber;
    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "EXTENSION")
    private String extension;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "JOBTITLE")
    private String jobtitle;
    @OneToMany(mappedBy = "reportsto")
    private List<Employee> employeeList;
    @JoinColumn(name = "REPORTSTO", referencedColumnName = "EMPLOYEENUMBER")
    @ManyToOne
    private Employee reportsto;
    @JoinColumn(name = "OFFICECODE", referencedColumnName = "OFFICECODE")
    @ManyToOne(optional = false)
    private Office officecode;
    @OneToMany(mappedBy = "salesrepemployeenumber")
    private List<Customer> customerList;

    public Employee() {
    }

    public Employee(Integer employeenumber) {
        this.employeenumber = employeenumber;
    }

    public Employee(Integer employeenumber, String lastname, String firstname, String extension, String email, String jobtitle) {
        this.employeenumber = employeenumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.extension = extension;
        this.email = email;
        this.jobtitle = jobtitle;
    }

    public Integer getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(Integer employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getReportsto() {
        return reportsto;
    }

    public void setReportsto(Employee reportsto) {
        this.reportsto = reportsto;
    }

    public Office getOfficecode() {
        return officecode;
    }

    public void setOfficecode(Office officecode) {
        this.officecode = officecode;
    }

    @XmlTransient
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeenumber != null ? employeenumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeenumber == null && other.employeenumber != null) || (this.employeenumber != null && !this.employeenumber.equals(other.employeenumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sit.sit303.model.Employee[ employeenumber=" + employeenumber + " ]";
    }
    
}
