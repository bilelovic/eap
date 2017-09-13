package com.sfl.bean;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "QueueMessageConsumer", activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "activemq/queue/TestQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class Consumer implements MessageListener {

	private static Logger logger = Logger.getLogger(Consumer.class.toString());

	@Override
	public void onMessage(Message message) {
		logger.info("Queue message received ---- > " + message);
	}

}
