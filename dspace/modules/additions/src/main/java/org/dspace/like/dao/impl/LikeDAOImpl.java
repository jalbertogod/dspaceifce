package org.dspace.like.dao.impl;

import org.dspace.core.AbstractHibernateDAO;
import org.dspace.core.Context;
import org.dspace.like.Like;
import org.dspace.like.dao.LikeDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alberto
 */
public class LikeDAOImpl extends AbstractHibernateDAO<Like>  implements LikeDAO
{
    protected LikeDAOImpl()
    {
        super();
    } 
  
}
