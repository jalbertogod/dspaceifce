
package org.dspace.like;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import org.dspace.core.Context;
import org.dspace.like.dao.LikeDAO;
import org.dspace.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.apache.log4j.Logger;

/**
 *
 *
 * @author Alberto 
*/
public class LikeServiceImpl implements LikeService {
	private static Logger log = Logger.getLogger(LikeServiceImpl.class);
    @Autowired(required = true)
    protected  LikeDAO likeDAO;


   

    protected LikeServiceImpl()
    {

    }

    /** Service Methods */
   
    @Override
    public Like createNewLike(Context c, String like )  throws SQLException{
    	Like like2 = new Like();
    	like2 =likeDAO.create(c, like2);
    	likeDAO.save(c, like2);
        return  null;
    }

   
    /*

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

*/
    
}
