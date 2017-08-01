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
    	System.out.println("uuidObject:"+uuidObject+",metadatafieldElement:"+metadatafieldElement+",metadatafieldQualifier:"+metadatafieldQualifier);
        String queryString = "SELECT m FROM MetadataValue m JOIN FETCH m.metadataField WHERE m.dspace_object_id = :dspace_object_id AND m.metadataField.element = :element AND m.metadataField.qualifier = :qualifier AND ";
        Query query = createQuery(context, queryString);
        query.setParameter("element", metadatafieldElement);
        query.setParameter("qualifier", metadatafieldElement);
        query.setParameter("dspace_object_id", uuidObject);
        query.setMaxResults(1);
        return (MetadataValue) query.uniqueResult();
    }
  
}
