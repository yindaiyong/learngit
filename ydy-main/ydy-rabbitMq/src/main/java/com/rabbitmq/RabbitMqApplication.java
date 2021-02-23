package com.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * 
 *
 */
@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
public class RabbitMqApplication {
	
    	private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqApplication.class);

        public static void main(String[] args) {

            LOGGER.info("启动RabbitMq项目");
            SpringApplication.run(RabbitMqApplication.class, args);
        }
        
}
