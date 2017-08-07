
package org.dspace.rest;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dspace.utils.DenunciaMessage;
import org.dspace.denuncia.Denuncia;
import org.dspace.denuncia.factory.DenunciaServiceFactory;
import org.dspace.denuncia.service.DenunciaService;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.factory.EPersonServiceFactory;
import org.dspace.eperson.service.EPersonService;
import org.dspace.rest.exceptions.ContextException;

/**
 * @author Alberto
 *
 */
@Path("/denuncia")
public class RestDenuncia {
    protected EPersonService epersonService = EPersonServiceFactory.getInstance().getEPersonService();
    protected DenunciaService denunciaService = DenunciaServiceFactory.getInstance().getDenunciaService();
    private static Logger log = Logger.getLogger(RestDenuncia.class);
 

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
    public DenunciaMessage set(@QueryParam("handle1") String handle1,@QueryParam("handle2") String handle2) throws UnsupportedEncodingException {
        org.dspace.core.Context context = null;	
        DenunciaMessage denunciaMessage = new DenunciaMessage();

        try {
            context = Resource.createContext();
            EPerson ePerson = context.getCurrentUser();
            if(ePerson != null) {
            	denunciaMessage.setHandle1(handle1);
            	denunciaMessage.setHandle2(handle2);
            	Denuncia denuncia= denunciaService.createNewDenuncia(context,handle1,handle2,ePerson);
                EPerson dbEPerson = epersonService.findByEmail(context, ePerson.getEmail());
                context.complete();
                if(denuncia != null){
                	denunciaMessage.setCountDenuncia(denuncia.getCountDenuncia());
                	denunciaMessage.setErro(false);
                	denunciaMessage.setAuthenticated(true);
                	denunciaMessage.setIdDenuncia(denuncia.getID());
                }
                denunciaMessage.setMensagem("Dados registrados com sucesso");
                return denunciaMessage;
            }else{
            	denunciaMessage.setErro(true);
            	denunciaMessage.setAuthenticated(false);
            	denunciaMessage.setMensagem("Não foi possível autenticar com o serviço");
            }
            context.complete();
        } catch (ContextException e)
        {
        	denunciaMessage.setErro(true);
        	denunciaMessage.setMensagem("Denuncia context error: " + e.getMessage());
            Resource.processException("Denuncia context error: " + e.getMessage(), context);
        } catch (SQLException e) {
        	denunciaMessage.setMensagem("Denuncia  db lookup error:" + e.getMessage());
            Resource.processException("Denuncia  db lookup error: " + e.getMessage(), context);
        } finally {
            context.abort();
        }
        return denunciaMessage;
    }


}
