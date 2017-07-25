
package org.dspace.rest;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dspace.utils.LikeRESP;
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
    @GET
    @Path("/set/{handle1}/set/{handle2}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public LikeRESP set(@Context HttpHeaders headers,@PathParam("handle1") String handle1,@PathParam("handle2") String handle2) throws UnsupportedEncodingException {
        org.dspace.core.Context context = null;	

        try {
            context = Resource.createContext();
            EPerson ePerson = context.getCurrentUser();
           
            likeService.createNewLike(context,handle1,handle2,ePerson);
           
           // Bookmark bookmark = bookmarkService.create(context);
          //  bookmark.setTitle("Teste");
            if(ePerson != null) {
                //DB EPerson needed since token won't have full info, need context
                EPerson dbEPerson = epersonService.findByEmail(context, ePerson.getEmail());

                LikeRESP likeRESP = new LikeRESP(dbEPerson.getEmail(), dbEPerson.getFullName());
                context.complete();
                return likeRESP;
            }else{
            	 context.complete();
            }
           
        } catch (ContextException e)
        {
            Resource.processException("Like context error: " + e.getMessage(), context);
        } catch (SQLException e) {
            Resource.processException("Like  db lookup error: " + e.getMessage(), context);
        } finally {
            context.abort();
        }

        //fallback status, unauth
        return new LikeRESP();
    }


}
