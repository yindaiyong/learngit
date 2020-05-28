package com.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rabbitmq.sender.RabbitMqSender;

@Controller
public class RabbitMqController {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqController.class);
	
	@Autowired
	private RabbitMqSender rabbitMqSender;
	
	@PostMapping("/getRabbitMq")
	public String getRabbitMq() {
		rabbitMqSender.send();
		LOGGER.info("生产者生产消息。。。。");
		return "OK";
	}
	
	@GetMapping("/index") 
    public String index() {
        return "/index";
    }

}
