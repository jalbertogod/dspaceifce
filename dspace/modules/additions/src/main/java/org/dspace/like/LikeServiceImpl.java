
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
import org.dspace.metadataValue.dao.MetadataValueDAO;
import org.dspace.content.MetadataValue;
import org.dspace.core.Constants;
import org.dspace.content.MetadataSchema;
import org.dspace.content.MetadataField;
import org.dspace.content.dao.MetadataFieldDAO;
import org.dspace.content.dao.MetadataSchemaDAO;
import org.dspace.content.service.MetadataFieldService;
import org.dspace.content.factory.ContentServiceFactory;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.NonUniqueMetadataException;
import org.dspace.content.service.MetadataValueService;
import org.dspace.content.DSpaceObject;

/**
 *
 *
 * @author Alberto 
*/
public class LikeServiceImpl implements LikeService {
	private static Logger log = Logger.getLogger(LikeServiceImpl.class);
    @Autowired(required = true)
    protected LikeDAO likeDAO;
    @Autowired(required = true)
    protected MetadataValueDAO metadataValueDAO;
    @Autowired(required = true)
    protected MetadataFieldDAO metadataFieldDAO;
    @Autowired(required = true)
    protected HandleDAO handleDAO;
    @Autowired(required = true)
    protected MetadataSchemaDAO metadataSchemaDAO;
    

   

    protected LikeServiceImpl()
    {
    	 super();
    }

    /** Service Methods */
   
    @Override
    public Like createNewLike(Context c, String handle1,String handle2,EPerson ePerson ){
    	try{
    		Handle handleObject =handleDAO.findByHandle(c,handle1+"/"+handle2);
    		if(handleObject!=null && handleObject.getResourceTypeId()==Constants.ITEM){
    			int countLike=0;
    			Like like2 = likeDAO.getLikeEPersonHandle(c, ePerson.getID(),handleObject.getID());
    			if(like2 !=null){
    				likeDAO.delete(c,like2);
    			}else{
    				like2 =  likeDAO.create(c,new Like());
    				like2.setLikeDate(new Date());
        	    	like2.setHandle(handleObject);
        	    	like2.setEPerson(ePerson);
        	    	likeDAO.save(c,like2);
    			}
    			countLike=likeDAO.countLikeItem(c,handleObject.getID());
    	    	MetadataValue metadataValue = metadataValueDAO.getMetadataValue(c, handleObject.getDSpaceObject().getID(),org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_QUALIFIER);
    	    	if(metadataValue == null){
    	    		metadataValue = createMetadataValueLike(c,handleObject.getDSpaceObject());
    	    	}
    	    	metadataValue.setValue(String.valueOf(countLike));
    	    	like2.setCountLike(countLike);
    	    	metadataValueDAO.save(c,metadataValue);
    	        return  like2;
    		}
	    	return null;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new RuntimeException(e.getMessage(), e);
	    }
    }

  
    @Override
     public MetadataValue createMetadataValueLike(Context c,DSpaceObject dSpaceObjectItem)  throws SQLException {
    	MetadataSchema metadataSchema =metadataSchemaDAO.find(c, "dc");
    	MetadataField metadataField = metadataFieldDAO.findByElement(c,metadataSchema,org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_QUALIFIER);
    	if(metadataField ==null){
    		MetadataFieldService metadataFieldService = ContentServiceFactory.getInstance().getMetadataFieldService();
    		try {
                c.turnOffAuthorisationSystem();
                metadataField = metadataFieldService.create(c, metadataSchema,org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_QUALIFIER, org.dspace.metadataValue.Constants.METADATA_FIELD_LIKE_SCOPE_NOTE);
            } catch (AuthorizeException e) {
                log.error(e.getMessage(), e);
               
            } catch (NonUniqueMetadataException e) {
                log.error(e.getMessage(), e);
                
            }finally {
                c.restoreAuthSystemState();
            }
    	}
    	MetadataValueService MetadataValueService = ContentServiceFactory.getInstance().getMetadataValueService();
    	MetadataValue metadataValue =MetadataValueService.create(c, dSpaceObjectItem, metadataField);
    	return metadataValue;
    	
    }
}
