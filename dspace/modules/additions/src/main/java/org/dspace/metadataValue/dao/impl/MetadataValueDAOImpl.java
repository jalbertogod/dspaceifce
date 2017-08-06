package org.dspace.metadataValue.dao.impl;

import org.dspace.core.AbstractHibernateDAO;
import org.dspace.core.Context;
import org.dspace.content.MetadataValue;
import org.dspace.metadataValue.dao.MetadataValueDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alberto
 */
public class MetadataValueDAOImpl extends AbstractHibernateDAO<MetadataValue>  implements MetadataValueDAO
{
    protected MetadataValueDAOImpl()
    {
        super();
    }
    @Override
    public MetadataValue getMetadataValue(Context context, java.util.UUID uuidObject,String metadatafieldElement,String metadatafieldQualifier)
            throws SQLException
    {
       Query query = createQuery(context, "SELECT m FROM MetadataValue m INNER JOIN FETCH m.metadataField  mf INNER JOIN FETCH m.dSpaceObject ob WHERE ob.id = :dspace_object_id AND  mf.element = :element AND mf.qualifier = :qualifier");
       query.setParameter("element", metadatafieldElement);   
       query.setParameter("qualifier", metadatafieldQualifier);
       query.setParameter("dspace_object_id", uuidObject);
       query.setMaxResults(1);
       return (MetadataValue) query.uniqueResult();
    }
    @Override
    public int countRows(Context context) throws SQLException {
        return count(createQuery(context, "SELECT count(*) FROM MetadataValue"));
    }


  
}
