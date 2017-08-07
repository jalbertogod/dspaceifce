package org.dspace.denuncia.factory;

import org.dspace.services.factory.DSpaceServicesFactory;
import org.dspace.denuncia.service.DenunciaService;

/**
 *
 * @author Alberto
 */
public abstract class DenunciaServiceFactory {
    public abstract DenunciaService getDenunciaService();

    public static DenunciaServiceFactory getInstance()
    {
        return DSpaceServicesFactory.getInstance().getServiceManager().getServiceByName("denunciaServiceFactory", DenunciaServiceFactory.class);
    }
}
