package izone.adams.communication.jboss.cluster;

import javax.management.NotificationListener;

//import org.jboss.ha.jmx.HAServiceMBean;

/**
 * Created by IntelliJ IDEA.
 * User: fchen
 * Date: Apr 12, 2005
 * Time: 9:20:48 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * JIRA# ADAMS-6873 JBoss Migration EAP 7 : izone-service.jar deployment Added
 * sendNotification method to resolve NoSuchMethod sendNotification error .
 */
public interface HANotificationMBean {

	/**
	 * Adds a notificaiton listener
	 * 
	 * @param listener
	 */
	public void register(NotificationListener listener);

	/**
	 * Remove notification listener
	 * 
	 * @param listener
	 * @return true if unregistered.  False if there was an error unregistering it
	 */
	public boolean unregister(NotificationListener listener);

	
	/**
	 * Send a notification
	 * 
	 * @param notification
	 */
	public void sendNotification(javax.management.Notification notification);

}
