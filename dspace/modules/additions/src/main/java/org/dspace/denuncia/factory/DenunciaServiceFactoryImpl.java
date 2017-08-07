package org.dspace.denuncia.factory;

import org.dspace.denuncia.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alberto
 */
public class DenunciaServiceFactoryImpl extends DenunciaServiceFactory {

 
    @Autowired(required = true)
    private DenunciaService denunciaService;


    @Override
    public DenunciaService getDenunciaService() {
        return denunciaService;
    }
}
