package izone.adams.communication.jboss.cluster.infinispan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Singleton;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryExpired;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;

@Singleton
@Listener(clustered = true)
public class CacheListener {
	
	List<CacheEntryEvent> events = Collections.synchronizedList(new ArrayList<CacheEntryEvent>());
	

	@CacheEntryCreated
    @CacheEntryModified
    @CacheEntryExpired
    @CacheEntryRemoved
    public void onCacheEvent(CacheEntryEvent event) {
		System.out.println("Adding new cluster event " + event.getKey());
       events.add(event);
    }
	
}
