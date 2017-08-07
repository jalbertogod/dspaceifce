/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.denuncia;

import org.dspace.content.Item;
import org.dspace.core.Context;
import org.dspace.core.ReloadableEntity;
import org.dspace.eperson.EPerson;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.util.Date;
import org.dspace.handle.Handle;
import org.dspace.eperson.EPerson;

/**
 *
 *
 * @author Alberto 
 * 
 */
@Entity
@Table(name="denuncia", uniqueConstraints=@UniqueConstraint(columnNames = {"handle_id", "eperson_id"}))// 
public class Denuncia implements ReloadableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="denuncia_seq")
    @SequenceGenerator(name="denuncia_seq", sequenceName="denuncia_seq", allocationSize = 1)
    @Column(name = "denuncia_seq", unique = true, nullable = false, insertable = true, updatable = false)
    private Integer id;

   
    @Temporal(TemporalType.TIMESTAMP)
    private Date denunciaDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handle_id")
    private Handle handle;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eperson_id")
    private EPerson ePerson;
    
    @Transient
    private transient int countDenuncia;
    
    protected Denuncia()
    {

    }
    public int getCountDenuncia() {
        return countDenuncia;
    }
    public void setCountDenuncia(int countDenuncia) {
        this.countDenuncia=countDenuncia;
    }
    
    public EPerson getEPerson() {
        return ePerson;
    }
    public void setEPerson(EPerson ePerson) {
        this.ePerson=ePerson;
    }
    public Handle getHandle() {
        return handle;
    }
    public void setHandle(Handle handle) {
        this.handle=handle;
    }
    public Integer getID() {
        return id;
    }
    public Date getDenunciaDate() {
        return denunciaDate;
    }

    public void setDenunciaDate(Date denunciaDate) {
        this.denunciaDate = denunciaDate;
    }

  

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(o);
        if (getClass() != objClass)
        {
            return false;
        }

        final Denuncia that = (Denuncia)o;
        if (this.getID() != that.getID())
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash=7;
        hash=79*hash+ this.getID();
        return hash;
    }
}
