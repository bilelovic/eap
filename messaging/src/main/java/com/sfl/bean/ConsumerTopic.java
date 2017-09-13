package com.sfl.bean;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "TopicMessageConsumer", activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "AdamsGTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") })
public class ConsumerTopic implements MessageListener { 
	private static Logger logger = Logger.getLogger(ConsumerTopic.class.toString());

	@Override
	public void onMessage(Message message) {
		logger.info("Topic message received ---- > " + message);
		
	}
}
