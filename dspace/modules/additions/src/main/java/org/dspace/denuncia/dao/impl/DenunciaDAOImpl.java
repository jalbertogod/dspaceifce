package org.dspace.denuncia.dao.impl;

import org.dspace.core.AbstractHibernateDAO;
import org.dspace.core.Context;
import org.dspace.denuncia.Denuncia;
import org.dspace.denuncia.dao.DenunciaDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alberto
 */
public class DenunciaDAOImpl extends AbstractHibernateDAO<Denuncia>  implements DenunciaDAO
{
    protected DenunciaDAOImpl()
    {
        super();
    } 
    
    @Override
    public Denuncia getDenunciaEPersonHandle(Context context, java.util.UUID uuidEPerson,Integer idHandle)
            throws SQLException{
        Query query = createQuery(context, "SELECT l FROM Denuncia l INNER JOIN l.handle h INNER JOIN l.ePerson p WHERE h.id = :idHandle AND p.id = :uuidEPerson");
        query.setParameter("idHandle", idHandle);
        query.setParameter("uuidEPerson", uuidEPerson);	
        return (Denuncia) query.uniqueResult();
    }
    @Override
    public int countDenunciaItem(Context context,Integer idHandle) throws SQLException {
    	 Query query = createQuery(context, "SELECT count(*) FROM Denuncia l INNER JOIN l.handle h WHERE h.id = :idHandle");
    	 query.setParameter("idHandle", idHandle);
    	 return count(query);
    }
  
}
