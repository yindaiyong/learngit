package com.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@MapperScan("com.producer.mapper")
public class ProducerApplication{

    private static Logger LOGGER = LoggerFactory.getLogger(ProducerApplication.class);

    public static void main(String[] args) {

        LOGGER.info("启动producer项目");
        SpringApplication.run(ProducerApplication.class, args);
    }

}

