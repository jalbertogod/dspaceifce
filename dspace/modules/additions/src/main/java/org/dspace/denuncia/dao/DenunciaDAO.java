/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.denuncia.dao;

import org.dspace.core.Context;
import org.dspace.core.GenericDAO;
import org.dspace.denuncia.Denuncia;
import java.sql.SQLException;
import java.util.List;

public interface DenunciaDAO extends GenericDAO<Denuncia>
{
	public Denuncia getDenunciaEPersonHandle(Context context, java.util.UUID uuidEPerson,Integer idHandle)
            throws SQLException;
	
	public int countDenunciaItem(Context context,Integer idHandle) throws SQLException;

}
