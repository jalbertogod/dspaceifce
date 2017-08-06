/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.denuncia.service;

import org.dspace.core.Context;
import org.dspace.denuncia.Denuncia;
import java.sql.SQLException;
import org.dspace.eperson.EPerson;
import org.dspace.content.DSpaceObject;
import org.dspace.content.MetadataValue;

/**
 *
 *
 * @author Alberto
 */
public interface DenunciaService {

   
    public Denuncia createNewDenuncia(Context c, String handle1, String handle2,EPerson ePerson)  throws SQLException;
    public MetadataValue createMetadataValueDenuncia(Context c,DSpaceObject dSpaceObjectItem)  throws SQLException;
    
    
    /*
     * public Denuncia getDenuncia(Context c, int denunciaID) throws SQLException;
    public void removeDenuncia(Context c, Denuncia denuncia) throws SQLException;


    public Denuncia getDenuncia(Context c, int denunciaID) throws SQLException;

  
    public Denuncia getDenuncia(Context c, Denuncia denuncia) throws SQLException;
    
*/
    
}
