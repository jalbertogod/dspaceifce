
package org.dspace.like;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import org.dspace.core.Context;
import org.dspace.like.dao.LikeDAO;
import org.dspace.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 *
 * @author Alberto 
*/
public class LikeServiceImpl implements LikeService {

    @Autowired(required = true)
    protected LikeDAO likeDAO;


   

    protected LikeServiceImpl()
    {

    }

    /** Service Methods */
    @Override
    public Like createNewLike(Context c, Like like){
        return createNewLike(c, like);
    }

   


    @Override
    public void removeLike(Context c, Like like) throws SQLException {
        
    }

    @Override
    public Like getLike(Context c, int likeID) throws SQLException {
        return likeDAO.findByID(c, Like.class, likeID);
    }


   

  


    @Override
    public Like getLike(Context c, Like like) throws SQLException {
        return likeDAO.findByLike(c, like);
    }


    
}
