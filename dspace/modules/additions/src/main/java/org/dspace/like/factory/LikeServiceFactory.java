package org.dspace.like.factory;

import org.dspace.services.factory.DSpaceServicesFactory;
import org.dspace.like.service.LikeService;

/**
 *
 * @author Alberto
 */
public abstract class LikeServiceFactory {
    public abstract LikeService getLikeService();

    public static LikeServiceFactory getInstance()
    {
        return DSpaceServicesFactory.getInstance().getServiceManager().getServiceByName("likeServiceFactory", LikeServiceFactory.class);
    }
}
