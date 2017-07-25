package org.dspace.like.factory;

import org.dspace.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alberto
 */
public class LikeServiceFactoryImpl extends LikeServiceFactory {

 
    @Autowired(required = true)
    private LikeService likeService;


    @Override
    public LikeService getLikeService() {
        return likeService;
    }
}
