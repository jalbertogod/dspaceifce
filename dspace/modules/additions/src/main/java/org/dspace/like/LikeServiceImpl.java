
package org.dspace.like;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.dspace.eperson.EPerson;
import org.dspace.core.Context;
import org.dspace.like.dao.LikeDAO;
import org.dspace.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.apache.log4j.Logger;
import org.dspace.handle.Handle;
import org.dspace.handle.dao.HandleDAO;


/**
 *
 *
 * @author Alberto 
*/
public class LikeServiceImpl implements LikeService {
	private static Logger log = Logger.getLogger(LikeServiceImpl.class);
    @Autowired(required = true)
    protected  LikeDAO likeDAO;
    @Autowired(required = true)
    protected  HandleDAO handleDAO;

   

    protected LikeServiceImpl()
    {
    	 super();
    }

    /** Service Methods */
   
    @Override
    public Like createNewLike(Context c, String handle1,String handle2,EPerson ePerson ){
    	try{
	    	Like like2 =  likeDAO.create(c,new Like());
	    	Handle handleObject =handleDAO.findByHandle(c,handle1+"/"+handle2);
	    	like2.setLikeDate(new Date());
	    	like2.setHandle(handleObject);
	    	like2.setEPerson(ePerson);
	    	System.out.print(like2.getID()+"------------");
	    	
	    	likeDAO.save(c,like2);
	    	System.out.print("ID LIKE:"+like2.getID()+"  createNewLike - ALBERTO Teste--:"+handleObject.getResourceTypeId()+"---TESTE");
	        return  like2;
	    } catch (SQLException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
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
