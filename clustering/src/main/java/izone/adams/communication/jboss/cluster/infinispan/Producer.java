package izone.adams.communication.jboss.cluster.infinispan;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.infinispan.manager.EmbeddedCacheManager;
  
@Singleton
public class Producer {
 
    @Resource(lookup = "java:jboss/infinispan/container/web")
    private EmbeddedCacheManager container;
 
    private org.infinispan.Cache<String, String> cache;
 
    @Inject
    CacheListener listener;
 
    @PostConstruct
    public void initCache() {
    	System.out.println( "initializing cache");
        this.cache = container.getCache("dist");
 
        System.out.println("Got cache " + cache.getName());
        cache.addListener(listener);
 
    }
 
    @Produces
    public org.infinispan.Cache<String, String> getCache() {
        return cache;
    }
 
    public void setCache(org.infinispan.Cache<String, String> cache) {
        this.cache = cache;
    }
 
}
