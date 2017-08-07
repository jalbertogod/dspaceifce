
package org.dspace.denuncia;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.dspace.eperson.EPerson;
import org.dspace.core.Context;
import org.dspace.denuncia.dao.DenunciaDAO;
import org.dspace.denuncia.service.DenunciaService;
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
public class DenunciaServiceImpl implements DenunciaService {
	private static Logger log = Logger.getLogger(DenunciaServiceImpl.class);
    @Autowired(required = true)
    protected DenunciaDAO denunciaDAO;
    @Autowired(required = true)
    protected MetadataValueDAO metadataValueDAO;
    @Autowired(required = true)
    protected MetadataFieldDAO metadataFieldDAO;
    @Autowired(required = true)
    protected HandleDAO handleDAO;
    @Autowired(required = true)
    protected MetadataSchemaDAO metadataSchemaDAO;
    

   

    protected DenunciaServiceImpl()
    {
    	 super();
    }

    /** Service Methods */
   
    @Override
    public Denuncia createNewDenuncia(Context c, String handle1,String handle2,EPerson ePerson ){
    	try{
    		Handle handleObject =handleDAO.findByHandle(c,handle1+"/"+handle2);
    		if(handleObject!=null && handleObject.getResourceTypeId()==Constants.ITEM){
    			int countDenuncia=0;
    			Denuncia denuncia2 = denunciaDAO.getDenunciaEPersonHandle(c, ePerson.getID(),handleObject.getID());
    			if(denuncia2 !=null){
    				denunciaDAO.delete(c,denuncia2);
    			}else{
    				denuncia2 =  denunciaDAO.create(c,new Denuncia());
    				denuncia2.setDenunciaDate(new Date());
        	    	denuncia2.setHandle(handleObject);
        	    	denuncia2.setEPerson(ePerson);
        	    	denunciaDAO.save(c,denuncia2);
    			}
    			countDenuncia=denunciaDAO.countDenunciaItem(c,handleObject.getID());
    	    	MetadataValue metadataValue = metadataValueDAO.getMetadataValue(c, handleObject.getDSpaceObject().getID(),org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_QUALIFIER);
    	    	if(metadataValue == null){
    	    		metadataValue = createMetadataValueDenuncia(c,handleObject.getDSpaceObject());
    	    	}
    	    	metadataValue.setValue(String.valueOf(countDenuncia));
    	    	denuncia2.setCountDenuncia(countDenuncia);
    	    	metadataValueDAO.save(c,metadataValue);
    	        return  denuncia2;
    		}
	    	return null;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new RuntimeException(e.getMessage(), e);
	    }
    }

  
    @Override
     public MetadataValue createMetadataValueDenuncia(Context c,DSpaceObject dSpaceObjectItem)  throws SQLException {
    	MetadataSchema metadataSchema =metadataSchemaDAO.find(c, "dc");
    	MetadataField metadataField = metadataFieldDAO.findByElement(c,metadataSchema,org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_QUALIFIER);
    	if(metadataField ==null){
    		MetadataFieldService metadataFieldService = ContentServiceFactory.getInstance().getMetadataFieldService();
    		try {
                c.turnOffAuthorisationSystem();
                metadataField = metadataFieldService.create(c, metadataSchema,org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_ELEMENT,org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_QUALIFIER, org.dspace.metadataValue.Constants.METADATA_FIELD_DENUNCIA_SCOPE_NOTE);
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
