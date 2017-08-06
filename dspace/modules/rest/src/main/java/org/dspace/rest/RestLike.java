
package org.dspace.rest;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dspace.utils.LikeMessage;
import org.dspace.like.Like;
import org.dspace.like.factory.LikeServiceFactory;
import org.dspace.like.service.LikeService;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.factory.EPersonServiceFactory;
import org.dspace.eperson.service.EPersonService;
import org.dspace.rest.exceptions.ContextException;

/**
 * @author Alberto
 *
 */
@Path("/like")
public class RestLike {
    protected EPersonService epersonService = EPersonServiceFactory.getInstance().getEPersonService();
    protected LikeService likeService = LikeServiceFactory.getInstance().getLikeService();
    private static Logger log = Logger.getLogger(RestLike.class);
 

    /**
     * Method to check current status of the service and logged in user.
     *
     * okay: true | false
     * authenticated: true | false
     * epersonEMAIL: jalbertogod@gmail.com
     * epersonNAME: Alberto
     * @param headers
     *            Request header which contains the header named
     *            "rest-dspace-token" containing the token as value.
     * @return status
     */
    @POST
    @Path("/set")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public LikeMessage set(@QueryParam("handle1") String handle1,@QueryParam("handle2") String handle2) throws UnsupportedEncodingException {
        org.dspace.core.Context context = null;	
        LikeMessage likeMessage = new LikeMessage();

        try {
            context = Resource.createContext();
            EPerson ePerson = context.getCurrentUser();
            if(ePerson != null) {
            	likeMessage.setHandle1(handle1);
            	likeMessage.setHandle2(handle2);
            	Like like= likeService.createNewLike(context,handle1,handle2,ePerson);
                EPerson dbEPerson = epersonService.findByEmail(context, ePerson.getEmail());
                context.complete();
                if(like != null){
                	likeMessage.setCountLike(like.getCountLike());
                	likeMessage.setErro(false);
                	likeMessage.setAuthenticated(true);
                	likeMessage.setIdLike(like.getID());
                }
                likeMessage.setMensagem("Dados registrados com sucesso");
                return likeMessage;
            }else{
            	likeMessage.setErro(true);
            	likeMessage.setAuthenticated(false);
            	likeMessage.setMensagem("Não foi possível autenticar com o serviço");
            }
            context.complete();
        } catch (ContextException e)
        {
        	likeMessage.setErro(true);
        	likeMessage.setMensagem("Like context error: " + e.getMessage());
            Resource.processException("Like context error: " + e.getMessage(), context);
        } catch (SQLException e) {
        	likeMessage.setMensagem("Like  db lookup error:" + e.getMessage());
            Resource.processException("Like  db lookup error: " + e.getMessage(), context);
        } finally {
            context.abort();
        }
        return likeMessage;
    }


}
