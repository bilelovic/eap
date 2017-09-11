package izone.adams.communication.jboss.cluster;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
// JBOSS 7.0.0 EAP changes to do Lookup and avoid Jboss 4 dependencies.removed javax.ejb.Singleton and javax.ejb.Startup
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanServer;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;

/**
 * Created by IntelliJ IDEA. User: fchen Date: Apr 12, 2005 Time: 9:20:32 AM To
 * change this template use File | Settings | File Templates.
 */

/**
 * JIRA# ADAMS-6873 JBoss Migration EAP 7 : izone-service.jar deployment
 * Jboss-service.xml which was used in JBoss 4 and 5 replaced by annotations and Implementation class extends from NotificationBroadcasterSupport.
 */

// JBOSS 7.0.0 EAP changes to do Lookup and avoid Jboss 4 dependencies @Singleton and @Startup
@Singleton
@Startup
public class HANotification extends NotificationBroadcasterSupport implements HANotificationMBean{

	public static final String CACHE_MANAGER_JNDI_NAME = "izone.communication:service=NotificationManager";

	public void register(NotificationListener listener) {
		addNotificationListener(listener, null, null);
	}
	
	
	public boolean unregister(NotificationListener listener) {
		try {
			removeNotificationListener(listener);
		} catch (ListenerNotFoundException e) {
			// listener not found
			return false;
		}
		
		// success
		return true;
	}
	
	

	// private String status = "not started";
	private ObjectName objectName;
	private MBeanServer platformMBeanServer;

	@PostConstruct
	public void start()  {
		try {
			System.out.println("started ----------------");
			//objectName = new ObjectName(JMXUtil.CACHE_MANAGER_JNDI_NAME);
			objectName = new ObjectName(CACHE_MANAGER_JNDI_NAME);
			platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
			platformMBeanServer.registerMBean(this, objectName);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to register " + objectName + " into JMX:" + e);
		}
	}

	@PreDestroy
	public void stop() {
		try {
			platformMBeanServer.unregisterMBean(objectName);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to unregister " + objectName + " from JMX:" + e);
		}
	}



}
