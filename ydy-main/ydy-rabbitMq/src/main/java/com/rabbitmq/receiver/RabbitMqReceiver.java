package com.rabbitmq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
 * 消费者
 *
 */
@Component
@RabbitListener(queues = "rabbitMq-Queue")
public class RabbitMqReceiver {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqReceiver.class);
	
	@RabbitHandler
    public void process(String queue) {
		LOGGER.info("rabbitMq-Queue:RabbitMqReceiver ---> 接受消息" + queue);
    }
}
