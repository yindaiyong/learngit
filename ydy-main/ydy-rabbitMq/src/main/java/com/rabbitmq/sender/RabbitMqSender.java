package com.rabbitmq.sender;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * 生产者
 *
 */
@Component
public class RabbitMqSender {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqSender.class);
	
	 @Autowired
	 private AmqpTemplate rabbitTemplate;
	 
	 public void send() {
        String sendMsg = "rabbitMq-Queue" + new Date()+",生产了消息";
        LOGGER.info("rabbitMq-Queue:生产者--->" + sendMsg);
        rabbitTemplate.convertAndSend("rabbitMq-Queue", sendMsg);
     }

}
