/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.metadataValue.dao;

import org.dspace.core.Context;
import org.dspace.core.GenericDAO;
import java.sql.SQLException;
import java.util.List;
import org.dspace.content.MetadataValue;

/**
 * Database Access Object interface class for the VersionA object.
 * The implementation of this class is responsible for all database calls for the VersionA object and is autowired by spring
 * This class should only be accessed from a single service and should never be exposed outside of the API
 *
 * @author kevinvandevelde at atmire.com
 */
public interface MetadataValueDAO extends GenericDAO<MetadataValue>
{
	public MetadataValue getMetadataValue(Context context, java.util.UUID uuidObject,String metadatafieldElement,String metadatafieldQualifier)
            throws SQLException;
}
