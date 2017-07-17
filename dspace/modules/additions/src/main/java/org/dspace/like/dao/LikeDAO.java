/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.like.dao;

import org.dspace.core.Context;
import org.dspace.core.GenericDAO;
import org.dspace.like.Like;

import java.sql.SQLException;
import java.util.List;

/**
 * Database Access Object interface class for the VersionA object.
 * The implementation of this class is responsible for all database calls for the VersionA object and is autowired by spring
 * This class should only be accessed from a single service and should never be exposed outside of the API
 *
 * @author kevinvandevelde at atmire.com
 */
public interface LikeDAO extends GenericDAO<Like>
{
    public Like findByLike(Context context, Like like) throws SQLException;
    

}
