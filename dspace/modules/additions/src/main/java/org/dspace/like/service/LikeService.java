/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.like.service;

import org.dspace.core.Context;
import org.dspace.like.Like;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author Alberto
 */
public interface LikeService {

   
    public Like createNewLike(Context c, String like)  throws SQLException;
    /*
    public void removeLike(Context c, Like like) throws SQLException;


    public Like getLike(Context c, int likeID) throws SQLException;

  
    public Like getLike(Context c, Like like) throws SQLException;
    
*/
    
}
