/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.sit303.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nuntuch Thongyoo
 */
@Embeddable
public class PaymentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CUSTOMERNUMBER")
    private int customernumber;
    @Basic(optional = false)
    @Column(name = "CHECKNUMBER")
    private String checknumber;

    public PaymentPK() {
    }

    public PaymentPK(int customernumber, String checknumber) {
        this.customernumber = customernumber;
        this.checknumber = checknumber;
    }

    public int getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(int customernumber) {
        this.customernumber = customernumber;
    }

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customernumber;
        hash += (checknumber != null ? checknumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentPK)) {
            return false;
        }
        PaymentPK other = (PaymentPK) object;
        if (this.customernumber != other.customernumber) {
            return false;
        }
        if ((this.checknumber == null && other.checknumber != null) || (this.checknumber != null && !this.checknumber.equals(other.checknumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sit.sit303.model.PaymentPK[ customernumber=" + customernumber + ", checknumber=" + checknumber + " ]";
    }
    
}
