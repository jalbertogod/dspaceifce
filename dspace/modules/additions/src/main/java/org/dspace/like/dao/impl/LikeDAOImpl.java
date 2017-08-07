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
    
    @Override
    public Like getLikeEPersonHandle(Context context, java.util.UUID uuidEPerson,Integer idHandle)
            throws SQLException{
        Query query = createQuery(context, "SELECT l FROM Like l INNER JOIN l.handle h INNER JOIN l.ePerson p WHERE h.id = :idHandle AND p.id = :uuidEPerson");
        query.setParameter("idHandle", idHandle);
        query.setParameter("uuidEPerson", uuidEPerson);	
        return (Like) query.uniqueResult();
    }
    @Override
    public int countLikeItem(Context context,Integer idHandle) throws SQLException {
    	 Query query = createQuery(context, "SELECT count(*) FROM Like l INNER JOIN l.handle h WHERE h.id = :idHandle");
    	 query.setParameter("idHandle", idHandle);
    	 return count(query);
    }
  
}
