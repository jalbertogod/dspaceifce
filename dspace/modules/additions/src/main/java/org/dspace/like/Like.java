/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.like;

import org.dspace.content.Item;
import org.dspace.core.Context;
import org.dspace.core.ReloadableEntity;
import org.dspace.eperson.EPerson;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 * @author Alberto 
 */
@Entity
@Table(name="like")
public class Like implements ReloadableEntity<Integer> {

    @Id
    @Column(name="like_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="like_seq")
    @SequenceGenerator(name="like_seq", sequenceName="like_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "like_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;

    protected Like()
    {

    }

    public Integer getID() {
        return id;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
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

        final Like that = (Like)o;
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
